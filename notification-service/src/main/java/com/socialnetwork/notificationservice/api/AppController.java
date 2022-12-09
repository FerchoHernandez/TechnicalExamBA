package com.socialnetwork.notificationservice.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.socialnetwork.notificationservice.entity.NotificationsEntity;
import com.socialnetwork.notificationservice.service.notification.NotificationsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notifcations")
public class AppController {

    private static final Logger log = Logger.getLogger(AppController.class);
    @Autowired
    private NotificationsServiceImpl notificationsService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public NotificationsEntity leer(@PathVariable("id") Long idNotification) {
        log.info("Search a Notification by ID");
        return notificationsService.searchNotification(idNotification);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public NotificationsEntity crear(@RequestBody NotificationsEntity newNotification) {
        log.info("Create new Notification");
        return notificationsService.saveNotification(newNotification);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void borrar(@PathVariable("id") Long idNotification) {
        log.info("Delete a notification by ID");
        notificationsService.deleteNotification(idNotification);
    }

}
