package com.DevConnect.BE.Repo;

import com.DevConnect.BE.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Integer>
{
    @Query(value = "select n from Notification n where n.receiver.username = ?1")
    List<Notification> findByReceiver(String username);
}
