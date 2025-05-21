package com.zini93.petstagram.domain.post.exception;

public class UserUuidAndPostUuidNotFoundException extends RuntimeException {
  public UserUuidAndPostUuidNotFoundException(String message) {
    super(message);
  }
}
