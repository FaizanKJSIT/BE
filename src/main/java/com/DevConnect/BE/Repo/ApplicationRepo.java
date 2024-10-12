package com.DevConnect.BE.Repo;

import com.DevConnect.BE.Entity.Application;
import com.DevConnect.BE.Entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long>
{
    @Query("select a from Application a where a.applicant.username = ?1")
    List<Application> GetApplicationForUser(String username);

    @Query("select a from Application a where a.applied_project.id = ?1")
    List<Application> GetAllForProject(Long project_id);

    @Query("select a from Application a where a.applied_project.id = ?1 and a.applied_role = ?2")
    List<Application> GetAllForRole(Long project_id, String role);

    @Query("select l from Listing l where l.listed_project.id = ?1 and l.lister.username = ?2")
    List<Listing> GetAllSelfApplication(Long applied_project, String applicant);
}
