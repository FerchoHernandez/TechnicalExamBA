package com.socialnetwork.microservice.service.user;

import com.socialnetwork.microservice.entity.UsersEntity;

public interface UsersService {

    UsersEntity saveUser(UsersEntity newUser);

    UsersEntity searchUser(Long idUser);


}
