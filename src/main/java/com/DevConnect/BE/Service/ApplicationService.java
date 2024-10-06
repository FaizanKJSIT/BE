package com.DevConnect.BE.Service;

import com.DevConnect.BE.DataTransfer.ApplicationDTO;
import com.DevConnect.BE.DataTransfer.NotificationDTO;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService
{
    ApplicationDTO AddApplication(ApplicationDTO appl);
    ApplicationDTO UpdateApplication(Long id, ApplicationDTO appl);
    ApplicationDTO UpdateProject(Long id, Long project_id);
    ApplicationDTO UpdateRole(Long id, String applied_role);
    ApplicationDTO UpdateStatus(Long id, String status);
    ApplicationDTO GetApplication(Long id);
    List<ApplicationDTO> GetApplicationForUser(String username);
    List<ApplicationDTO> GetAllForProject(Long project_id);
    List<ApplicationDTO> GetApplicationByRole(Long project_id, String role);
    SimpleResponse DeleteApplication(Long id);
    void DeleteApplicationForProject(Long Project_id);
}
