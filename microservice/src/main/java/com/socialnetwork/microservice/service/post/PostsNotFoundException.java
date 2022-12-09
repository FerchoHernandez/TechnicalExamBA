package com.socialnetwork.microservice.service.post;


public class PostsNotFoundException extends RuntimeException {
    private final Long id;

    public PostsNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public static com.socialnetwork.microservice.service.post.PostsNotFoundException from(String message, Long id) {
        return new com.socialnetwork.microservice.service.post.PostsNotFoundException(message, id);
    }

    public Long getId() {
        return id;
    }
}
