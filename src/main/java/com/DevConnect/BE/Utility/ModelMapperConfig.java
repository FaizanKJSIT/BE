package com.DevConnect.BE.Utility;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.Implementation.UserImplement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ModelMapperConfig
{
    @Autowired
    UserImplement userRepo;

    ModelMapper mapper;

    public ModelMapperConfig()
    {
        mapper = new ModelMapper();
    }

    public ModelMapper UserMapper()
    {
        mapper.typeMap(UserDTO.class, User.class).addMappings(mp ->  mp.skip(User::setPassword));
        return mapper;
    }
}
