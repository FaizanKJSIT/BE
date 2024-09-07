package com.DevConnect.BE.Service;


import com.DevConnect.BE.DataTransfer.UserDTO;
import java.util.List;

public interface UserService
{
    UserDTO CreateUser(UserDTO newUser);
    UserDTO UpdateUser(UserDTO Updated_User, String username);

    UserDTO UpdateUsername(String username, String newUsername);
    UserDTO UpdateUserPassword(String username, String password);

    UserDTO AddEmailId(String username, String emailid);
    UserDTO ReplaceEmailId(String username, String oldEmailId, String newEmailId);
    UserDTO RemoveEmailId(String username, String EmailId);
    UserDTO RemoveAllEmailId(String username);

    UserDTO AddMobileNo(String username, int MobileNo);
    UserDTO ReplaceMobileNo(String username, int oldMobileNo, int newMobileNo);
    UserDTO RemoveMobileNo(String username, int MobileNo);
    UserDTO RemoveAllMobileNo(String username);

    UserDTO UpdateQualifications(String username, String Qualification);

    UserDTO UpdateDomainInterest(String username, String domain);

    UserDTO getUser(String username);
    List<UserDTO> getAllUsers();

    List<UserDTO> getUserbyName(String firstname);
    List<UserDTO> getUserbyName(String firstname, String middlename, String lastname);

    List<UserDTO> getUserbyEmail(String email_id);

    void DeleteUser(String username);
}
