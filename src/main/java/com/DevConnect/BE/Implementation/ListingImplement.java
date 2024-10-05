package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.ListingDTO;
import com.DevConnect.BE.Entity.Listing;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.ResourceNotFoundException;
import com.DevConnect.BE.Repo.ListingRepo;
import com.DevConnect.BE.Service.ListingService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListingImplement implements ListingService
{
    @Autowired
    private ListingRepo listingRepo;

    @Autowired
    private UserImplement userRepo;

    @Autowired
    private ProjectImplement projectRepo;

    private ModelMapper mapper;

    @Autowired
    ListingImplement(ProjectImplement projectImplement)
    {
        this.projectRepo = projectImplement;
        mapper = ListingConfig();
    }

    private ModelMapper ListingConfig()
    {
        ModelMapper mapper = projectRepo.MapperConfig();//Add projectDto to project mapper config

        Converter<User, String> UserToUsername = ctx -> ctx.getSource() == null ? null : ctx.getSource().getUsername();
        mapper.typeMap(Listing.class, ListingDTO.class).addMappings(mp->mp.using(UserToUsername).map(Listing::getLister, ListingDTO::setLister));

        Converter<String, User> UsernameToUser = ctx -> ctx.getSource() == null ? null : userRepo.FindUser(ctx.getSource());
        mapper.typeMap(ListingDTO.class, Listing.class).addMappings((mp->mp.using(UsernameToUser).map(ListingDTO::getLister, Listing::setLister)));

        return mapper;
    }

    private List<ListingDTO> listingToDTO(List<Listing> listing)
    {
        List<ListingDTO> listingDTO  = new ArrayList<>(listing.size());
        for(Listing l : listing)
            listingDTO.add(mapper.map(l, ListingDTO.class));
        return listingDTO;
    }

    private ListingDTO SaveListing(Listing listing)
    {
        listingRepo.save(listing);
        return mapper.map(listing, ListingDTO.class);
    }

    private Listing FindListing(Long id)
    { return listingRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Listing", "id", id.toString())); }

    @Override
    public ListingDTO AddListing(ListingDTO listing)
    {
        Listing listingEntity = mapper.map(listing, Listing.class);
        return SaveListing(listingEntity);
    }

    @Override
    public ListingDTO UpdateListing(Long id, ListingDTO listing)
    {
        if(!listing.getId().equals(id))
            throw new RuntimeException("Listing id and passed id must be same!");
        Listing listingEntity = FindListing(id);
        listingEntity = mapper.map(listing, Listing.class);
        return SaveListing(listingEntity);
    }

    @Override
    public ListingDTO GetListing(Long id)
    {
        Listing listing = FindListing(id);
        return mapper.map(listing, ListingDTO.class);
    }

    @Override
    public List<ListingDTO> GetAllForUser(String username)
    {
        List<Listing> listingList = listingRepo.GetListingForUser(username);
        return listingToDTO(listingList);
    }

    @Override
    public List<ListingDTO> GetAll()
    { return listingToDTO(listingRepo.findAll()); }

    @Override
    public SimpleResponse DeleteListing(Long id)
    {
        FindListing(id);
        listingRepo.deleteById(id);
        SimpleResponse response = new SimpleResponse("Listing with id: " +id + " deleted!", true);
        if(listingRepo.existsById(id))
        {
            response.setMessage("Failed to delete Listing with id: " + id);
            response.setSuccess(false);
        }
       return response;
    }
}
