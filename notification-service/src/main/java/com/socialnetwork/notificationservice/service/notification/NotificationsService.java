package com.socialnetwork.notificationservice.service.notification;

import com.socialnetwork.notificationservice.entity.NotificationsEntity;

public interface NotificationsService {

	NotificationsEntity saveNotification(NotificationsEntity newNotification);

	NotificationsEntity searchNotification(Long idNotification);

	void deleteNotification(Long idNotification);

}
