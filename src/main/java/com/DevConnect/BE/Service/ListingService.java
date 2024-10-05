package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.ListingDTO;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ListingService
{
    ListingDTO AddListing(ListingDTO listing);
    ListingDTO UpdateListing(Long id, ListingDTO listing);
    ListingDTO GetListing(Long id);
    List<ListingDTO> GetAllForUser(String username);
    List<ListingDTO> GetAll();
    SimpleResponse DeleteListing(Long id);
}
