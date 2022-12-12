package com.socialnetwork.consumerservice.repository;

import com.socialnetwork.consumerservice.entity.BackupsNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BackupsNotificationsRepository extends JpaRepository<BackupsNotifications, String> {

    Optional<BackupsNotifications> findById(@Param("id") Long id);
}
