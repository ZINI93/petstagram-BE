package com.zini93.petstagram.domain.hashtag.exception;

public class HashtagNotFoundException extends RuntimeException {
  public HashtagNotFoundException(String message) {
    super(message);
  }
}
