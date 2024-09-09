package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.UserDTO;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.Service.UserService;
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
    public ResponseEntity<UserDTO> addMobileNo(@PathVariable String username, @RequestParam(name = "NewMobileNo") Long mobno)
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
    public ResponseEntity<?> updatePassword(@PathVariable String username, @RequestParam(name = "NewPassword") String password)
    {
        userService.UpdateUserPassword(username, password);
        return new ResponseEntity<>("User " + username + "'s password is updated", HttpStatus.OK);
    }

    @PutMapping("{username}/EmailId/")
    public ResponseEntity<UserDTO> updateEmailId(@PathVariable String username, @RequestParam(name = "NewEmailId") String newEmail, @RequestParam(name = "OldEmailId") String emailid)
    { return new ResponseEntity<>(userService.UpdateEmailId(username, emailid, newEmail), HttpStatus.OK); }

    @PutMapping("{username}/MobileNo/")
    public ResponseEntity<UserDTO> updateMobileNo(@PathVariable String username, @RequestParam(name = "NewMobileNo") Long newMob, @RequestParam(name = "OldMobileNo") Long mobno)
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

    @DeleteMapping("{username}/")
    public ResponseEntity<?> deleteUser(@PathVariable String username)
    {
        userService.DeleteUser(username);
        return new ResponseEntity<>("User " + username + " deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("{username}/EmailId/{emailid}/")
    public ResponseEntity<UserDTO> deleteEmail(@PathVariable String username, @PathVariable String emailid)
    { return new ResponseEntity<>(userService.DeleteEmailId(username, emailid), HttpStatus.OK); }

    @DeleteMapping("{username}/EmailId/All/")
    public ResponseEntity<UserDTO> deleteAllEmail(@PathVariable String username)
    { return new ResponseEntity<>(userService.DeleteAllEmailId(username), HttpStatus.OK); }

    @DeleteMapping("{username}/MobileNo/{mobno}/")
    public ResponseEntity<UserDTO> deleteMobileNo(@PathVariable String username, @PathVariable Long mobno)
    { return new ResponseEntity<>(userService.DeleteMobileNo(username, mobno), HttpStatus.OK); }

    @DeleteMapping("{username}/MobileNo/All/")
    public ResponseEntity<UserDTO> deleteAllMobileNo(@PathVariable String username)
    { return new ResponseEntity<>(userService.DeleteAllMobileNo(username), HttpStatus.OK); }

}
