package com.socialnetwork.microservice.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.socialnetwork.microservice.entity.PostsEntity;
import com.socialnetwork.microservice.model.NotificationDto;
import com.socialnetwork.microservice.remote.NotificationsRemoteClient;
import com.socialnetwork.microservice.service.post.PostsServiceImpl;
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

import com.socialnetwork.microservice.entity.UsersEntity;
import com.socialnetwork.microservice.service.user.UsersServiceImpl;

import java.util.Objects;

@RestController
@RequestMapping("api")
public class AppController {

    private static final Logger log = Logger.getLogger(AppController.class);
    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private PostsServiceImpl postsService;

    @Autowired
    private NotificationsRemoteClient notificationsRemoteClient;

    @GetMapping("/users/{id}")
    @ResponseStatus(OK)
    public UsersEntity leer(@PathVariable("id") Long idUser) {
        log.info("Search a User by ID");
        return usersService.searchUser(idUser);
    }

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    public UsersEntity crear(@RequestBody UsersEntity newUser) {
        log.info("Create new User");
        return usersService.saveUser(newUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(NO_CONTENT)
    public void borrar(@PathVariable("id") Long idUser) {
        log.info("Delete a user by ID");
        usersService.deleteUser(idUser);
    }

    @PostMapping("/posts")
    @ResponseStatus(CREATED)
    @HystrixCommand(fallbackMethod = "fallbackToNotificationService")
    public PostsEntity crearPost(@RequestBody PostsEntity newPost) {
        log.info("Create new Post");
        PostsEntity postCreated = postsService.savePost(newPost);

        if(Objects.nonNull(postCreated)){
            NotificationDto newNotification = new NotificationDto();
            newNotification.setTypeId(newPost.getPostTypeId());
            newNotification.setRefId(newPost.getAuthorRefId());
            newNotification.setReceptorId(newPost.getReceptorTypeId());
            newNotification.setSenderId(newPost.getAuthorRefId());
            newNotification.setReaded(false);
            notificationsRemoteClient.createNotification(newNotification);
        }
        return postCreated;
    }

    private PostsEntity fallbackToNotificationService(PostsEntity newPost){
        log.error("Notification could not be generated, sending data for revision");
        return newPost;
    }

}
