package dto;

public class Project {

	//Private variables
    private int projectId;
    private int projectUsers; //Number of users included in the project
    private String projectName;
    private String projectProperties;
    private String projectPassword;
    
    //Getters and setters
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getProjectUsers() {
		return projectUsers;
	}
	public void setProjectUsers(int projectUsers) {
		this.projectUsers = projectUsers;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectProperties() {
		return projectProperties;
	}
	public void setProjectProperties(String projectProperties) {
		this.projectProperties = projectProperties;
	}
	public String getProjectPassword() {
		return projectPassword;
	}
	public void setProjectPassword(String projectPassword) {
		this.projectPassword = projectPassword;
	}
    
}
