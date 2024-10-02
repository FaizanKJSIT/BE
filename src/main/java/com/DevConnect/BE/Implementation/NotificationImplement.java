package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.NotificationDTO;
import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.Notification;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.AlreadyExistsException;
import com.DevConnect.BE.ExceptionH.ResourceNotFoundException;
import com.DevConnect.BE.Repo.NotificationRepo;
import com.DevConnect.BE.Service.NotificationService;
import com.DevConnect.BE.Utility.ModelMapperConfig;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationImplement implements NotificationService
{
    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    UserImplement userRepo;

    ModelMapper mapper;

    private Notification FindNotification(Integer id)
    { return notificationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", id.toString())); }
    private NotificationDTO SaveNotification(Notification noti)
    {
        notificationRepo.save(noti);
        return mapper.map(noti, NotificationDTO.class);
    }

    private ModelMapper NotificationConfig()
    {
        ModelMapperConfig mmc = new ModelMapperConfig();
        mapper = mmc.UserMapper();

        Converter<User, String> UserToUsername = ctx -> ctx.getSource() == null ? null : ctx.getSource().getUsername();
        mapper.typeMap(Notification.class, NotificationDTO.class).addMappings(mp->mp.using(UserToUsername).map(Notification::getReceiver, NotificationDTO::setReceiver));

        Converter<String, User> UsernameToUser = ctx -> ctx.getSource() == null ? null : userRepo.FindUser(ctx.getSource());
        mapper.typeMap(NotificationDTO.class, Notification.class).addMappings((mp->mp.using(UsernameToUser).map(NotificationDTO::getReceiver, Notification::setReceiver)));

        return mapper;
    }

    public NotificationImplement()
    { mapper = NotificationConfig(); }


    @Override
    public NotificationDTO AddNotification(NotificationDTO noti)
    {
        boolean notiCheck = noti.getId() == null || !notificationRepo.existsById(noti.getId());
        if(notiCheck)
            return SaveNotification(mapper.map(noti, Notification.class));
        else
            throw new AlreadyExistsException("Notification", noti.getId().toString());
    }

    @Override
    public NotificationDTO UpdateNotification(Integer id, NotificationDTO notiDTO)
    {
        Notification noti = FindNotification(notiDTO.getId());
        noti = mapper.map(notiDTO, Notification.class);
        return SaveNotification(noti);
    }

    @Override
    public NotificationDTO UpdateReceiver(Integer id, String username)
    {
        Notification noti = FindNotification(id);
        User user = userRepo.FindUser(username);
        noti.setReceiver(user);
        return SaveNotification(noti);
    }

    @Override
    public NotificationDTO UpdateMessage(Integer id, String message)
    {
        Notification noti = FindNotification(id);
        noti.setMessage(message);
        return SaveNotification(noti);
    }

    @Override
    public NotificationDTO UpdateData(Integer id, List<String> data)
    {
        Notification noti = FindNotification(id);
        noti.setData(data);
        return SaveNotification(noti);
    }

    @Override
    public NotificationDTO GetNotification(Integer id)
    {
        Notification noti = FindNotification(id);
        return mapper.map(noti, NotificationDTO.class);
    }

    @Override
    public UserDTO GetReceiver(Integer id)
    {
        Notification noti = FindNotification(id);
        User user = noti.getReceiver();
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<NotificationDTO> GetAllForReceiver(String username)
    {
        User user = userRepo.FindUser(username);
        List<Notification> nList = notificationRepo.findByReceiver(username);
        List<NotificationDTO> ndtoList = new ArrayList<>(nList.size());
        for(Notification n : nList)
            ndtoList.add(mapper.map(n, NotificationDTO.class));
        return ndtoList;
    }

    @Override
    public void DeleteNotification(Integer id)
    { notificationRepo.deleteById(id); }
}
