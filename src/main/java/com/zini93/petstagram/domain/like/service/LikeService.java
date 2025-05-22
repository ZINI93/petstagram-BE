package com.zini93.petstagram.domain.like.service;

import com.zini93.petstagram.domain.like.dto.LikeResponseDto;
import com.zini93.petstagram.domain.like.repository.LikeRepository;
import com.zini93.petstagram.domain.post.entity.Post;

public interface LikeService {


    LikeResponseDto createLikeWithValidate(String userUuid, String postUuid);
    void deleteLike(String userUuid, String postUuid);
    LikeResponseDto getLikeCountByPostUuid(String productUuid);

}
