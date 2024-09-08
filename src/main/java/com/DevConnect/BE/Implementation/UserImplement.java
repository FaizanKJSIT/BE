package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.*;
import com.DevConnect.BE.Repo.UserRepo;
import com.DevConnect.BE.Service.UserService;
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

    private User FindUser(String username)
    { return userRepo.findById(username).orElseThrow(() -> new ResourceNotFoundException("User", "Username", username)); }

    private UserDTO SaveUser(UserDTO Updated_User) //needs testing probably doesnt work as intended
    {
        User user = mapper.map(Updated_User, User.class);
        userRepo.save(user);
        return mapper.map(user, UserDTO.class);
    }

    private List<UserDTO> UserDTOListMapper(List<User> user_l)
    {
        List<UserDTO> userdto_l = new ArrayList<>(user_l.size());
        for(User u : user_l)
        { userdto_l.add(mapper.map(u, UserDTO.class)); }
        return userdto_l;
    }

    @Override
    public UserDTO CreateUser(UserDTO newUser)
    {
        return SaveUser(newUser);
    }

    @Override
    public UserDTO UpdateUser(UserDTO Updated_User, String username)
    {
        User user = FindUser(username);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO UpdateUsername(String username, String newUsername)
    {
        User user = FindUser(username);
        UserDTO userdt = mapper.map(user, UserDTO.class);
        userdt.setUsername(newUsername);//check for newusername not being unique later
        DeleteUser(username);
        return CreateUser(userdt);
    }

    @Override
    public UserDTO UpdateUserPassword(String username, String password)
    {
        User user = FindUser(username);
        user.setPassword(password);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO AddEmailId(String username, String emailid)
    {
        User user = FindUser(username);
        List<String> AllEmails = user.getEmail_id();
        AllEmails.add(emailid);
        user.setEmail_id(AllEmails);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO ReplaceEmailId(String username, String oldEmailId, String newEmailId)
    {
        User user = FindUser(username);
        List<String> allEmails = user.getEmail_id();
        allEmails.set(allEmails.indexOf(oldEmailId), newEmailId);//Check for oldemailid not existing
        user.setEmail_id(allEmails);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO RemoveEmailId(String username, String EmailId)
    {
        User user = FindUser(username);
        List<String> allEmails = user.getEmail_id();
        allEmails.remove(EmailId);
        user.setEmail_id(allEmails);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO RemoveAllEmailId(String username)
    {
        User user = FindUser(username);
        user.setEmail_id(new ArrayList<>());
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO AddMobileNo(String username, long MobileNo)
    {
        User user = FindUser(username);
        List<Long> AllMob = user.getMobile_no();
        AllMob.add(MobileNo);
        user.setMobile_no(AllMob);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO ReplaceMobileNo(String username, long oldMobileNo, long newMobileNo)
    {
        User user = FindUser(username);
        List<Long> AllMob = user.getMobile_no();
        AllMob.set(AllMob.indexOf(oldMobileNo), newMobileNo);
        user.setMobile_no(AllMob);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO RemoveMobileNo(String username, long MobileNo)
    {
        User user = FindUser(username);
        List<Long> AllMob = user.getMobile_no();
        AllMob.remove(MobileNo);
        user.setMobile_no(AllMob);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO RemoveAllMobileNo(String username)
    {
        User user = FindUser(username);
        user.setMobile_no(new ArrayList<>());
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO UpdateQualifications(String username, String Qualification)
    {
        User user = FindUser(username);
        user.setQualifications(Qualification);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO UpdateDomainInterest(String username, String domain)
    {
        User user = FindUser(username);
        user.setDomain_interest(domain);
        return SaveUser(mapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO getUser(String username)
    {
        User user = FindUser(username);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers()
    { return UserDTOListMapper(userRepo.findAll()); }

    @Override
    public List<String> getUserbyName(String firstname)
    { return userRepo.findByfirstname(firstname); }

    @Override
    public List<String> getUserbyName(String firstname, String middlename, String lastname)
    { return userRepo.findByfullname(firstname, middlename, lastname); }

    @Override
    public List<String> getUserbyEmail(String email_id)
    { return userRepo.findByEmailId(email_id); }

    @Override
    public void DeleteUser(String username)
    { userRepo.deleteById(username); }
}
