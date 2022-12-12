package com.socialnetwork.consumerservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialnetwork.consumerservice.dto.DataMessage;
import com.socialnetwork.consumerservice.entity.BackupsNotifications;
import com.socialnetwork.consumerservice.repository.BackupsNotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.Date;

@Service

public class ConsumeMessageService {
    private static final Logger log = Logger.getLogger(ConsumeMessageService.class);

    @Autowired
    private BackupsNotificationsRepository backupsNotificationsRepository;

    public void consumeMessage(@Payload String message) {
        log.info("===>RECEIVED [" + message + "]");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            DataMessage dataMessage = objectMapper.readValue(message, DataMessage.class);
            log.info("TRY SAVE BACKUP NOTIFICATION");
            BackupsNotifications backupNotification = new BackupsNotifications();
            backupNotification.setType(dataMessage.getType());
            backupNotification.setData(dataMessage.getData());
            backupNotification.setCreated_at(new Date());

            saveBakcupNotification(backupNotification);
        } catch (Exception e) {
            log.error("===>EXCEPTION [" + e.getMessage() + "]");
        }
    }

    private void saveBakcupNotification(BackupsNotifications backupNotification) {
        backupNotification.setCreated_at(new Date());
        backupsNotificationsRepository.save(backupNotification);
        log.info("BACKUP NOTIFICATION SAVE SUCCESS");
    }
}
