package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.NotificationDTO;
import com.DevConnect.BE.DataTransfer.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService
{
    NotificationDTO AddNotification(NotificationDTO noti);
    NotificationDTO UpdateNotification(Integer id, NotificationDTO noti);
    NotificationDTO UpdateReceiver(Integer id, String username);
    NotificationDTO UpdateMessage(Integer id, String message);
    NotificationDTO UpdateData(Integer id, List<String> data);
    NotificationDTO GetNotification(Integer id);
    UserDTO GetReceiver(Integer id);
    List<NotificationDTO> GetAllForReceiver(String username);
    void DeleteNotification(Integer id);
}
