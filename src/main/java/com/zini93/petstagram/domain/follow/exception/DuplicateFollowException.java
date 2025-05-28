package com.zini93.petstagram.domain.follow.exception;

public class DuplicateFollowException extends RuntimeException {
    public DuplicateFollowException(String message) {
        super(message);
    }
}
