package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.NotificationDTO;
import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Service.NotificationService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Notification/")
public class NotificationController
{
    @Autowired
    NotificationService notificationService;

    @PostMapping("Add/")
    public ResponseEntity<NotificationDTO> addNotification(@RequestBody NotificationDTO ndto)
    { return new ResponseEntity<>(notificationService.AddNotification(ndto), HttpStatus.CREATED); }

    @PutMapping("{id}/")
    public  ResponseEntity<NotificationDTO> updateNotification(@PathVariable Long id, @RequestBody NotificationDTO ndto)
    { return new ResponseEntity<>(notificationService.UpdateNotification(id, ndto), HttpStatus.OK); }

    @PutMapping("{id}/Receiver/")
    public ResponseEntity<NotificationDTO> updateReceiver(@PathVariable Long id, @RequestParam(name = "NewReceiver") String newReceiver)
    { return new ResponseEntity<>(notificationService.UpdateReceiver(id, newReceiver), HttpStatus.OK); }

    @PutMapping("{id}/Message/")
    public ResponseEntity<NotificationDTO> updateMessage(@PathVariable Long id, @RequestParam(name = "NewMessage") String newMessage)
    { return new ResponseEntity<>(notificationService.UpdateMessage(id, newMessage), HttpStatus.OK); }

    @PutMapping("{id}/Data/")
    public ResponseEntity<NotificationDTO> updateData(@PathVariable Long id, @RequestParam(name = "NewData") List<String> newData)
    { return new ResponseEntity<>(notificationService.UpdateData(id, newData), HttpStatus.OK); }

    @GetMapping("{id}/")
    public ResponseEntity<NotificationDTO> getNotification(@PathVariable Long id)
    { return new ResponseEntity<>(notificationService.GetNotification(id), HttpStatus.OK); }

    @GetMapping("{id}/Receiver/")
    public ResponseEntity<UserDTO> getReceiver(@PathVariable Long id)
    { return new ResponseEntity<>(notificationService.GetReceiver(id), HttpStatus.OK); }

    @GetMapping("All/")
    public ResponseEntity<List<NotificationDTO>> getAllForReceiver(@RequestParam(name = "Receiver") String receiver)
    { return new ResponseEntity<>(notificationService.GetAllForReceiver(receiver), HttpStatus.OK); }

    @DeleteMapping("{id}/")
    public ResponseEntity<SimpleResponse> deleteNotification(@PathVariable Long id)
    { return new ResponseEntity<>(notificationService.DeleteNotification(id), HttpStatus.OK); }

    @DeleteMapping("All/")
    public ResponseEntity<SimpleResponse> deleteAllForReceiver(@RequestParam(name = "Receiver") String receiver)
    {
        notificationService.DeleteAllForUser(receiver);
        return new ResponseEntity<>(new SimpleResponse("All notifications for " + receiver + " deleted!", true), HttpStatus.OK);
    }
}
