package com.socialnetwork.microservice.remote;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.socialnetwork.microservice.model.NotificationDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "notification-service-social-network")
public interface NotificationsRemoteClient {

    @RequestMapping(method = POST, value = "notification-sn/api/notifcations", produces = "application/json")
    NotificationDto createNotification(@RequestBody NotificationDto newNotification);
}
