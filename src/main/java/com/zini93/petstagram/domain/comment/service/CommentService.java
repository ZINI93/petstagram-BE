package com.zini93.petstagram.domain.comment.service;

import com.zini93.petstagram.domain.comment.dto.CommentRequestDto;
import com.zini93.petstagram.domain.comment.dto.CommentResponseDto;
import com.zini93.petstagram.domain.comment.dto.CommentUpdateDto;

public interface CommentService {

    CommentResponseDto createCommentWithValidate(String userUuid, String postUuid, CommentRequestDto requestDto);

    CommentResponseDto updateCommentWithValidate(String userUuid, String commentUuid, String postUuid, CommentUpdateDto updateDto);

    void deleteComment(String userUuid, String commentUuid, String postUuid);

}
