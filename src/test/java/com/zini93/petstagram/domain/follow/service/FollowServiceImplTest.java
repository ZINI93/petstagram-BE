package com.zini93.petstagram.domain.follow.service;

import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import com.zini93.petstagram.domain.follow.entity.Follow;
import com.zini93.petstagram.domain.follow.repository.FollowRepository;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FollowServiceImplTest {

    @Mock UserRepository userRepository;
    @Mock FollowRepository followRepository;
    @InjectMocks FollowServiceImpl followService;


    User follower;
    User following;
    Follow follow;

    @BeforeEach
    void serUp(){

        follower = User.builder().userUuid(UUID.randomUUID().toString()).build();
        following = User.builder().userUuid(UUID.randomUUID().toString()).build();

        follow = new Follow(UUID.randomUUID().toString(), follower, following);
    }


    @Test
    void follow() {
        //given
        when(userRepository.findByUserUuid(follower.getUserUuid())).thenReturn(Optional.ofNullable(follower));
        when(userRepository.findByUserUuid(following.getUserUuid())).thenReturn(Optional.ofNullable(following));
        when(followRepository.save(any(Follow.class))).thenReturn(follow);

        //when
        FollowResponseDto result = followService.followUser(follower.getUserUuid(), following.getUserUuid());

        //then
        assertNotNull(result);
        assertEquals(follow.getFollower(),follower);
        assertEquals(follow.getFollowing(),following);

        verify(userRepository,times(1)).findByUserUuid(follower.getUserUuid());
        verify(userRepository,times(1)).findByUserUuid(following.getUserUuid());
        verify(followRepository,times(1)).save(any(Follow.class));
    }

    @Test
    void getFollower() {
    }

    @Test
    void getFollowing() {
    }

    @Test
    void unFollow() {

        //given


        //when
        followRepository.deleteByUserUserUuidAndUserUserUuid(follower.getUserUuid(),following.getUserUuid());

        //then

    }
}