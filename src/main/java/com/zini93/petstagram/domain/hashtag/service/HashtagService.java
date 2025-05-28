package com.zini93.petstagram.domain.hashtag.service;


import com.zini93.petstagram.domain.hashtag.dto.HashtagRequestDto;
import com.zini93.petstagram.domain.hashtag.dto.HashtagResponseDto;

public interface HashtagService {

    HashtagResponseDto createHashtagAndValidate(String userUuid, HashtagRequestDto requestDto);
    void deleteHashtag(String userUuid, String hashtagUuid);
}