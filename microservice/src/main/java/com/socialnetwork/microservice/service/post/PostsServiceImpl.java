package com.socialnetwork.microservice.service.post;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.socialnetwork.microservice.entity.PostsEntity;
import com.socialnetwork.microservice.model.NotificationDto;
import com.socialnetwork.microservice.rabbitmq.Publisher;
import com.socialnetwork.microservice.rabbitmq.dto.DataMessage;
import com.socialnetwork.microservice.remote.NotificationsRemoteClient;
import com.socialnetwork.microservice.repository.PostsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class PostsServiceImpl implements PostsService {
    private static final Logger log = Logger.getLogger(PostsServiceImpl.class);
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private NotificationsRemoteClient notificationsRemoteClient;

    @Autowired
    private Publisher publisher;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackToNotificationService")
    public PostsEntity savePost(PostsEntity newPost) {
        newPost.setCreated_at(new Date());
        PostsEntity postSaved = postsRepository.save(newPost);
        if (Objects.nonNull(postSaved)) {
            NotificationDto newNotification = new NotificationDto();
            newNotification.setTypeId(1);
            newNotification.setRefId(newPost.getAuthorRefId());
            newNotification.setReceptorId(newPost.getReceptorTypeId());
            newNotification.setSenderId(newPost.getAuthorRefId());
            newNotification.setReaded(false);
            notificationsRemoteClient.createNotification(newNotification);
        }

        return postSaved;
    }

    @Override
    public PostsEntity searchPost(Long idPost) {

        return postsRepository.findById(idPost)
                .orElseThrow(() -> new PostsNotFoundException("Post not found:", idPost));
    }

    private PostsEntity fallbackToNotificationService(PostsEntity newPost) {
        log.error("====>NOTIFICATION COULD NOT BE GENERATED, SENDING DATA TO PUBLISHR RABBITMQ");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            DataMessage data = new DataMessage();
            data.setType("Post");
            data.setData(newPost.toString());

            String objectString = objectMapper.writeValueAsString(data);
            publisher.send(objectString);
        } catch (Exception e) {
            log.error("====>BACKUP MESSAGE NOT SENDING FOR:" + e.getMessage());
        }
        return newPost;
    }
}
