package com.socialnetwork.microservice.repository;

import com.socialnetwork.microservice.entity.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MessagesRepository extends JpaRepository<MessagesEntity, String> {

    Optional<MessagesEntity> findById(@Param("id") Long id);
}
