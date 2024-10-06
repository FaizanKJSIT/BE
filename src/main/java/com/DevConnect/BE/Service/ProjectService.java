package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.ProjectDTO;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService
{
    ProjectDTO AddProject(ProjectDTO project);
    ProjectDTO UpdateProject(ProjectDTO updatedProject, Long id);
    ProjectDTO GetProject(Long id);

    List<ProjectDTO> GetAllProject();
    List<ProjectDTO> GetProjectByCategoryI(List<String> Category); //Will return all project with at least 1 category matching(Inclusive)
    List<ProjectDTO> GetProjectByCategoryE(List<String> Category); //Will only return projects with all categories matching (Exclusive)
    List<ProjectDTO> GetProjectByRole(String Role); //Will return all projects requiring the passed role (example: if role = backend will return all projects requiring a backend dev
    List<ProjectDTO> GetProjectByInterest(String username);
    /*Will give a list of project a user might be interested in based on their entered interest(see user entity).
      Useful for recommendation page/front page for users*/
    SimpleResponse DeleteProject(Long id);

    ProjectDTO UpdateStatus(Long id, String status);
    ProjectDTO UpdatePrivacyStatus(Long id, String priv_status);

    ProjectDTO AddCollaborator(Long id, String username);
    ProjectDTO UpdateCollaborator(Long id, String oldColUsername, String newColUsername);
    List<String> GetAllCollaborator(Long id);
    ProjectDTO DeleteCollaborator(Long id, String username);
    ProjectDTO DeleteAllCollaborator(Long id);

    ProjectDTO AddRole(Long id, String role);
    ProjectDTO UpdateRole(Long id, String oldRole, String newRole);
    List<String> GetAllRole(Long id);
    ProjectDTO DeleteRole(Long id, String role);
    ProjectDTO DeleteAllRole(Long id);

    ProjectDTO AddCategory(Long id, String category);
    ProjectDTO UpdateCategory(Long id, String oldCategory, String newCategory);
    List<String> GetAllCategory(Long id);
    ProjectDTO DeleteCategory(Long id, String category);
    ProjectDTO DeleteAllCategory(Long id);

    ProjectDTO UpdateDescription(Long id, String newDescription);
}
