package com.socialnetwork.notificationservice.service.notification;

public class NotificationsNotFoundException extends RuntimeException {
  private final Long id;

  public NotificationsNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }

  public static com.socialnetwork.notificationservice.service.notification.NotificationsNotFoundException from(String message, Long id) {
    return new com.socialnetwork.notificationservice.service.notification.NotificationsNotFoundException(message, id);
  }

  public Long getId() {
    return id;
  }
}
