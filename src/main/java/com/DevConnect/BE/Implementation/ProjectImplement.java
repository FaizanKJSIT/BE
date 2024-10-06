package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.ProjectDTO;
import com.DevConnect.BE.Entity.Project;
import com.DevConnect.BE.Entity.User;
import com.DevConnect.BE.ExceptionH.AlreadyExistsException;
import com.DevConnect.BE.ExceptionH.ResourceNotFoundException;
import com.DevConnect.BE.Repo.ProjectRepo;
import com.DevConnect.BE.Service.ProjectService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectImplement implements ProjectService
{
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    UserImplement userRepo;

    private ModelMapper mapper;

    ProjectImplement()
    {
        mapper = MapperConfig();
    }

    private Project FindProject(Long id)
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

    public ModelMapper MapperConfig()//Would love to shift this into config but userRepo autowiring doesnt seem to work in Utility
    {
        ModelMapper mapper = new ModelMapper();
        Converter<List<User>, List<String>> UserToUsername = ctx -> ctx.getSource() == null ? null : getUsername(ctx.getSource());
        mapper.typeMap(Project.class, ProjectDTO.class).addMappings(mp -> mp.using(UserToUsername).map(Project::getCollaborator, ProjectDTO::setCollaborator));

        Converter<List<String>, List<User>> UsernameToUser = ctx -> ctx.getSource() == null ? null : getUser(ctx.getSource());
        mapper.typeMap(ProjectDTO.class, Project.class).addMappings(mp -> mp.using(UsernameToUser).map(ProjectDTO::getCollaborator, Project::setCollaborator));
        return mapper;
    }
    private List<String> getUsername(List<User> users)
    {
        List<String> username = new ArrayList<>(users.size());
        for(User u : users)
            username.add(u.getUsername());
        return username;
    }
    private List<User> getUser(List<String> username)
    {
        List<User> users = new ArrayList<>(username.size());
        for (String s : username)
        {
            User u = userRepo.FindUser(s);
            users.add(u);
        }
        return users;
    }

    @Override
    public ProjectDTO AddProject(ProjectDTO project)
    {
        boolean projectCheck = project.getId() == null || !projectRepo.existsById(project.getId());
        if(projectCheck)
            return SaveProject(mapper.map(project, Project.class));
        else
            throw new AlreadyExistsException("Project", project.getId().toString());
    }

    @Override
    public ProjectDTO UpdateProject(ProjectDTO updatedProject, Long id)
    {
        if(!updatedProject.getId().equals(id))
            throw new RuntimeException("Project Id and updated project id must be same!");
        Project project = FindProject(id);
        project = mapper.map(updatedProject, Project.class);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO GetProject(Long id)
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
        for(int i = 0; i < PartialMatch.size(); i++)
        {
            for(ProjectDTO match : PerfectMatch)
            {
                if(PartialMatch.get(i).getId().equals(match.getId()))
                    PartialMatch.remove(i);
            }
        }
        PerfectMatch.addAll(PartialMatch);
        return PerfectMatch;
    }

    @Override
    public SimpleResponse DeleteProject(Long id)
    {
        FindProject(id);
        projectRepo.deleteById(id);
        SimpleResponse response = new SimpleResponse("Project with id: "+ id +" deleted!", true);
        if(projectRepo.existsById(id))
        {
            response.setMessage("Failed to delete project with id: " + id);
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public ProjectDTO AddCollaborator(Long id, String username)
    {
        Project project = FindProject(id);
        List<User> collaborator = project.getCollaborator();
        for(User c : collaborator)
        {
            if(c.getUsername().equals(username))
                throw new AlreadyExistsException("Collaborator", username);
        }
        User newUser = userRepo.FindUser(username);
        collaborator.add(newUser);
        project.setCollaborator(collaborator);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateCollaborator(Long id, String oldColUsername, String newColUsername)
    {
        DeleteCollaborator(id, oldColUsername);
        return AddCollaborator(id, newColUsername);
    }

    @Override
    public List<String> GetAllCollaborator(Long id)
    { return projectRepo.findAllCollaborator(id); }

    @Override
    public ProjectDTO DeleteCollaborator(Long id, String username)
    {
        Project project = FindProject(id);
        List<User> collaborator= project.getCollaborator();
        int index = collaborator.indexOf(userRepo.FindUser(username));
        if(index == -1)
            throw new ResourceNotFoundException("Collaborator", "Username", username);
        collaborator.remove(index);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO DeleteAllCollaborator(Long id)
    {
        Project project = FindProject(id);
        project.setCollaborator(new ArrayList<>());
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateDescription(Long id, String newDescription)
    {
        Project project = FindProject(id);
        project.setDescription(newDescription);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO AddRole(Long id, String role)
    {
        Project project = FindProject(id);
        List<String> allRoles = project.getRole();
        allRoles.add(role);
        project.setRole(allRoles);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateRole(Long id, String oldRole, String newRole)
    {
        Project project = FindProject(id);
        List<String> allRoles = project.getRole();
        allRoles.set(allRoles.indexOf(oldRole), newRole);
        project.setRole(allRoles);
        return SaveProject(project);
    }

    @Override
    public List<String> GetAllRole(Long id)
    { return FindProject(id).getRole(); }

    @Override
    public ProjectDTO DeleteRole(Long id, String role)
    {
        Project project = FindProject(id);
        List<String> allRoles = project.getRole();
        allRoles.remove(allRoles.indexOf(role));
        project.setRole(allRoles);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO DeleteAllRole(Long id)
    {
        Project project = FindProject(id);
        project.setRole(new ArrayList<>());
        return  SaveProject(project);
    }

    @Override
    public ProjectDTO AddCategory(Long id, String category)
    {
        Project project = FindProject(id);
        List<String> categories = project.getCategory();
        categories.add(category);
        project.setCategory(categories);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateCategory(Long id, String oldCategory, String newCategory)
    {
        Project project = FindProject(id);
        List<String> categories = project.getCategory();
        categories.set(categories.indexOf(oldCategory), newCategory);
        project.setCategory(categories);
        return SaveProject(project);
    }

    @Override
    public List<String> GetAllCategory(Long id)
    { return FindProject(id).getCategory(); }

    @Override
    public ProjectDTO DeleteCategory(Long id, String category)
    {
        Project project = FindProject(id);
        List<String> categories = project.getCategory();
        categories.remove(category);
        project.setCategory(categories);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO DeleteAllCategory(Long id)
    {
        Project project = FindProject(id);
        project.setCategory(new ArrayList<>());
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdateStatus(Long id, String status)
    {
        Project project = FindProject(id);
        project.setStatus(status);
        return SaveProject(project);
    }

    @Override
    public ProjectDTO UpdatePrivacyStatus(Long id, String priv_status)
    {
        Project project = FindProject(id);
        project.setPrivacy_status(priv_status);
        return SaveProject(project);
    }
}
