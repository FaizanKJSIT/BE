package com.DevConnect.BE.Repo;

import com.DevConnect.BE.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>
{
    List<Project> findByCategoryIn(List <String> Category);

    @Query("select p from Project p left join p.category cat where cat in :categoryList" + " group by p having count(cat) = :categoryListSize")
    List<Project> findByCategoryContaining(@Param("categoryList") List <String> Category, @Param("categoryListSize") long category_s);//Need to test if it works

    List<Project> findByRoleContaining(String Role);

    @Query("select u.username from Project p join User u where p.id = ?1")
    List<String> findAllCollaborator(Long id);
}
