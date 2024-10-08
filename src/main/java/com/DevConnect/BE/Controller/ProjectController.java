package com.DevConnect.BE.Controller;

import com.DevConnect.BE.DataTransfer.ProjectDTO;
import com.DevConnect.BE.Service.ProjectService;
import com.DevConnect.BE.Utility.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Project/")
public class ProjectController
{
    @Autowired
    ProjectService projectService;

    //POST MAPPINGS
    @PostMapping("Add/")
    public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO project)
    { return new ResponseEntity<>(projectService.AddProject(project), HttpStatus.CREATED); }

    @PostMapping("{id}/Collaborator/")
    public ResponseEntity<ProjectDTO> addCollaborator(@PathVariable Long id, @RequestParam(name = "NewCollaborator") String username)
    { return new ResponseEntity<>(projectService.AddCollaborator(id, username), HttpStatus.CREATED); }

    @PostMapping("{id}/Role/")
    public ResponseEntity<ProjectDTO> addRole(@PathVariable Long id, @RequestParam(name = "NewRole") String role)
    { return new ResponseEntity<>(projectService.AddRole(id, role), HttpStatus.CREATED); }

    @PostMapping("{id}/Category/")
    public ResponseEntity<ProjectDTO> addCategory(@PathVariable Long id, @RequestParam(name = "NewCategory") String category)
    { return new ResponseEntity<>(projectService.AddCategory(id, category), HttpStatus.CREATED); }


    //PUT MAPPINGS
    @PutMapping("{id}/")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO updatedProject)
    { return new ResponseEntity<>(projectService.UpdateProject(updatedProject, id), HttpStatus.OK); }

    @PutMapping("{id}/Collaborator/")
    public ResponseEntity<ProjectDTO> updateCollaborator(@PathVariable Long id, @RequestParam(name = "NewCollaborator") String newCollab, @RequestParam(name = "OldCollaborator") String oldCollab)
    { return new ResponseEntity<>(projectService.UpdateCollaborator(id, oldCollab, newCollab), HttpStatus.OK); }

    @PutMapping("{id}/Role/")
    public ResponseEntity<ProjectDTO> updateRole(@PathVariable Long id, @RequestParam(name = "NewRole") String newRole, @RequestParam(name = "OldRole") String oldRole)
    { return new ResponseEntity<>(projectService.UpdateRole(id, oldRole, newRole), HttpStatus.OK); }

    @PutMapping("{id}/Category/")
    public ResponseEntity<ProjectDTO> updateCategory(@PathVariable Long id, @RequestParam(name = "NewCategory") String newCategory, @RequestParam(name = "OldCategory") String oldCategory)
    { return new ResponseEntity<>(projectService.UpdateCategory(id, oldCategory, newCategory), HttpStatus.OK); }

    @PutMapping("{id}/Description/")
    public ResponseEntity<ProjectDTO> updateDescription(@PathVariable Long id, @RequestParam(name = "NewDescription") String desc)
    { return new ResponseEntity<>(projectService.UpdateDescription(id, desc), HttpStatus.OK); }

    @PutMapping("{id}/Status/")
    public ResponseEntity<ProjectDTO> updateStatus(@PathVariable Long id, @RequestParam(name = "NewStatus") String status)
    { return new ResponseEntity<>(projectService.UpdateStatus(id, status), HttpStatus.OK); }

    @PutMapping("{id}/PrivacyStatus/")
    public ResponseEntity<ProjectDTO> updatePrivacyStatus(@PathVariable Long id, @RequestParam(name = "NewPrivacyStatus") String priv_status)
    { return new ResponseEntity<>(projectService.UpdatePrivacyStatus(id, priv_status), HttpStatus.OK); }

    //GET MAPPINGS
    @GetMapping("Id/")
    public ResponseEntity<ProjectDTO> getById(@RequestParam(name = "Id") Long id)
    { return  new ResponseEntity<>(projectService.GetProject(id), HttpStatus.OK); }

    @GetMapping("All/")
    public ResponseEntity<List<ProjectDTO>> getAll()
    { return new ResponseEntity<>(projectService.GetAllProject(), HttpStatus.OK); }

    @GetMapping("Category/Inclusive/")
    public  ResponseEntity<List<ProjectDTO>> getByCategoryI(@RequestParam(name = "CategoryList") List<String> categoryList)
    { return new ResponseEntity<>(projectService.GetProjectByCategoryI(categoryList), HttpStatus.OK); }

    @GetMapping("Category/Exclusive/")
    public ResponseEntity<List<ProjectDTO>> getByCategoryE(@RequestParam(name = "CategoryList") List<String> categoryList)
    { return  new ResponseEntity<>(projectService.GetProjectByCategoryE(categoryList), HttpStatus.OK); }

    @GetMapping("Role/")
    public ResponseEntity<List<ProjectDTO>> getByRole(@RequestParam(name = "Role") String role)
    { return new ResponseEntity<>(projectService.GetProjectByRole(role), HttpStatus.OK); }

    @GetMapping("Interest/")
    public ResponseEntity<List<ProjectDTO>> getForUser(@RequestParam(name = "Username") String username)
    { return new ResponseEntity<>(projectService.GetProjectByInterest(username), HttpStatus.OK); }

    @GetMapping("{id}/Collaborator/")
    public ResponseEntity<List<String>> getAllCollaborator(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.GetAllCollaborator(id), HttpStatus.OK); }

    @GetMapping("{id}/Role/")
    public ResponseEntity<List<String>> getAllRole(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.GetAllRole(id), HttpStatus.OK); }

    @GetMapping("{id}/Category/")
    public ResponseEntity<List<String>> getAllCategory(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.GetAllCategory(id), HttpStatus.OK); }


    //DELETE MAPPINGS
    @DeleteMapping("{id}/")
    public ResponseEntity<SimpleResponse> deleteProject(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.DeleteProject(id), HttpStatus.OK); }

    @DeleteMapping("{id}/Collaborator/{username}/")
    public ResponseEntity<ProjectDTO> deleteCollaborator(@PathVariable Long id, @PathVariable String username)
    { return new ResponseEntity<>(projectService.DeleteCollaborator(id, username), HttpStatus.OK); }

    @DeleteMapping("{id}/Collaborator/All/")
    public ResponseEntity<ProjectDTO> deleteAllCollaborator(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.DeleteAllCollaborator(id), HttpStatus.OK); }

    @DeleteMapping("{id}/Role/{role}/")
    public ResponseEntity<ProjectDTO> deleteRole(@PathVariable Long id, @PathVariable String role)
    { return new ResponseEntity<>(projectService.DeleteRole(id, role), HttpStatus.OK); }

    @DeleteMapping("{id}/Role/All/")
    public ResponseEntity<ProjectDTO> deleteAllRole(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.DeleteAllRole(id), HttpStatus.OK); }

    @DeleteMapping("{id}/Category/{category}/")
    public ResponseEntity<ProjectDTO> deleteCategory(@PathVariable Long id, @PathVariable String category)
    { return new ResponseEntity<>(projectService.DeleteCategory(id, category), HttpStatus.OK); }

    @DeleteMapping("{id}/Category/All/")
    public ResponseEntity<ProjectDTO> deleteAllCategory(@PathVariable Long id)
    { return new ResponseEntity<>(projectService.DeleteAllCategory(id), HttpStatus.OK); }

}
