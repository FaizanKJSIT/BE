package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.ListingDTO;
import com.DevConnect.BE.Service.ListingService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Listing/")
public class ListingController
{
    @Autowired
    ListingService listingService;

    @PostMapping("Add/")
    public ResponseEntity<ListingDTO> AddListing(@RequestBody ListingDTO listingDTO)
    {return new ResponseEntity<>(listingService.AddListing(listingDTO), HttpStatus.CREATED); }

    @PutMapping("{id}/")
    public ResponseEntity<ListingDTO> UpdateListing(@PathVariable Long id, @RequestBody ListingDTO listingDTO)
    { return new ResponseEntity<>(listingService.UpdateListing(id, listingDTO), HttpStatus.OK); }

    @GetMapping("{id}/")
    public ResponseEntity<ListingDTO> GetListing(@PathVariable Long id)
    { return new ResponseEntity<>(listingService.GetListing(id), HttpStatus.OK); }

    @GetMapping("All/")
    public ResponseEntity<List<ListingDTO>> GetAll()
    { return new ResponseEntity<>(listingService.GetAll(), HttpStatus.OK); }

    @GetMapping("Lister/All/")
    public ResponseEntity<List<ListingDTO>> GetAllForUser(@RequestParam("Lister") String username)
    { return new ResponseEntity<>(listingService.GetAllForUser(username), HttpStatus.OK); }

    @DeleteMapping("{id}/")
    public ResponseEntity<SimpleResponse> deleteById(@PathVariable Long id)
    { return new ResponseEntity<>(listingService.DeleteListing(id), HttpStatus.OK); }
}
