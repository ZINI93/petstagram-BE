package com.zini93.petstagram.domain.hashtag.service;


import com.zini93.petstagram.domain.hashtag.dto.HashtagRequestDto;
import com.zini93.petstagram.domain.hashtag.dto.HashtagResponseDto;
import com.zini93.petstagram.domain.hashtag.entity.Hashtag;
import com.zini93.petstagram.domain.hashtag.exception.HashtagNotFoundException;
import com.zini93.petstagram.domain.hashtag.repository.HashtagRepository;
import com.zini93.petstagram.domain.user.dto.UserResponseDto;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserNotFoundException;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class HashtagServiceImpl implements HashtagService{

    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;


    private Hashtag createHashtag(String userUuid, HashtagRequestDto requestDto){

        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new UserNotFoundException("user Uuid : user not found"));

        return Hashtag.builder()
                .user(user)
                .name(requestDto.getName())
                .build();
    }


    @Override
    public HashtagResponseDto createHashtagAndValidate(String userUuid, HashtagRequestDto requestDto) {
        Hashtag savedHashtag = createHashtag(userUuid, requestDto);

        return hashtagRepository.save(savedHashtag).toResponse();
    }

    @Override
    public void deleteHashtag(String userUuid, String hashtagUuid) {
        Hashtag hashtag = hashtagRepository.findByUserUserUuidAndHashtagUuid(userUuid, hashtagUuid)
                .orElseThrow(() -> new HashtagNotFoundException("hashtag Uuid: Hashtag not found"));

        hashtagRepository.delete(hashtag);
    }
}
