package com.socialnetwork.microservice.service.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetwork.microservice.entity.UsersEntity;
import com.socialnetwork.microservice.repository.UsersRepository;

import java.util.Date;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger log = Logger.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersEntity saveUser(UsersEntity newUser) {
        newUser.setCreated_at(new Date());
        return usersRepository.save(newUser);
    }

    @Override
    public UsersEntity searchUser(Long idUser) {

        return usersRepository.findById(idUser)
                .orElseThrow(() -> new UsersNotFoundException("User not found:", idUser));
    }
}
