package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.UserDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
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

    UserDTO AddMobileNo(String username, long MobileNo);
    UserDTO ReplaceMobileNo(String username, long oldMobileNo, long newMobileNo);
    UserDTO RemoveMobileNo(String username, long MobileNo);
    UserDTO RemoveAllMobileNo(String username);

    UserDTO UpdateQualifications(String username, String Qualification);

    UserDTO UpdateDomainInterest(String username, String domain);

    UserDTO getUser(String username);
    List<UserDTO> getAllUsers();

    List<String> getUserbyName(String firstname);
    List<String> getUserbyName(String firstname, String middlename, String lastname);

    List<String> getUserbyEmail(String email_id);

    void DeleteUser(String username);
}
