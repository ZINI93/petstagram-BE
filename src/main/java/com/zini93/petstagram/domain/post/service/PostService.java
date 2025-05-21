package com.zini93.petstagram.domain.post.service;

import com.zini93.petstagram.domain.post.dto.PostRequestDto;
import com.zini93.petstagram.domain.post.dto.PostResponseDto;
import com.zini93.petstagram.domain.post.dto.PostUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostResponseDto createPostWithValidate(String userUuid, PostRequestDto requestDto);

    PostResponseDto getPostInfo(String userUuid, String postUuid);

    Page<PostResponseDto> getAllPost(String userUuid, String postUuid, Pageable pageable);

    PostResponseDto updatePostWithValidate(String userUuid, String postUuid, PostUpdateDto updateDto);

    void deletePost(String userUuid, String postUuid);
}
