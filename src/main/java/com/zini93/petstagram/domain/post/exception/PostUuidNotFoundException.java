package com.zini93.petstagram.domain.post.exception;

public class PostUuidNotFoundException extends RuntimeException {
    public PostUuidNotFoundException(String message) {
        super(message);
    }
}
