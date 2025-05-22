package com.zini93.petstagram.domain.comment.service;

import com.zini93.petstagram.domain.comment.dto.CommentRequestDto;
import com.zini93.petstagram.domain.comment.dto.CommentResponseDto;
import com.zini93.petstagram.domain.comment.dto.CommentUpdateDto;
import com.zini93.petstagram.domain.comment.entity.Comment;
import com.zini93.petstagram.domain.comment.exception.CommentAndPostMissMatchException;
import com.zini93.petstagram.domain.comment.exception.CommentUuidAndUserUuidNotFoundException;
import com.zini93.petstagram.domain.comment.repository.CommentRepository;
import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.post.exception.PostUuidNotFoundException;
import com.zini93.petstagram.domain.post.repository.PostRepository;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserUuidNotFoundException;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentServiceImpl implements CommentService{

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private Comment createComment(User user, Post post, CommentRequestDto requestDto){
        return Comment.builder()
                .user(user)
                .post(post)
                .content(requestDto.getContent())
                .build();
    }

    @Override @Transactional
    public CommentResponseDto createCommentWithValidate(String userUuid, String postUuid, CommentRequestDto requestDto) {

        User user = findUserByUserUuidInUser(userUuid);
        Post post = findPostByPostUuidInPost(postUuid);

        Comment comment = createComment(user, post, requestDto);
        Comment savedComment = commentRepository.save(comment);

        return savedComment.toResponse();
    }

    private void updateComment(CommentUpdateDto updateDto){
        new CommentUpdateDto(updateDto.getContent());
    }

    @Override @Transactional
    public CommentResponseDto updateCommentWithValidate(String userUuid, String commentUuid, String postUuid , CommentUpdateDto updateDto) {

        Post post = findPostByPostUuidInPost(postUuid);
        Comment comment = findCommentByUserUuidAndCommentUuidInComment(userUuid, commentUuid);
        validateCommentAndPostMatch(post, comment);
        updateComment(updateDto);

        return comment.toResponse();
    }

    @Override @Transactional
    public void deleteComment(String userUuid, String commentUuid, String postUuid) {
        Post post = findPostByPostUuidInPost(postUuid);
        Comment comment = findCommentByUserUuidAndCommentUuidInComment(userUuid, commentUuid);
        validateCommentAndPostMatch(post, comment);

        commentRepository.delete(comment);
    }

    private Comment findCommentByUserUuidAndCommentUuidInComment(String userUuid, String commentUuid) {
        return commentRepository.findByUserUserUuidAndCommentUuid(userUuid, commentUuid)
                .orElseThrow(() -> new CommentUuidAndUserUuidNotFoundException("Not found comment"));
    }

    private Post findPostByPostUuidInPost(String postUuid) {
        return postRepository.findByPostUuid(postUuid)
                .orElseThrow(() -> new PostUuidNotFoundException("Post not found"));
    }

    private User findUserByUserUuidInUser(String userUuid) {
        return userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new UserUuidNotFoundException("User not found"));
    }

    private static void validateCommentAndPostMatch(Post post, Comment comment) {
        if (post.getPostId() != comment.getPost().getPostId()){
            throw new CommentAndPostMissMatchException("Post and comment miss match");
        }
    }
}
