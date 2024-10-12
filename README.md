# <p align = "center"> **EndPoints** </p>

## Note:
### 1. The endpoints are given for entities and each entity has certain attributes.
### 2. These attributes are required to be passed to access services provided by those endpoints. 
### 3. When a body is requested the entire object with all atributes must be passed.
### 4. All Endpoints returns the same entity object back unless specified otherwise
### 5. It is Recommended that you Drop and Recreate schema after each update if you are purely relying on hibernate to create database to avoid conflicts. 
---
## **I) User Entity**

### **Attributes:**
1. **String username:** Constraints: Primary Key, Not Null and minimum length = 10.
2. **String password:** Constraints: Not null and minimum length = 8. (Note: This attribute shouldn't be passed with user object except when creating user for the first time).
3. **String first_name:** Constraints: none
4. **String middle_name:** Constraints: none
5. **String last_name:** Constraints: none
6. **List of String email_id:** Constraints: none
7. **List of String mobile_no:** Constraints: none
8. **List of String interest:** Constraints: none
9. **String qualification:** Constraints: none

### **Endpoints:** 
* **Create User:**\
  **URL:** "POST /Api/User/Add/"\
  **Parameter:** User Body with all attributes.\
  
* **Add Email id:** \
  **URL:** "POST /Api/User/{username}/EmailId/"\
  **Parameter:** ?NewEmailId = {email_id}\
  **Info:** Adds email id to user with username: {username}

* **Add Mobile No:** \
  **URL:** "POST /Api/User/{username}/MobileNo/"\
  **Parameter:** ?NewMobileNo = {mobile_no}\
  **Info:** Adds mobile no to user with username: {username}

* **Add Interest:** \
  **URL:** "POST /Api/User/{username}/Interest/"\
  **Parameter:** ?NewInterest = {interest}\
   **Info:** Adds new Interest Category to user with username: {username}

* **Update User:** \
  **URL:** "PUT /Api/User/{username}/"\
  **Parameter:** User Body with all attributes except password\
  **Info:** Updates user with new user body. Make sure that username in url is the same as as username in user body. Use this when needing to update multiple attributes at once.
  
* **Update Username:** \
  **URL:** "PUT /Api/User/{username}/Username/"\
  **Parameter:** ?NewUsername = {username}\
  **Info:** Changes the username of user with usernamne: {username}.

* **Update Password:** \
  **URL:** "PUT /Api/User/{username}/Password/"\
  **Parameter:** ?OldPassword={password} & NewPassword={password}\
  **Info:** Updates password of user with username: {username}, uses the old password to authenticate this change.

* **Update Name:** \
  **URL:** "PUT /Api/User/{username}/Name/"\
  **Parameter:** ?FirstName={first_name} & MiddleName={middle_name} & LastName={last_name}\
  **Info:** Updates name of user with username: {username}. You can leave any of the parameters blank which will result in only the remaining paramters to be updated.
  
* **Replace EmailId:** \
  **URL:** "PUT /Api/User/{username}/EmailId/"\
  **Parameter:** ?NewEmailId={email_id} & ?OldEmailId={email_id}\
  **Info:** Replaces OldEmailId of user with NewEmailId.

* **Replace Mobile No:** \
  **URL:** "PUT /Api/User/{username}/MobileNo/"\
  **Parameter:** ?NewMobileNo={mobile_no} & ?OldMobileNo={mobile_no}\
  **Info:** Replaces OldMobileNo of user with NewMobileNo.

* **Update Qualification:** \
  **URL:** "PUT /Api/User/{username}/Qualification/"\
  **Parameter:** ?NewQualification={qualification} \
  **Info:** Updates Qualification of user with Username: {username} with {qualification}

* **Get All Users:** \
  **URL:** "GET /Api/User/All/"\
  **Info:** Returns a list of all User objects.

* **Get Particular User:** \
  **URL:** "GET /Api/User/{username}/"\
  **Info:** Returns User with username : {username}.

* **Get User by first_name:** \
  **URL:** "GET /Api/User/{firstname}/"\
  **Info:** Returns a List of User matching the {firstname}.

* **Get User by Full Name:** \
  **URL:** "GET /Api/User/{firstname}/{middlename}/{lastname}/"\
  **Info:** Returns a List of User matching the {firstname} and {middlename} and {lastname}.

* **Get User by email_id:** \
  **URL:** "GET /Api/User/EmailId/{email_id}/"\
  **Info:** Returns a List of User having at least one email id matching {email_id}.

* **Authenticate User:** \
  **URL:** "GET /Api/User/Authenticate/{username}/"\
  **Parameter:** ?Password={password}\
  **Info:** Returns a response object with message and success = true or false depending upon if the {password} matches users actual password.

* **Check if username is unique or not:** \
  **URL:** "GET /Api/User/Unique/"\
  **Parameter:** ?Username={username}\
  **Info:** Returns a response object with message and success = true or false depending upon if the {username} matches with any other username in the database.(true if it doesnt match)

* **Delete User:** \
  **URL:** "DELETE /Api/User/{username}/"\
  **Info:** Deletes a User with username : {username} and returns a response object with success status(true or false) and message.

* **Delete User's EmailId:** \
  **URL:** "DELETE /Api/User/{username}/EmailId/{emailid}/"\
  **Info:** Deletes User with username : {username}'s email_id matching passed {emailid}.

* **Delete All EmailId for User:** \
  **URL:** "DELETE /Api/User/{username}/EmailId/All/"\
  **Info:** Deletes all email_id of User with username : {username}'s.

* **Delete User's Mobile No:** \
  **URL:** "DELETE /Api/User/{username}/MobileNo/{mobileno}/"\
  **Info:** Deletes User with username : {username}'s mobile_no matching passed {mobileno}.

* **Delete All MobileNo for User:** \
  **URL:** "DELETE /Api/User/{username}/MobileNo/All/"\
  **Info:** Deletes all mobile_no of User with username : {username}.

---
## **II) Project Entity**

### **Attributes:**
1. **Long id:** Constraints: Primary Key (Note: Do not pass this attribute while creating a project because it leads to unexpected behaviour because of spring autogeneration)
2. **List of String collaborator:** Constraints: none. (Note: This attribute stores usernames of all Users working on this project).
3. **String name:** Constraints: Not Null
4. **String aim:** Constraints: none
5. **String status:** Constraints: none
6. **String privacy_status:** Constraints: none
7. **String scope:** Constraints: none
8. **String domain:** Constraints: none
9. **List of String role:** Constraints: none (Notr: Stores all roles required in the project (tech stack of the project))
10. **List of String category:** Constraints: none (Note: Stores all categories of the project, this is used to tag/category search and create recommendation for user) 

### **Endpoints:** 
* **Create Project:**\
  **URL:** "POST /Api/Project/Add/"\
  **Parameter:** Project with all attributes except id.\
  **Info:** Will create a project with attributes as given in the body with automatically generated id.

* **Add Collaborator:**\
  **URL:** "POST /Api/Project/{id}/Collaborator/"\
  **Parameter:** ?NewCollaborator={username}\
  **Info:** Adds user with username = {username} to collaborator list of the project with id = {id}.

* **Add Role:**\
  **URL:** "POST /Api/Project/{id}/Role/"\
  **Parameter:** ?NewRole={role}\
  **Info:** Adds new {role} to the project's (where id matches {id}) role list.

* **Add Category:**\
  **URL:** "POST /Api/Project/{id}/Category/"\
  **Parameter:** ?NewCategory={category}\
  **Info:** Adds new {category} to the project's (where id matches {id}) category list.
  
* **Update Project:**\
  **URL:** "PUT /Api/Project/{id}/"\
  **Parameter:** Project with all attributes.\
  **Info:** Updates project with id = {id} with project body. Make sure {id} matches id attribute of project.

* **Replace Collaborator:**\
  **URL:** "PUT /Api/Project/{id}/Collaborator/"\
  **Parameter:** ?NewCollaborator={username1} & OldCollaborator={username2}.\
  **Info:** Replaces collaborator with {username1} to collaborator with {username2} in Project with id = {id}.

* **Replace Role:**\
  **URL:** "PUT /Api/Project/{id}/Role/"\
  **Parameter:** ?NewRole={role1} & OldRole={role2}.\
  **Info:** Replaces {role1} with {role2} in Project's role list.

* **Replace Category:**\
  **URL:** "PUT /Api/Project/{id}/Category/"\
  **Parameter:** ?NewCategory={category1} & OldCategory={category2}.\
  **Info:** Replaces {category1} with {category2} in Project's category list.

* **Update Description:**\
  **URL:** "PUT /Api/Project/{id}/Description/"\
  **Parameter:** ?NewDescription={description}\
  **Info:** Updates description to {description} of Project with id = {id}.

* **Update Status:**\
  **URL:** "PUT /Api/Project/{id}/Status/"\
  **Parameter:** ?NewStatus={status}\
  **Info:** Updates status to {status} of Project with id = {id}.

* **Update Privacy Status:**\
  **URL:** "PUT /Api/Project/{id}/PrivacyStatus/"\
  **Parameter:** ?NewPrivacyStatus={privacy_status}\
  **Info:** Updates privacy_status to {privacy_status} of Project with id = {id}.

* **Get Particular Project:**\
  **URL:** "GET /Api/Project/Id/"\
  **Parameter:** ?Id={id}\
  **Info:** Returns a project matching id = {id}.

* **Get All Project:**\
  **URL:** "GET /Api/Project/All/"\
  **Info:** Returns list of all project.

* **Get Projects having certain categories: (Inclusive)**\
  **URL:** "GET /Api/Project/Category/Inclusive/"\
  **Parameter:** ?CategoryList={"category1", "category2", ...}\
  **Info:** Returns all projects matching ***at least one of*** category in CategoryList.

* **Get Projects having certain categories: (Exclusive)**\
  **URL:** "GET /Api/Project/Category/Exclusive/"\
  **Parameter:** ?CategoryList={"category1", "category2", ...}\
  **Info:** Returns all projects matching ***all of*** the categories in CategoryList.

* **Get Projects matching user Interest:**\
  **URL:** "GET /Api/Project/Interest/"\
  **Parameter:** ?Username={username}\
  **Info:** Returns all projects matching interests for User with username = {username}. Use for Recommendations.

* **Get Projects having a certain role:**\
  **URL:** "GET /Api/Project/Role/"\
  **Parameter:** ?Role={role}\
  **Info:** Returns all projects having {role} in its role list. Used to help user find projects having certain roles they might be interested in(Like say working on backend or frontend or testing, etc).

* **Get All Collaborators of a Project:**\
  **URL:** "GET /Api/Project/{id}/Collaborator/"\
  **Info:** Returns all collaborators username of Project with id = {id}.

* **Get All Roles of a Project:**\
  **URL:** "GET /Api/Project/{id}/Role/"\
  **Info:** Returns list of roles of Project with id = {id}.
  
* **Get All Categories of a Project:**\
  **URL:** "GET /Api/Project/{id}/Categories/"\
  **Info:** Returns list of categories of Project with id = {id}.
  
* **Delete a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/"\
  **Info:** Deletes Project with id = {id} and returns a response object with success status = true or false and a message.

* **Delete a Collaborator of a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/Collaborator/{username}/"\
  **Info:** Deletes collaborator with username = {username} from Project with id = {id}.

* **Delete All Collaborators of a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/Collaborator/All/"\
  **Info:** Deletes all collaborators from Project with id = {id}.

* **Delete a Role of a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/Role/{role}/"\
  **Info:** Deletes from role list where role matches {role} from Project with id = {id}.

* **Delete All Roles of a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/Role/All/"\
  **Info:** Deletes all roles from Project with id = {id}.

* **Delete a Category of a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/Category/{category}/"\
  **Info:** Deletes from category list where category matches {category} from Project with id = {id}.

* **Delete All Categories of a Particular Project:**\
  **URL:** "DELETE /Api/Project/{id}/Category/All/"\
  **Info:** Deletes all categories from Project with id = {id}.

---
## **III) Listing Entity**

### **Attributes:**
1. **Long id:** Constraints: Primary Key. (Note: Do not pass this attribute while creating a listing because it leads to unexpected behaviour because of spring autogeneration)
2. **Project Entity listed_project:** Constraints: Not Null.
3. **String lister:** Constraints: none (Note: Must be the same as username of the user listing the project)
4. **String date:** Constraints: none

### **Endpoints:** 

* **Create Listing:**\
  **URL:** "POST /Api/Lising/Add/"\
  **Parameter:** Listing with all attributes including all attributes of project entity except id for both.\
                 *For Example:*\
                *`{`\
                    `  "listed_project" :`\
                     `  {`\
                      `    "collaborator" : "[user3, user4],"`\
                      `    "name" : "project2",`\
                      `    ...`\
                      `  },`\
                    `  "lister" : "user2",`\
                    `  "date" : "02/11/2025"`\
                 `}`*\
  **Info:** Will create a listing with attributes as given in the body with automatically generated id.

* **Update Listing:**\
  **URL:** "PUT /Api/Listing/{id}/"\
  **Parameter:** Listing with all atrributes including project attributes(as given in create listing) and ids for both.\
  **Info:** Updates the entire listing with all given attributes.

* **Get a Particular Listing:**\
  **URL:** "GET /Api/Listing/{id}/"\
  **Info:** Returns listing having id = {id}.
  
* **Get All Listings:**\
  **URL:** "GET /Api/Listing/All/"\
  **Info:** Returns list of all listings.

* **Get All Listings listed by a user:**\
  **URL:** "GET /Api/Listing/Lister/All/"\
  **Parameter:** ?Lister={username} \
  **Info:** Returns list of all listings listed by User having username={username}.

* **Delete a Particular Listing:**\
  **URL:** "DELETE /Api/Listing/{id}/"\
  **Info:** Deletes listing with id = {id}.

---
## **IV) Application Entity**
### **Attributes:**
1. **Long id:** Constraints: Primary Key. (Note: Do not pass this attribute while creating a listing because it leads to unexpected behaviour because of spring autogeneration)
2. **String applicant:** Constraints: none (Note: This should be the same as username of the user applying)
3. **Long applied_project:** Constraints: none (Note: This should be the same as primary key of project being applied to)
4. **String applied_role:** Constraints: none (Note: The passed role must exist in the project being applied to)
5. **String status:** Constraints: none
6. **String date:** Constraints: not null

### **Endpoints:** 

* **Create Application:**\
  **URL:** "POST /Api/Application/Add/"\
  **Parameter:** Application with all attributes except id.\
  **Info:** Will create an application with attributes as given in the body with automatically generated id.

* **Update Application:**\
  **URL:** "PUT /Api/Application/{id}/"\
  **Parameter:** Application with all attributes including id. Make sure the object id and {id} match\
  **Info:** Will update application with id = {id} with new attributes.

* **Change Project being applied to:**\
  **URL:** "PUT /Api/Application/{id}/Project/"\
  **Parameter:** ?NewProjectId={project_id}\
  **Info:** Will update project of application to the project with id = {id}.

* **Change Role being applied to:**\
  **URL:** "PUT /Api/Application/{id}/Role/"\
  **Parameter:** ?NewRole={role}\
  **Info:** Will update the role being applied to {role}.

* **Update Status:**\
  **URL:** "PUT /Api/Application/{id}/Status/"\
  **Parameter:** ?NewStatus={project_id}\
  **Info:** Will update application's status.

* **Get a particular application:**\
  **URL:** "GET /Api/Application/{id}/"\
  **Info:** Will get application with id = {id}.

* **Get all applications by a user:**\
  **URL:** "GET /Api/Application/All/User/"\
  **Parameter:** ?Applicant={username}\
  **Info:** Will get all applications of a user.

* **Get all applications to a project:**\
  **URL:** "GET /Api/Application/All/Project/"\
  **Parameter:** ?AProjectId={project_id}\
  **Info:** Will get all applications to a project with id = {project_id}.
  
* **Get all applications for a specific Role on a project:**\
  **URL:** "GET /Api/Application/All/Role/"\
  **Parameter:** ?ProjectId={project_id} & Role={role}\
  **Info:** Will get all applications applying for {role} on the project having id = {project_id}.

* **Delete an application:**\
  **URL:** "DELETE /Api/Application/{id}/"\
  **Info:** Will delete application with id = {id}.

* **Delete all applications for a project:**
  **URL:** "DELETE /Api/Application/Project/"\
  **Parameter:** ?ProjectId={project_id}
  **Info:** Will delete all applications whose applied_project attribute matches {project_id}.
  
---
## **V) Notification Entity**

### **Attributes:**
1. **Long id:** Constraints: Primary Key (Note: Do not pass this attribute while creating a notification because it leads to unexpected behaviour because of spring autogeneration)
2. **String message:** Constraints: Not Null
3. **String receiver:** Constraints: None (Note: Stores username of user who will receive the notification)
4. **String type:** Constraints: None
5. **List of String Data:** Constraints: None (Note: Use this to store any data that might be needed later like listing id, project id, etc)


### **Endpoints:** 

* **Create Notification:**\
  **URL:** "POST /Api/Notification/Add/"\
  **Parameter:** Notification with all attributes except id.\
  **Info:** Will create a notification with attributes as given in the body with automatically generated id.

* **Update Notification:**\
  **URL:** "PUT /Api/Notification/{id}/"\
  **Parameter:** Notification with all attributes including id.\
  **Info:** Will update notification with id = {id} with the passed object. Make sure the id of passed object matches {id}.

* **Update Receiver:**\
  **URL:** "PUT /Api/Notification/{id}/Receiver/"\
  **Parameter:** ?NewReceiver={username}\
  **Info:** Changes receiver of the notification to {username} for notification where id = {id}.

* **Update Message:**\
  **URL:** "PUT /Api/Notification/{id}/Message/"\
  **Parameter:** ?NewMessage={message}\
  **Info:** Changes message of the notification to {message} for notification where id = {id}.
  
* **Update Data:**\
  **URL:** "PUT /Api/Notification/{id}/Data/"\
  **Parameter:** ?NewData={"data1", "data2", ...}\
  **Info:** Changes data of the notification to NewData for notification where id = {id}.

* **Get a Particular Notification:**\
  **URL:** "GET /Api/Notification/{id}/"\
  **Info:** Returns notification whose id = {id}.

* **Get Receiver of a Particular Notification:**
  **URL:** "GET /Api/Notification/{id}/Receiver/"\
  **Info:** Returns User object of notification with id = {id}.
  
* **Get All Notifications for a User:**
  **URL:** "GET /Api/Notification/All/"\
  **Parameter:** ?Receiver={username} \
  **Info:** Returns list of all notifications whose receiver matches {usernaame}.
  
* **Delete a Particular Notification:**
  **URL:** "GET /Api/Notification/{id}/"\
  **Info:** Deletes notification with id = {id}. Returns a response with message and success status = true or false.

* **Delete All Notification for a Receiver:**
  **URL:** "GET /Api/Notification/All/"\
  **Parameter:** ?Receiver={username} \
  **Info:** Deletes all notifications whose receiver matches {username}. Return sa response with message and success status = true or false.
 
