package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.AuthenticateFailureException;
import com.DevConnect.BE.Service.UserService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/User/")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("Add/")
    public ResponseEntity<UserDTO> addUser(@RequestBody User newUser)
    { return new ResponseEntity<>(userService.AddUser(newUser), HttpStatus.CREATED); }

    @PostMapping("{username}/EmailId/")
    public ResponseEntity<UserDTO> addEmailId(@PathVariable String username, @RequestParam(name = "NewEmailId") String email)
    { return new ResponseEntity<>(userService.AddEmailId(username, email), HttpStatus.CREATED); }

    @PostMapping("{username}/MobileNo/")
    public ResponseEntity<UserDTO> addMobileNo(@PathVariable String username, @RequestParam(name = "NewMobileNo") String mobno)
    { return new ResponseEntity<>(userService.AddMobileNo(username, mobno), HttpStatus.CREATED); }

    @PostMapping("{username}/Interest/")
    public ResponseEntity<UserDTO> addInterest(@PathVariable String username, @RequestParam(name = "NewInterest") String interest)
    { return new ResponseEntity<>(userService.AddInterest(username, interest), HttpStatus.CREATED); }

    @PutMapping("{username}/")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO updatedUser)
    { return new ResponseEntity<>(userService.UpdateUser(updatedUser, username), HttpStatus.OK); }

    @PutMapping("{username}/Username/")
    public ResponseEntity<UserDTO> updateUsername(@RequestParam(name = "NewUsername") String newUsername, @PathVariable String username)
    { return new ResponseEntity<>(userService.UpdateUsername(username, newUsername), HttpStatus.OK); }

    @PutMapping("{username}/Password/")
    public ResponseEntity<SimpleResponse> updatePassword(@PathVariable String username, @RequestParam(name = "OldPassword") String password, @RequestParam(name = "NewPassword") String newpassword)
    {
        userService.UpdateUserPassword(username, password, newpassword);
        return new ResponseEntity<>(new SimpleResponse("User " + username + "'s password is updated", true), HttpStatus.OK);
    }

    @PutMapping("{username}/Name/")
    public ResponseEntity<UserDTO> updateName(@PathVariable String username, @RequestParam(name = "FirstName") String firstname, @RequestParam(name = "MiddleName") String middlename, @RequestParam(name = "LastName") String lastname)
    { return new ResponseEntity<>(userService.UpdateName(username, firstname, middlename, lastname), HttpStatus.OK); }

    @PutMapping("{username}/EmailId/")
    public ResponseEntity<UserDTO> updateEmailId(@PathVariable String username, @RequestParam(name = "NewEmailId") String newEmail, @RequestParam(name = "OldEmailId") String emailid)
    { return new ResponseEntity<>(userService.UpdateEmailId(username, emailid, newEmail), HttpStatus.OK); }

    @PutMapping("{username}/MobileNo/")
    public ResponseEntity<UserDTO> updateMobileNo(@PathVariable String username, @RequestParam(name = "NewMobileNo") String newMob, @RequestParam(name = "OldMobileNo") String mobno)
    { return new ResponseEntity<>(userService.UpdateMobileNo(username, mobno, newMob), HttpStatus.OK); }

    @PutMapping("{username}/Qualification/")
    public ResponseEntity<UserDTO> updateQualification(@RequestParam(name = "NewQualification") String qual, @PathVariable String username)
    { return new ResponseEntity<>(userService.UpdateQualification(username, qual), HttpStatus.OK); }

    @GetMapping("All/")
    public ResponseEntity<List<UserDTO>> getAllUser()
    { return new ResponseEntity<>(userService.GetAllUsers(), HttpStatus.OK); }

    @GetMapping("{username}/")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username)
    { return new ResponseEntity<>(userService.GetUser(username), HttpStatus.OK); }

    @GetMapping("Name/{firstname}/")
    public ResponseEntity<List<UserDTO>> getUserbyName(@PathVariable String firstname)
    { return new ResponseEntity<>(userService.GetUserByName(firstname), HttpStatus.OK); }

    @GetMapping("Name/{firstname}/{middlename}/{lastname}/")
    public ResponseEntity<List<UserDTO>> getUserbyName(@PathVariable String firstname, @PathVariable String middlename, @PathVariable String lastname)
    { return new ResponseEntity<>(userService.GetUserByName(firstname, middlename, lastname), HttpStatus.OK); }

    @GetMapping("EmailId/{emailid}/")
    public ResponseEntity<List<UserDTO>> getUserbyEmail(@PathVariable String email)
    { return new ResponseEntity<>(userService.GetUserByEmail(email), HttpStatus.OK); }

    @GetMapping("Authenticate/{username}/")
    public ResponseEntity<SimpleResponse> login(@PathVariable String username, @RequestParam("Password") String password)
    {
        if(!userService.Authenticate(username, password))
            throw new AuthenticateFailureException(username);
        return new ResponseEntity<>(new SimpleResponse("User " + username + " Authenticated", true), HttpStatus.OK);
    }

    @GetMapping("Unique/")
    public ResponseEntity<SimpleResponse> unique(@RequestParam("Username") String username)
    {
        if(userService.IsUnique(username))
            return new ResponseEntity<>(new SimpleResponse("Username: " + username + " is unique", true), HttpStatus.OK);
        else
            return new ResponseEntity<>(new SimpleResponse("Username: " + username +" is not unique", false), HttpStatus.CONFLICT);
    }

    @DeleteMapping("{username}/")
    public ResponseEntity<SimpleResponse> deleteUser(@PathVariable String username)
    { return new ResponseEntity<>(userService.DeleteUser(username), HttpStatus.OK); }

    @DeleteMapping("{username}/EmailId/{emailid}/")
    public ResponseEntity<UserDTO> deleteEmail(@PathVariable String username, @PathVariable String emailid)
    { return new ResponseEntity<>(userService.DeleteEmailId(username, emailid), HttpStatus.OK); }

    @DeleteMapping("{username}/EmailId/All/")
    public ResponseEntity<UserDTO> deleteAllEmail(@PathVariable String username)
    { return new ResponseEntity<>(userService.DeleteAllEmailId(username), HttpStatus.OK); }

    @DeleteMapping("{username}/MobileNo/{mobno}/")
    public ResponseEntity<UserDTO> deleteMobileNo(@PathVariable String username, @PathVariable String mobno)
    { return new ResponseEntity<>(userService.DeleteMobileNo(username, mobno), HttpStatus.OK); }

    @DeleteMapping("{username}/MobileNo/All/")
    public ResponseEntity<UserDTO> deleteAllMobileNo(@PathVariable String username)
    { return new ResponseEntity<>(userService.DeleteAllMobileNo(username), HttpStatus.OK); }

}
