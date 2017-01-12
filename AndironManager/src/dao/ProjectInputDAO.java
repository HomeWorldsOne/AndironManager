package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProjectInput;

public class ProjectInputDAO extends BaseDAO {

	public static List<ProjectInput> selectAllByInputId(int inputId) {
		List<ProjectInput> projectInputList = new ArrayList<ProjectInput>();
		String sql = "SELECT * FROM projectInput WHERE inputId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, inputId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					projectInputList.add(parseProjectInput(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectInputList;
	}

	public static List<ProjectInput> selectAllByProjectId(int projectId) {
		List<ProjectInput> projectInputList = new ArrayList<ProjectInput>();
		String sql = "SELECT * FROM projectInput WHERE projectId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, projectId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					projectInputList.add(parseProjectInput(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectInputList;
	}

	// Select specific projectInput based on projectInputId
	public static ProjectInput selectById(int projectInputId) {
		String sql = "SELECT * FROM projectInput WHERE projectInputId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, projectInputId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseProjectInput(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert ProjectInput class into database row
	public static void add(ProjectInput projectInput) {
		String sql = "INSERT INTO `projectInput`(`projectId`, `inputId`)" + "VALUES (?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, projectInput);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Update ProjectInput class in database row
	public static void update(ProjectInput projectInput) {
		String sql = "UPDATE `projectInput` SET `projectId`=?, `inputId`=?" + "WHERE `projectInputId` = ?";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, projectInput);
			stmt.setInt(3, projectInput.getProjectInputId());
			stmt.executeUpdate();
			System.out.println("PROJECTINPUT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Switch based on User existence
	public static void addOrUpdate(ProjectInput projectInput) {
		if (projectInput.getProjectInputId() > 0) {
			update(projectInput);
		} else {
			add(projectInput);
		}
	}

	// Builds user from a database
	public static ProjectInput parseProjectInput(ResultSet rs) throws SQLException {
		ProjectInput projectInput = new ProjectInput();
		projectInput.setProjectInputId(rs.getInt("projectInputId"));
		projectInput.setProjectId(rs.getInt("projectId"));
		projectInput.setInputId(rs.getInt("inputId"));
		return projectInput;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, ProjectInput projectInput) throws SQLException {
		stmt.setInt(1, projectInput.getProjectId());
		stmt.setInt(2, projectInput.getInputId());
	}
}
