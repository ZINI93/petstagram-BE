package com.zini93.petstagram.domain.like.exception;

public class UserUuidAndLikeUuidNotFoundException extends RuntimeException {
    public UserUuidAndLikeUuidNotFoundException(String message) {
        super(message);
    }
}
