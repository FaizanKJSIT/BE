package com.DevConnect.BE.Implementation;

import com.DevConnect.BE.DataTransfer.ApplicationDTO;
import com.DevConnect.BE.DataTransfer.ProjectDTO;
import com.DevConnect.BE.Entity.*;
import com.DevConnect.BE.ExceptionH.AlreadyExistsException;
import com.DevConnect.BE.ExceptionH.ResourceNotFoundException;
import com.DevConnect.BE.Repo.ApplicationRepo;
import com.DevConnect.BE.Service.ApplicationService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationImplement implements ApplicationService
{
    @Autowired
    private ApplicationRepo applicationRepo;

    @Autowired
    private ProjectImplement projectRepo;

    @Autowired
    private UserImplement userRepo;

    ModelMapper mapper;

    private ModelMapper ApplicationConfig()
    {
        mapper = projectRepo.MapperConfig();

        Converter<User, String> UserToUsername = ctx -> ctx.getSource() == null ? null : ctx.getSource().getUsername();
        mapper.typeMap(Application.class, ApplicationDTO.class).addMappings(mp->mp.using(UserToUsername).map(Application::getApplicant, ApplicationDTO::setApplicant));

        Converter<String, User> UsernameToUser = ctx -> ctx.getSource() == null ? null : userRepo.FindUser(ctx.getSource());
        mapper.typeMap(ApplicationDTO.class, Application.class).addMappings((mp->mp.using(UsernameToUser).map(ApplicationDTO::getApplicant, Application::setApplicant)));

        return mapper;
    }

    private Application FindApplication(Long id)
    { return applicationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Application", "id", id.toString())); }

    private ApplicationDTO SaveApplication(Application appl)
    {
        applicationRepo.save(appl);
        return mapper.map(appl, ApplicationDTO.class);
    }

    private List<ApplicationDTO> ApplicationToDTO(List<Application> applicationList)
    {
        List<ApplicationDTO> applDTO = new ArrayList<>(applicationList.size());
        for(Application appl : applicationList)
            applDTO.add(mapper.map(appl, ApplicationDTO.class));
        return applDTO;
    }

    @Autowired
    public ApplicationImplement(ProjectImplement projectRepo)
    {
        this.projectRepo = projectRepo;
        mapper = ApplicationConfig();
    }

    @Override
    public ApplicationDTO AddApplication(ApplicationDTO appl)
    {
        boolean applCheck = appl.getId() == null || !applicationRepo.existsById(appl.getId());
        if(applCheck)
            return SaveApplication(mapper.map(appl, Application.class));
        else
            throw new AlreadyExistsException("Application", appl.getId().toString());
    }

    @Override
    public ApplicationDTO UpdateApplication(Long id, ApplicationDTO appl)
    {
        if(!appl.getId().equals(id))
            throw new RuntimeException("Application id must match passed id!");
        Application application = FindApplication(id);
        application = mapper.map(appl, Application.class);
        return SaveApplication(application);
    }

    @Override
    public ApplicationDTO UpdateProject(Long id, Long project_id)
    {
        Application appl = FindApplication(id);
        ProjectDTO project = projectRepo.GetProject(project_id);
        appl.setApplied_project(mapper.map(project, Project.class));
        return SaveApplication(appl);
    }

    @Override
    public ApplicationDTO UpdateRole(Long id, String applied_role)
    {
        Application appl = FindApplication(id);
        appl.setApplied_role(applied_role);
        return SaveApplication(appl);
    }

    @Override
    public ApplicationDTO UpdateStatus(Long id, String status)
    {
        Application appl = FindApplication(id);
        appl.setStatus(status);
        return SaveApplication(appl);
    }

    @Override
    public ApplicationDTO GetApplication(Long id)
    {
        Application appl = FindApplication(id);
        return mapper.map(appl, ApplicationDTO.class);
    }

    @Override
    public List<ApplicationDTO> GetApplicationForUser(String username)
    {
        List<Application> applicationList = applicationRepo.GetApplicationForUser(username);
        return ApplicationToDTO(applicationList);
    }

    @Override
    public List<ApplicationDTO> GetAllForProject(Long project_id)
    {
        List<Application> applicationList = applicationRepo.GetAllForProject(project_id);
        return ApplicationToDTO(applicationList);
    }

    @Override
    public List<ApplicationDTO> GetApplicationByRole(Long project_id, String role)
    {
        List<Application> applicationList = applicationRepo.GetAllForRole(project_id, role);
        return ApplicationToDTO(applicationList);
    }

    @Override
    public SimpleResponse DeleteApplication(Long id)
    {
        FindApplication(id);
        applicationRepo.findById(id);
        SimpleResponse response = new SimpleResponse("Application with id: " + id + " deleted!", true);
        if(applicationRepo.existsById(id))
        {
            response.setMessage("Failed to delete application with id: " + id);
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public void DeleteApplicationForProject(Long project_id)
    {
        List<Application> appl = applicationRepo.GetAllForProject(project_id);
        for(Application a : appl)
            DeleteApplication(a.getId());
    }
}
