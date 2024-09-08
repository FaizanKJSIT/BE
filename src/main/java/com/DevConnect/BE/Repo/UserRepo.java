package com.DevConnect.BE.Repo;

import com.DevConnect.BE.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String>
{
    @Query("select u from User u where u.first_name = %?1")
    List<User> findByfirstname(String firstname);

    @Query("select u from User u where u.first_name = %?1 and u.middle_name = %?2 and u.last_name = %?3")
    List<User> findByfullname(String firstname, String middlename, String lastname);

    @Query("select u from User u join u.email_id eid where eid = %?1")
    List<User> findByEmailId(String EmailId);
}
