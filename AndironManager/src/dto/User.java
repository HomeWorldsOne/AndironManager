package dto;

//Data transfer object for User Objects
public class User {

	//Private variables
	private int userId;
	private int userLocked;
	private String userName;
	private String userPassword;
	private String userUnlockCode;
	private String userEmail;
	private String userType;
	private int userLoginAttempt;
	
	//Getters and Setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserLocked() {
		return userLocked;
	}
	public void setUserLocked(int userLocked) {
		this.userLocked = userLocked;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserUnlockCode() {
		return userUnlockCode;
	}
	public void setUserUnlockCode(String userUnlockCode) {
		this.userUnlockCode = userUnlockCode;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getUserLoginAttempt() {
		return userLoginAttempt;
	}
	public void setUserLoginAttempt(int userLoginAttempt) {
		this.userLoginAttempt = userLoginAttempt;
	}
}
