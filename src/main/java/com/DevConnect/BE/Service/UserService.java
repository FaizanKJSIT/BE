package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService
{
    UserDTO AddUser(User newUser);
    UserDTO UpdateUser(UserDTO updatedUser, String username);
    UserDTO GetUser(String username);
    List<UserDTO> GetAllUsers();
    void DeleteUser(String username);

    UserDTO UpdateUsername(String username, String newUsername);
    void UpdateUserPassword(String username, String password);

    UserDTO AddEmailId(String username, String emailId);
    UserDTO UpdateEmailId(String username, String oldEmailId, String newEmailId);
    List<UserDTO> GetUserByEmail(String email_id);
    UserDTO DeleteEmailId(String username, String EmailId);
    UserDTO DeleteAllEmailId(String username);

    UserDTO AddMobileNo(String username, long MobileNo);
    UserDTO UpdateMobileNo(String username, long oldMobileNo, long newMobileNo);
    UserDTO DeleteMobileNo(String username, long MobileNo);
    UserDTO DeleteAllMobileNo(String username);

    UserDTO UpdateQualifications(String username, String Qualification);

    UserDTO AddInterest(String username, String interest);

    List<UserDTO> GetUserByName(String firstname);
    List<UserDTO> GetUserByName(String firstname, String middlename, String lastname);
}
