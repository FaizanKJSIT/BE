package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.ApplicationDTO;
import com.DevConnect.BE.Service.ApplicationService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Application/")
public class ApplicationController
{
    @Autowired
    ApplicationService applicationService;

    @PostMapping("Add/")
    public ResponseEntity<ApplicationDTO> addApplication(@RequestBody ApplicationDTO appl)
    { return new ResponseEntity<>(applicationService.AddApplication(appl), HttpStatus.CREATED); }

    @PutMapping("{id}/")
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable Long id, @RequestBody ApplicationDTO appl)
    { return new ResponseEntity<>(applicationService.UpdateApplication(id, appl), HttpStatus.OK); }

    @PutMapping("{id}/Project/")
    public ResponseEntity<ApplicationDTO> updateProject(@PathVariable Long id, @RequestParam("NewProjectId") Long project_id)
    { return new ResponseEntity<>(applicationService.UpdateProject(id, project_id), HttpStatus.OK); }

    @PutMapping("{id}/Role/")
    public ResponseEntity<ApplicationDTO> updateRole(@PathVariable Long id, @RequestParam("NewRole") String role)
    { return new ResponseEntity<>(applicationService.UpdateRole(id, role), HttpStatus.OK); }

    @PutMapping("{id}/Status/")
    public ResponseEntity<ApplicationDTO> updateStatus(@PathVariable Long id, @RequestParam("NewStatus") String status)
    { return new ResponseEntity<>(applicationService.UpdateStatus(id, status), HttpStatus.OK); }

    @GetMapping("{id}/")
    public ResponseEntity<ApplicationDTO> getApplication(@PathVariable Long id)
    { return new ResponseEntity<>(applicationService.GetApplication(id), HttpStatus.OK); }

    @GetMapping("All/User/")
    public ResponseEntity<List<ApplicationDTO>> getAllForUser(@RequestParam("Applicant") String username)
    { return new ResponseEntity<>(applicationService.GetApplicationForUser(username), HttpStatus.OK); }

    @GetMapping("All/Project/")
    public ResponseEntity<List<ApplicationDTO>> getAllForProject(@RequestParam("ProjectId") Long project_id)
    { return new ResponseEntity<>(applicationService.GetAllForProject(project_id), HttpStatus.OK); }

    @GetMapping("All/Role/")
    public ResponseEntity<List<ApplicationDTO>> getAllForRole(@RequestParam("ProjectId") Long project_id, @RequestParam("Role") String role)
    { return new ResponseEntity<>(applicationService.GetApplicationByRole(project_id, role), HttpStatus.OK); }

    @DeleteMapping("{id}/")
    public ResponseEntity<SimpleResponse> deleteApplication(@PathVariable Long id)
    { return new ResponseEntity<>(applicationService.DeleteApplication(id), HttpStatus.OK); }

    @DeleteMapping("Project/")
    public ResponseEntity<SimpleResponse> deleteByProject(@RequestParam("ProjectId") Long project_id)
    {
        applicationService.DeleteApplicationForProject(project_id);
        return new ResponseEntity<>(new SimpleResponse("Deleted all application for project with id: " + project_id, true), HttpStatus.OK);
    }
}
