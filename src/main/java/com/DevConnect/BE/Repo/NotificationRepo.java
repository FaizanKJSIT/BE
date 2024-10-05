package com.DevConnect.BE.Repo;

import com.DevConnect.BE.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long>
{
    @Query(value = "select n from Notification n where n.receiver.username = ?1")
    List<Notification> findByReceiver(String username);
}
