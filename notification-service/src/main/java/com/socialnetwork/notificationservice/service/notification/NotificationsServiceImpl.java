package com.socialnetwork.notificationservice.service.notification;

import com.socialnetwork.notificationservice.entity.NotificationsEntity;
import com.socialnetwork.notificationservice.repository.NotificationsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    private static final Logger log = Logger.getLogger(com.socialnetwork.notificationservice.service.notification.NotificationsServiceImpl.class);
    @Autowired
    private NotificationsRepository notificationsRepository;

    @Override
    public NotificationsEntity saveNotification(NotificationsEntity newNotification) {
        newNotification.setCreated_at(new Date());
        return notificationsRepository.save(newNotification);
    }

    @Override
    public NotificationsEntity searchNotification(Long idNotification) {

        return notificationsRepository.findById(idNotification)
                .orElseThrow(() -> new NotificationsNotFoundException("Notification not found:", idNotification));
    }

    @Override
    public void deleteNotification(Long idNotification) {

        NotificationsEntity notification = notificationsRepository.findById(idNotification)
                .orElseThrow(() -> new NotificationsNotFoundException("Notification not found:: ", idNotification));

        notificationsRepository.delete(notification);
    }

}
