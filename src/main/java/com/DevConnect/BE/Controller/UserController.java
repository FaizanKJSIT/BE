package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/User")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/Add/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO newUser)
    { return new ResponseEntity<>(userService.CreateUser(newUser), HttpStatus.CREATED); }
}
