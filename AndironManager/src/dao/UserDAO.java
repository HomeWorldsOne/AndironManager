package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;

//Class for managing the database operations of the User class
public class UserDAO extends BaseDAO {
	
	//Select specific User
	public static User selectById(int userId) {
		String sql = "SELECT * FROM user WHERE userId=?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);			
			){
			stmt.setInt(1, userId);
			
			try(ResultSet rs = stmt.executeQuery()) {		
				if(rs.next()) {
					return parseUser(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Select specific User based on email
	public static User selectByEmail(String email) {
		String sql = "SELECT * FROM user WHERE userEmail = ?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);			
		){
			
			stmt.setString(1, email);
			
			try(ResultSet rs = stmt.executeQuery()){			
				if(rs.next()) {
					return parseUser(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Insert User class into database row
	public static void add(User user) {
		String sql = "INSERT INTO `user`(`userLocked`, `userName`, `userPassword`, `userUnlockCode`, `userEmail`, `userType`, `userLoginAttempt`)"
				+ "VALUES (?,?,?,?,?,?,?)";
		
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			
			fillPreparedStatement(stmt, user);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//Update user class in database row
	public static void update(User user) {
		String sql = "UPDATE `user` SET `userLocked`=?, `userName`=?, `userPassword`=?, `userUnlockCode`=?, `userEmail`=?,"
				+ "`userType`=?, `userLoginAttempt`=? WHERE `userId` = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			fillPreparedStatement(stmt, user);
			stmt.setInt(9, user.getUserId());
			
			stmt.executeUpdate();
			System.out.println("USER UPDATED");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//Switch based on User existence
	public static void addOrUpdate(User user) {
		if(user.getUserId() > 0) {
			update(user);
		} else {
			add(user);
		}
	}

	//Builds user from a database 
	public static User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("userId"));
		user.setUserLocked(rs.getInt("userLocked"));
		user.setUserName(rs.getString("userName"));
		user.setUserPassword(rs.getString("userPassword"));
		user.setUserUnlockCode(rs.getString("userUnlockCode"));
		user.setUserEmail(rs.getString("userEmail"));
		user.setUserType(rs.getString("userType"));
		user.setUserLoginAttempt(rs.getInt("userLoginAttempt"));
		return user;
	}
	
	//Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, User user) throws SQLException {
		stmt.setInt(1, user.getUserLocked());
		stmt.setString(2, user.getUserName());
		stmt.setString(4, user.getUserPassword());
		stmt.setString(5, user.getUserUnlockCode());
		stmt.setString(6, user.getUserEmail());
		stmt.setString(7, user.getUserType());
		stmt.setInt(8, user.getUserLoginAttempt());
	}
	
}