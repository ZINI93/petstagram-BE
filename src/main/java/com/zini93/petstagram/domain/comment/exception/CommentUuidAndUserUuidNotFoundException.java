package com.zini93.petstagram.domain.comment.exception;

public class CommentUuidAndUserUuidNotFoundException extends RuntimeException {
    public CommentUuidAndUserUuidNotFoundException(String message) {
        super(message);
    }
}
