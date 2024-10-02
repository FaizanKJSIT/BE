package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService
{
    ProjectDTO AddProject(ProjectDTO project);
    ProjectDTO UpdateProject(ProjectDTO updatedProject, Integer id);
    ProjectDTO GetProject(Integer id);
    List<ProjectDTO> GetAllProject();
    List<ProjectDTO> GetProjectByCategoryI(List<String> Category); //Will return all project with at least 1 category matching(Inclusive)
    List<ProjectDTO> GetProjectByCategoryE(List<String> Category); //Will only return projects with all categories matching (Exclusive)
    List<ProjectDTO> GetProjectByRole(String Role); //Will return all projects requiring the passed role (example: if role = backend will return all projects requiring a backend dev
    List<ProjectDTO> GetProjectByInterest(String username);
    /*Will give a list of project a user might be interested in based on their entered interest(see user entity).
      Useful for recommendation page/front page for users*/
    void DeleteProject(Integer id);

    ProjectDTO UpdateStatus(Integer id, String status);
    ProjectDTO UpdatePrivacyStatus(Integer id, String priv_status);

    ProjectDTO AddCollaborator(Integer id, String username);
    ProjectDTO UpdateCollaborator(Integer id, String oldColUsername, String newColUsername);
    List<String> GetAllCollaborator(Integer id);
    ProjectDTO DeleteCollaborator(Integer id, String username);
    ProjectDTO DeleteAllCollaborator(Integer id);

    ProjectDTO AddRole(Integer id, String role);
    ProjectDTO UpdateRole(Integer id, String oldRole, String newRole);
    List<String> GetAllRole(Integer id);
    ProjectDTO DeleteRole(Integer id, String role);
    ProjectDTO DeleteAllRole(Integer id);

    ProjectDTO AddCategory(Integer id, String category);
    ProjectDTO UpdateCategory(Integer id, String oldCategory, String newCategory);
    List<String> GetAllCategory(Integer id);
    ProjectDTO DeleteCategory(Integer id, String category);
    ProjectDTO DeleteAllCategory(Integer id);

    ProjectDTO UpdateDescription(Integer id, String newDescription);
}
