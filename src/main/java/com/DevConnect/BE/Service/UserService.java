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
    void UpdateUserPassword(String username, String password, String newPassword);

    UserDTO AddEmailId(String username, String emailId);
    UserDTO UpdateEmailId(String username, String oldEmailId, String newEmailId);
    List<UserDTO> GetUserByEmail(String email_id);
    UserDTO DeleteEmailId(String username, String EmailId);
    UserDTO DeleteAllEmailId(String username);

    UserDTO AddMobileNo(String username, String MobileNo);
    UserDTO UpdateMobileNo(String username, String oldMobileNo, String newMobileNo);
    UserDTO DeleteMobileNo(String username, String MobileNo);
    UserDTO DeleteAllMobileNo(String username);

    UserDTO UpdateQualification(String username, String Qualification);

    UserDTO AddInterest(String username, String interest);

    UserDTO UpdateName(String username, String firstname, String middlename, String lastname);
    List<UserDTO> GetUserByName(String firstname);
    List<UserDTO> GetUserByName(String firstname, String middlename, String lastname);
    boolean Authenticate(String username, String password);
    boolean IsUnique(String username);
}
