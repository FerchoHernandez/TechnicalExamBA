package com.socialnetwork.microservice.repository;

import com.socialnetwork.microservice.entity.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PostsRepository extends JpaRepository<PostsEntity, String> {

    Optional<PostsEntity> findById(@Param("id") Long id);
}