package com.DevConnect.BE.Repo;

import com.DevConnect.BE.Entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepo extends JpaRepository<Listing, Long>
{
    @Query(value = "select l from Listing l where l.lister.username = ?1")
    List<Listing> GetListingForUser(String username);

    @Query(value = "delete from Listing l where l.id = ?1")
    void Delete(Long id);
}
