package com.socialnetwork.microservice.service.post;


import com.socialnetwork.microservice.entity.PostsEntity;
import com.socialnetwork.microservice.repository.PostsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostsServiceImpl implements PostsService {
    private static final Logger log = Logger.getLogger(PostsServiceImpl.class);
    @Autowired
    private PostsRepository postsRepository;

    @Override
    public PostsEntity savePost(PostsEntity newPost) {
        newPost.setCreated_at(new Date());
        PostsEntity postSaved = postsRepository.save(newPost);

        return postSaved;
    }

    @Override
    public PostsEntity searchPost(Long idPost) {

        return postsRepository.findById(idPost)
                .orElseThrow(() -> new PostsNotFoundException("Post not found:", idPost));
    }

    @Override
    public void deletePost(Long idPost) {

        PostsEntity currentPost = postsRepository.findById(idPost)
                .orElseThrow(() -> new PostsNotFoundException("Post not found:: ", idPost));

        postsRepository.delete(currentPost);
    }
}
