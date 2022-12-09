package com.socialnetwork.notificationservice.repository;

import java.util.Optional;

import com.socialnetwork.notificationservice.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface NotificationsRepository extends JpaRepository<NotificationsEntity, String> {

	Optional<NotificationsEntity> findById(@Param("id") Long id);
}
