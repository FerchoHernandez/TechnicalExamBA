package com.socialnetwork.microservice.service.message;

public class MessagesNotFoundException extends RuntimeException {
    private final Long id;

    public MessagesNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public static com.socialnetwork.microservice.service.message.MessagesNotFoundException from(String message, Long id) {
        return new com.socialnetwork.microservice.service.message.MessagesNotFoundException(message, id);
    }

    public Long getId() {
        return id;
    }
}
