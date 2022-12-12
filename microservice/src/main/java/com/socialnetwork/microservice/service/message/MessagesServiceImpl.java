package com.socialnetwork.microservice.service.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.socialnetwork.microservice.entity.MessagesEntity;
import com.socialnetwork.microservice.model.NotificationDto;
import com.socialnetwork.microservice.rabbitmq.Publisher;
import com.socialnetwork.microservice.rabbitmq.dto.DataMessage;
import com.socialnetwork.microservice.remote.NotificationsRemoteClient;
import com.socialnetwork.microservice.repository.MessagesRepository;
import com.socialnetwork.microservice.service.post.PostsNotFoundException;
import com.socialnetwork.microservice.service.post.PostsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class MessagesServiceImpl implements MessagesService {

    private static final Logger log = Logger.getLogger(PostsServiceImpl.class);
    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private NotificationsRemoteClient notificationsRemoteClient;

    @Autowired
    private Publisher publisher;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackToNotificationService")
    public MessagesEntity saveMessage(MessagesEntity newMessage) {

        newMessage.setCreated_at(new Date());
        MessagesEntity messageSaved = messagesRepository.save(newMessage);
        if (Objects.nonNull(newMessage)) {
            NotificationDto newNotification = new NotificationDto();
            newNotification.setTypeId(2);
            newNotification.setRefId(newMessage.getUserId());
            newNotification.setReceptorId(newMessage.getUserId());
            newNotification.setSenderId(newMessage.getUserId());
            newNotification.setReaded(false);
            notificationsRemoteClient.createNotification(newNotification);
        }

        return messageSaved;
    }

    private MessagesEntity fallbackToNotificationService(MessagesEntity newMessage) {
        log.error("====>NOTIFICATION COULD NOT BE GENERATED, SENDING DATA TO PUBLISHR RABBITMQ");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            DataMessage data = new DataMessage();
            data.setType("Post");
            data.setData(newMessage.toString());

            String objectString = objectMapper.writeValueAsString(data);
            publisher.send(objectString);
        } catch (Exception e) {
            log.error("====>BACKUP MESSAGE NOT SENDING FOR:" + e.getMessage());
        }
        return newMessage;
    }

    @Override
    public MessagesEntity searchMessage(Long idMessage) {
        return messagesRepository.findById(idMessage)
                .orElseThrow(() -> new PostsNotFoundException("Message not found:", idMessage));
    }
}
