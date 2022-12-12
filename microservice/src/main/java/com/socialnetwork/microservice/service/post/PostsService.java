package com.socialnetwork.microservice.service.post;

import com.socialnetwork.microservice.entity.PostsEntity;

public interface PostsService {

    PostsEntity savePost(PostsEntity newPost);

    PostsEntity searchPost(Long idPost);
}
