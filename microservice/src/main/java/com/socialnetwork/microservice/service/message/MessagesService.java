package com.socialnetwork.microservice.service.message;

import com.socialnetwork.microservice.entity.MessagesEntity;

public interface MessagesService {

    MessagesEntity saveMessage(MessagesEntity newMessage);

    MessagesEntity searchMessage(Long idMessage);
}
