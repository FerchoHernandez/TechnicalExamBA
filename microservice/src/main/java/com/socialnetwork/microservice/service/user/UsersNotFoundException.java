package com.socialnetwork.microservice.service.user;

public class UsersNotFoundException extends RuntimeException {
  private final Long id;

  public UsersNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }

  public static UsersNotFoundException from(String message, Long id) {
    return new UsersNotFoundException(message, id);
  }

  public Long getId() {
    return id;
  }
}
