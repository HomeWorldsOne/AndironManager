package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProjectOutput;

public class ProjectOutputDAO extends BaseDAO {

	public static List<ProjectOutput> selectAllByOutputId(int inputId) {
		List<ProjectOutput> projectOutputList = new ArrayList<ProjectOutput>();
		String sql = "SELECT * FROM projectOutput WHERE inputId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, inputId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					projectOutputList.add(parseProjectOutput(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectOutputList;
	}

	public static List<ProjectOutput> selectAllByProjectId(int projectId) {
		List<ProjectOutput> projectOutputList = new ArrayList<ProjectOutput>();
		String sql = "SELECT * FROM projectOutput WHERE projectId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, projectId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					projectOutputList.add(parseProjectOutput(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectOutputList;
	}

	// Select specific projectOutput based on projectOutputId
	public static ProjectOutput selectById(int projectOutputId) {
		String sql = "SELECT * FROM projectOutput WHERE projectOutputId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, projectOutputId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseProjectOutput(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert ProjectOutput class into database row
	public static void add(ProjectOutput projectOutput) {
		String sql = "INSERT INTO `projectOutput`(`projectId`, `outputId`)" + "VALUES (?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, projectOutput);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Update ProjectOutput class in database row
	public static void update(ProjectOutput projectOutput) {
		String sql = "UPDATE `projectOutput` SET `projectId`=?, `outputId`=?" + "WHERE `projectOutputId` = ?";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, projectOutput);
			stmt.setInt(3, projectOutput.getProjectOutputId());
			stmt.executeUpdate();
			System.out.println("PROJECTINPUT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Switch based on User existence
	public static void addOrUpdate(ProjectOutput projectOutput) {
		if (projectOutput.getProjectOutputId() > 0) {
			update(projectOutput);
		} else {
			add(projectOutput);
		}
	}

	// Builds user from a database
	public static ProjectOutput parseProjectOutput(ResultSet rs) throws SQLException {
		ProjectOutput projectOutput = new ProjectOutput();
		projectOutput.setProjectOutputId(rs.getInt("projectOutputId"));
		projectOutput.setProjectId(rs.getInt("projectId"));
		projectOutput.setOutputId(rs.getInt("outputId"));
		return projectOutput;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, ProjectOutput projectOutput) throws SQLException {
		stmt.setInt(1, projectOutput.getProjectId());
		stmt.setInt(2, projectOutput.getOutputId());
	}
}
