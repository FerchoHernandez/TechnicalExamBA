package com.socialnetwork.microservice.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.socialnetwork.microservice.entity.MessagesEntity;
import com.socialnetwork.microservice.entity.PostsEntity;
import com.socialnetwork.microservice.model.NotificationDto;
import com.socialnetwork.microservice.remote.NotificationsRemoteClient;
import com.socialnetwork.microservice.service.message.MessagesServiceImpl;
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
@RequestMapping("microservice-sn/api")
public class AppController {

    private static final Logger log = Logger.getLogger(AppController.class);
    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private PostsServiceImpl postsService;

    @Autowired
    private MessagesServiceImpl messagesService;

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    public UsersEntity createUser(@RequestBody UsersEntity newUser) {
        log.info("Create new User");
        return usersService.saveUser(newUser);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(OK)
    public UsersEntity getUserById(@PathVariable("id") Long idUser) {
        log.info("Search a User by ID");
        return usersService.searchUser(idUser);
    }

    @PostMapping("/posts")
    @ResponseStatus(CREATED)
    public PostsEntity createPost(@RequestBody PostsEntity newPost) {
        log.info("Create new Post");
        return postsService.savePost(newPost);
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(OK)
    public PostsEntity getPostById(@PathVariable("id") Long idPost) {
        log.info("Search a User by ID");
        return postsService.searchPost(idPost);
    }

    @PostMapping("/messages")
    @ResponseStatus(CREATED)
    public MessagesEntity createMessages(@RequestBody MessagesEntity newMessage) {
        log.info("Create new Menssage");
        return messagesService.saveMessage(newMessage);
    }

    @GetMapping("/messages/{id}")
    @ResponseStatus(OK)
    public MessagesEntity getMessageById(@PathVariable("id") Long idMessage) {
        log.info("Search a Message by ID");
        return messagesService.searchMessage(idMessage);
    }

}
