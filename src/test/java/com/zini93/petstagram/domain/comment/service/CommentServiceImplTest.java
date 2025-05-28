package com.zini93.petstagram.domain.comment.service;

import com.zini93.petstagram.domain.comment.dto.CommentRequestDto;
import com.zini93.petstagram.domain.comment.dto.CommentResponseDto;
import com.zini93.petstagram.domain.comment.dto.CommentUpdateDto;
import com.zini93.petstagram.domain.comment.entity.Comment;
import com.zini93.petstagram.domain.comment.repository.CommentRepository;
import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.post.repository.PostRepository;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock UserRepository userRepository;
    @Mock PostRepository postRepository;
    @Mock CommentRepository commentRepository;

    @InjectMocks CommentServiceImpl commentService;

    User user;
    Post post;
    CommentRequestDto requestDto;
    Comment comment;

    @BeforeEach
    void setUp(){
        user = User.builder().userUuid(UUID.randomUUID().toString()).build();
        post = Post.builder().postUuid(UUID.randomUUID().toString()).build();

        requestDto = new CommentRequestDto("kawaiiii");

        comment = new Comment(UUID.randomUUID().toString(),post,user, requestDto.getContent());
    }

    @Test
    void createCommentWithValidate() {

        //given
        when(userRepository.findByUserUuid(user.getUserUuid())).thenReturn(Optional.ofNullable(user));
        when(postRepository.findByPostUuid(post.getPostUuid())).thenReturn(Optional.ofNullable(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        //when
        CommentResponseDto result = commentService.createCommentWithValidate(user.getUserUuid(), post.getPostUuid(), requestDto);

        //then
        assertNotNull(result);
        assertEquals(comment.getContent(), result.getContent());
        assertEquals(comment.getCommentUuid(),result.getCommentUuid());

        verify(userRepository,times(1)).findByUserUuid(user.getUserUuid());
        verify(postRepository,times(1)).findByPostUuid(post.getPostUuid());
        verify(commentRepository,times(1)).save(any(Comment.class));

    }

    @Test
    void updateCommentWithValidate() {

        //given
        CommentUpdateDto updateComment = new CommentUpdateDto("kawai dog");
        when(postRepository.findByPostUuid(comment.getPost().getPostUuid())).thenReturn(Optional.ofNullable(post));
        when(commentRepository.findByUserUserUuidAndCommentUuid(user.getUserUuid(), comment.getCommentUuid())).thenReturn(Optional.ofNullable(comment));

        //when
        CommentResponseDto result = commentService.updateCommentWithValidate(user.getUserUuid(), comment.getCommentUuid(), comment.getPost().getPostUuid(), updateComment);

        //then
        assertNotNull(result);
        assertEquals(updateComment.getContent(), result.getContent());

        verify(commentRepository, times(1)).findByUserUserUuidAndCommentUuid(user.getUserUuid(), comment.getCommentUuid());

    }

    @Test
    void deleteComment() {
        //given
        when(postRepository.findByPostUuid(comment.getPost().getPostUuid())).thenReturn(Optional.ofNullable(post));
        when(commentRepository.findByUserUserUuidAndCommentUuid(user.getUserUuid(), comment.getCommentUuid())).thenReturn(Optional.ofNullable(comment));

        //when
        commentService.deleteComment(user.getUserUuid(), comment.getCommentUuid(), comment.getPost().getPostUuid());

        //


    }
}