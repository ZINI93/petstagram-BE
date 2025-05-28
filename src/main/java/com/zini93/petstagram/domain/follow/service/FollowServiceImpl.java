package com.zini93.petstagram.domain.follow.service;

import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import com.zini93.petstagram.domain.follow.entity.Follow;
import com.zini93.petstagram.domain.follow.exception.DuplicateFollowException;
import com.zini93.petstagram.domain.follow.exception.FollowNotFoundException;
import com.zini93.petstagram.domain.follow.repository.FollowRepository;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserUuidNotFoundException;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService{

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    private Follow createFollow(User follower, User following){

        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }

    @Override @Transactional
    public FollowResponseDto followUser(String followerUuid, String followingUuid) {

        User follower = findUserByUserUuidInUser(followerUuid);
        User following = findUserByUserUuidInUser(followingUuid);

        if (followRepository.existsByFollowerAndFollowing(follower,following)){
            throw new DuplicateFollowException("Already following");
        }

        Follow follow = createFollow(follower, following);
        Follow savedFollow = followRepository.save(follow);

        return savedFollow.toResponse();
    }

    @Override
    public Page<FollowResponseDto> getFollower(String userUuid, String followerUuid, String nicName, Pageable pageable) {
        return followRepository.followerList(userUuid, followerUuid, nicName, pageable);
    }

    @Override
    public Page<FollowResponseDto> getFollowing(String userUuid, String followingUuid, String nickName, Pageable pageable) {
        return followRepository.followingList(userUuid, followingUuid, nickName, pageable);
    }

    private User findUserByUserUuidInUser(String followerUuid) {
        return userRepository.findByUserUuid(followerUuid)
                .orElseThrow(() -> new UserUuidNotFoundException("User not found"));
    }

    @Override @Transactional
    public void unFollow(String followUuid, String targetUuid ) {

        Follow follow = findFollowByFollowUuidInFollow(followUuid);
        User following = findUserByUserUuidInUser(targetUuid);

        if (!follow.getFollowing().equals(following)){
            throw new FollowNotFoundException("Following was not found");
        }

        followRepository.deleteByUserUserUuidAndUserUserUuid(followUuid,targetUuid);

    }

    private Follow findFollowByFollowUuidInFollow(String followUuid) {
        return followRepository.findByFollowUuid(followUuid)
                .orElseThrow(() -> new FollowNotFoundException("follow Uuid: Follow Not found"));
    }
}
