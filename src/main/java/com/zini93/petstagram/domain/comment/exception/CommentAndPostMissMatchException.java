package com.zini93.petstagram.domain.comment.exception;

public class CommentAndPostMissMatchException extends RuntimeException {
    public CommentAndPostMissMatchException(String message) {
        super(message);
    }
}
