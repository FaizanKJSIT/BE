package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.NotificationDTO;
import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface NotificationService
{
    NotificationDTO AddNotification(NotificationDTO noti);
    NotificationDTO UpdateNotification(Long id, NotificationDTO noti);
    NotificationDTO UpdateReceiver(Long id, String username);
    NotificationDTO UpdateMessage(Long id, String message);
    NotificationDTO UpdateData(Long id, List<String> data);
    NotificationDTO GetNotification(Long id);
    UserDTO GetReceiver(Long id);
    List<NotificationDTO> GetAllForReceiver(String username);
    SimpleResponse DeleteNotification(Long id);
    void DeleteAllForUser(String username);
}
