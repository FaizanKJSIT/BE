package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.*;
import com.DevConnect.BE.Repo.UserRepo;
import com.DevConnect.BE.Service.UserService;
import com.DevConnect.BE.Utility.ModelMapperConfig;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserImplement implements UserService
{
    @Autowired
    private UserRepo userRepo;

    private ModelMapper mapper = new ModelMapper();

    public UserImplement()
    {
        ModelMapperConfig mmc = new ModelMapperConfig();
        mapper = mmc.UserMapper();
    }

    public User FindUser(String username)
    { return userRepo.findById(username).orElseThrow(() -> new ResourceNotFoundException("User", "Username", username)); }

    private UserDTO SaveUser(User Updated_User) //needs testing probably doesnt work as intended
    {
        userRepo.save(Updated_User);
        return mapper.map(Updated_User, UserDTO.class);
    }

    public List<UserDTO> UserDTOListMapper(List<User> user_l)
    {
        List<UserDTO> userdto_l = new ArrayList<>(user_l.size());
        for(User u : user_l)
        { userdto_l.add(mapper.map(u, UserDTO.class)); }
        return userdto_l;
    }

    @Override
    public UserDTO AddUser(User newUser)
    {
        if(!userRepo.existsById(newUser.getUsername()))
            return SaveUser(newUser);
        else
            throw new AlreadyExistsException("User", newUser.getUsername());
    }

    @Override
    public UserDTO UpdateUser(UserDTO updatedUser, String username)
    {
        if(!updatedUser.getUsername().equals(username))
            throw new RuntimeException("User username and passed username must be equal");
        User user = FindUser(username);
        String pass = user.getPassword();
        user = mapper.map(updatedUser, User.class);
        user.setPassword(pass);

        return SaveUser(user);
    }

    @Override
    public UserDTO UpdateUsername(String username, String newUsername)
    {
        User user = FindUser(username); //Get user
        UserDTO userdt = mapper.map(user, UserDTO.class); //Copy details to data transfer object
        String Password = user.getPassword(); //Save password in variable
        userdt.setUsername(newUsername); //Set the new username in data transfer object
        DeleteUser(username); //Delete the user (note user should be referencing null)
        user = mapper.map(userdt, User.class); //Transfer the data back to user with new username
        user.setPassword(Password); //Transfer back the old password
        return SaveUser(user);
    }

    @Override
    public void UpdateUserPassword(String username, String password, String newPassword)
    {
        if(!Authenticate(username, password))
            throw new AuthenticateFailureException(username);
        User user = FindUser(username);
        user.setPassword(newPassword);
        SaveUser(user);
    }

    @Override
    public UserDTO AddEmailId(String username, String emailId)
    {
        User user = FindUser(username);
        List<String> AllEmails = user.getEmail_id();
        AllEmails.add(emailId);
        user.setEmail_id(AllEmails);
        return SaveUser(user);
    }

    @Override
    public UserDTO UpdateEmailId(String username, String oldEmailId, String newEmailId)
    {
        User user = FindUser(username);
        List<String> allEmails = user.getEmail_id();
        allEmails.set(allEmails.indexOf(oldEmailId), newEmailId);//Check for oldemailid not existing
        user.setEmail_id(allEmails);
        return SaveUser(user);
    }

    @Override
    public UserDTO DeleteEmailId(String username, String EmailId)
    {
        User user = FindUser(username);
        List<String> allEmails = user.getEmail_id();
        allEmails.remove(EmailId);
        user.setEmail_id(allEmails);
        return SaveUser(user);
    }

    @Override
    public UserDTO DeleteAllEmailId(String username)
    {
        User user = FindUser(username);
        user.setEmail_id(new ArrayList<>());
        return SaveUser(user);
    }

    @Override
    public UserDTO AddMobileNo(String username, String MobileNo)
    {
        User user = FindUser(username);
        List<String> AllMob = user.getMobile_no();
        AllMob.add(MobileNo);
        user.setMobile_no(AllMob);
        return SaveUser(user);
    }

    @Override
    public UserDTO UpdateMobileNo(String username, String oldMobileNo, String newMobileNo)
    {
        User user = FindUser(username);
        List<String> AllMob = user.getMobile_no();
        AllMob.set(AllMob.indexOf(oldMobileNo), newMobileNo);
        user.setMobile_no(AllMob);
        return SaveUser(user);
    }

    @Override
    public UserDTO DeleteMobileNo(String username, String MobileNo)
    {
        User user = FindUser(username);
        List<String> AllMob = user.getMobile_no();
        AllMob.remove(MobileNo);
        user.setMobile_no(AllMob);
        return SaveUser(user);
    }

    @Override
    public UserDTO DeleteAllMobileNo(String username)
    {
        User user = FindUser(username);
        user.setMobile_no(new ArrayList<>());
        return SaveUser(user);
    }

    @Override
    public UserDTO UpdateQualification(String username, String Qualification)
    {
        User user = FindUser(username);
        user.setQualification(Qualification);
        return SaveUser(user);
    }

    @Override
    public UserDTO AddInterest(String username, String interest)
    {
        User user = FindUser(username);
        List<String> Interest = user.getInterest();
        Interest.add(interest);
        user.setInterest(Interest);
        return SaveUser(user);
    }

    @Override
    public UserDTO GetUser(String username)
    {
        User user = FindUser(username);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> GetAllUsers()
    { return UserDTOListMapper(userRepo.findAll()); }

    @Override
    public UserDTO UpdateName(String username, String firstname, String middlename, String lastname)
    {
        User user = FindUser(username);
        if(!firstname.equals(""))
            user.setFirst_name(firstname);
        if(!middlename.equals(""))
            user.setMiddle_name(middlename);
        if(!lastname.equals(""))
            user.setLast_name(lastname);
        return SaveUser(user);
    }

    @Override
    public List<UserDTO> GetUserByName(String firstname)
    { return UserDTOListMapper(userRepo.findByfirstname(firstname)); }

    @Override
    public List<UserDTO> GetUserByName(String firstname, String middlename, String lastname)
    { return UserDTOListMapper(userRepo.findByfullname(firstname, middlename, lastname)); }

    @Override
    public List<UserDTO> GetUserByEmail(String email_id)
    { return UserDTOListMapper(userRepo.findByEmailId(email_id)); }

    @Override
    public SimpleResponse DeleteUser(String username)
    {
        FindUser(username);
        userRepo.deleteById(username);
        SimpleResponse response = new SimpleResponse("User with username: " + username + " deleted!", true);
        if(userRepo.existsById(username))
        {
            response.setMessage("Failed to delete user with username: " + username);
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public boolean Authenticate(String username, String password)
    {
        User user = FindUser(username);
        return user.getPassword().equals(password);
    }

    @Override
    public boolean IsUnique(String username)
    {
        if(username == null || userRepo.existsById(username))
            return false;
        return true;
    }
}
