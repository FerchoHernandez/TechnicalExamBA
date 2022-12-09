package com.socialnetwork.microservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.socialnetwork.microservice.entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {

	Optional<UsersEntity> findById(@Param("id") Long id);
}
