package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Project;
import dto.User;

public class ProjectDAO extends BaseDAO {
	
	//Select specific project based on projectId
	public static Project selectById(int projectId) {
		String sql = "SELECT * FROM project WHERE projectId=?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);			
			){
			stmt.setInt(1, projectId);
			
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
	
	//Insert Project class into database row
	public static void add(Project project) {
		String sql = "INSERT INTO `project`(`projectName`, `projectProperties`, `projectUsers`, `projectPassword`)"
				+ "VALUES (?,?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, project);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//Update Project class in database row
	public static void update(Project project) {
		String sql = "UPDATE `project` SET `projectName`=?, `projectProperties`=?, `projectUsers`=?, `projectPassword`=?"
				+ "WHERE `projectId` = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			fillPreparedStatement(stmt, project);
			stmt.setInt(5, project.getProjectId());
			stmt.executeUpdate();
			System.out.println("PROJECT UPDATED");
		} catch(SQLException e) {
			e.printStackTrace();		}
		return;
	}
	
	//Switch based on User existence
	public static void addOrUpdate(Project project) {
		if(project.getProjectId() > 0) {
			update(project);
		} else {
			add(project);
		}
	}

	//Builds user from a database 
	public static Project parseUser(ResultSet rs) throws SQLException {
		Project project = new Project();
		project.setProjectName(rs.getString("projectName"));
		project.setProjectProperties(rs.getString("projectProperties"));
		project.setProjectUsers(rs.getInt("projectUsers"));
		project.setProjectPassword(rs.getString("projectPassword"));
		return project;
	}
		
	//Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, Project project) throws SQLException {
		stmt.setString(1, project.getProjectName());
		stmt.setString(2, project.getProjectProperties());
		stmt.setInt(3, project.getProjectUsers());
		stmt.setString(4, project.getProjectPassword());
	}
	
	
}
