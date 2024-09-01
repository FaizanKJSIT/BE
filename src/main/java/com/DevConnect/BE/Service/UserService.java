package com.DevConnect.BE.Service;


import com.DevConnect.BE.Entity.User;

public interface UserService
{
    User createUser(User newUser);
    void UpdateUsername(User user, String NewUsername);
    void UpdatePassword(User user, String NewPassword);
}
