package com.zini93.petstagram.domain.user.exception;

public class UserUuidNotFoundException extends RuntimeException {
    public UserUuidNotFoundException(String message) {
        super(message);
    }
}
