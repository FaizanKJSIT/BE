package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.ProjectDTO;
import com.DevConnect.BE.Entity.Project;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.ResourceNotFoundException;
import com.DevConnect.BE.Repo.ProjectRepo;
import com.DevConnect.BE.Service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectImplement implements ProjectService
{
    @Autowired
    ProjectRepo projectRepo;

    UserImplement userRepo = new UserImplement();

    ModelMapper mapper = new ModelMapper();

    private Project FindProject(Integer id)
    { return projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "Id", id.toString())); }

    private ProjectDTO SaveProject(Project project)
    {
        projectRepo.save(project);
        return mapper.map(project, ProjectDTO.class);
    }

    private List<ProjectDTO> ProjectListMapper(List<Project> project_l)
    {
        List<ProjectDTO> projectDTO_l = new ArrayList<>(project_l.size());
        for(Project p : project_l)
            projectDTO_l.add(mapper.map(p, ProjectDTO.class));
        return  projectDTO_l;
    }

    @Override
    public ProjectDTO AddProject(Project project)
    { return SaveProject(project); }

    @Override
    public ProjectDTO UpdateProject(ProjectDTO updatedProject, Integer id)
    {
        Project project = FindProject(id);
        if(updatedProject.getId() == id)
            project = mapper.map(updatedProject, Project.class);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO GetProject(Integer id)
    { return mapper.map(FindProject(id), ProjectDTO.class); }

    @Override
    public List<ProjectDTO> GetAllProject()
    { return ProjectListMapper(projectRepo.findAll()); }

    @Override
    public List<ProjectDTO> GetProjectByCategoryI(List<String> Category)
    { return ProjectListMapper(projectRepo.findByCategoryIn(Category)); }

    @Override
    public List<ProjectDTO> GetProjectByCategoryE(List<String> Category)
    { return ProjectListMapper(projectRepo.findByCategoryContaining(Category, Category.size())); }

    @Override
    public List<ProjectDTO> GetProjectByRole(String Role)
    { return ProjectListMapper(projectRepo.findByRoleContaining(Role)); }

    @Override
    public List<ProjectDTO> GetProjectByInterest(String username)
    {
        User user = userRepo.FindUser(username);
        List<ProjectDTO> PerfectMatch = GetProjectByCategoryE(user.getInterest());
        List<ProjectDTO> PartialMatch = GetProjectByCategoryI(user.getInterest());
        PartialMatch.removeAll(PerfectMatch);
        PerfectMatch.addAll(PartialMatch);
        return PerfectMatch;
    }

    @Override
    public void DeleteProject(Integer id)
    { projectRepo.deleteById(id); }

    @Override
    public ProjectDTO AddCollaborator(Integer id, String username)
    {
        User newUser = userRepo.FindUser(username);
        Project project = FindProject(id);
        List<User> collaborator = project.getCollaborator();
        collaborator.add(newUser);
        project.setCollaborator(collaborator);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateCollaborator(Integer id, String oldColUsername, String newColUsername)
    {
        User oldUser = userRepo.FindUser(oldColUsername);
        User newUser = userRepo.FindUser(newColUsername);
        Project project = FindProject(id);
        List<User> collaborator = project.getCollaborator();
        collaborator.set(collaborator.indexOf(oldUser), newUser);
        project.setCollaborator(collaborator);
        return SaveProject(project);
    }

    @Override
    public List<String> GetAllCollaborator(Integer id)
    { return projectRepo.findAllCollaborator(id); }

    @Override
    public ProjectDTO DeleteCollaborator(Integer id, String username)
    {
        Project project = FindProject(id);
        List<User> collaborator= project.getCollaborator();
        collaborator.remove(collaborator.indexOf(userRepo.FindUser(username)));
        return SaveProject(project);
    }

    @Override
    public ProjectDTO DeleteAllCollaborator(Integer id)
    {
        Project project = FindProject(id);
        project.setCollaborator(new ArrayList<>());
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateDescription(Integer id, String newDescription)
    {
        Project project = FindProject(id);
        project.setDescription(newDescription);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO AddRole(Integer id, String role)
    {
        Project project = FindProject(id);
        List<String> allRoles = project.getRole();
        allRoles.add(role);
        project.setRole(allRoles);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateRole(Integer id, String oldRole, String newRole)
    {
        Project project = FindProject(id);
        List<String> allRoles = project.getRole();
        allRoles.set(allRoles.indexOf(oldRole), newRole);
        project.setRole(allRoles);
        return SaveProject(project);
    }

    @Override
    public List<String> GetAllRole(Integer id)
    { return FindProject(id).getRole(); }

    @Override
    public ProjectDTO DeleteRole(Integer id, String role)
    {
        Project project = FindProject(id);
        List<String> allRoles = project.getRole();
        allRoles.remove(allRoles.indexOf(role));
        project.setRole(allRoles);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO DeleteAllRole(Integer id)
    {
        Project project = FindProject(id);
        project.setRole(new ArrayList<>());
        return  SaveProject(project);
    }

    @Override
    public ProjectDTO AddCategory(Integer id, String category)
    {
        Project project = FindProject(id);
        List<String> categories = project.getCategory();
        categories.add(category);
        project.setCategory(categories);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateCategory(Integer id, String oldCategory, String newCategory)
    {
        Project project = FindProject(id);
        List<String> categories = project.getCategory();
        categories.set(categories.indexOf(oldCategory), newCategory);
        project.setCategory(categories);
        return SaveProject(project);
    }

    @Override
    public List<String> GetAllCategory(Integer id)
    { return FindProject(id).getCategory(); }

    @Override
    public ProjectDTO DeleteCategory(Integer id, String category)
    {
        Project project = FindProject(id);
        List<String> categories = project.getCategory();
        categories.remove(category);
        project.setCategory(categories);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO DeleteAllCategory(Integer id)
    {
        Project project = FindProject(id);
        project.setCategory(new ArrayList<>());
        return SaveProject(project);
    }
}
