package com.DevConnect.BE.Utility;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig
{
    public ModelMapper UserMapper()
    {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(UserDTO.class, User.class).addMappings(mp -> { mp.skip(User::setPassword); });
        return mapper;
    }
}
