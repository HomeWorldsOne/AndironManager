package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserProject;

public class UserProjectDAO extends BaseDAO {

	public static List<UserProject> selectAllByUserId(int userId) {
		List<UserProject> convertedFileList = new ArrayList<UserProject>();
		String sql = "SELECT * FROM userProject WHERE userId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					convertedFileList.add(parseUserProject(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return convertedFileList;
	}

	public static List<UserProject> selectAllByProjectId(int projectId) {
		List<UserProject> convertedFileList = new ArrayList<UserProject>();
		String sql = "SELECT * FROM userProject WHERE projectId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, projectId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					convertedFileList.add(parseUserProject(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return convertedFileList;
	}

	// Select specific userProject based on userProjectId
	public static UserProject selectById(int userProjectId) {
		String sql = "SELECT * FROM userProject WHERE userProjectId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, userProjectId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseUserProject(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert UserProject class into database row
	public static void add(UserProject userProject) {
		String sql = "INSERT INTO `userProject`(`userId`, `projectId`)" + "VALUES (?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, userProject);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Update UserProject class in database row
	public static void update(UserProject userProject) {
		String sql = "UPDATE `userProject` SET `userId`=?, `projectId`=?" + "WHERE `userProjectId` = ?";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, userProject);
			stmt.setInt(3, userProject.getUserProjectId());
			stmt.executeUpdate();
			System.out.println("USERPROJECT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Switch based on User existence
	public static void addOrUpdate(UserProject userProject) {
		if (userProject.getUserProjectId() > 0) {
			update(userProject);
		} else {
			add(userProject);
		}
	}

	// Builds user from a database
	public static UserProject parseUserProject(ResultSet rs) throws SQLException {
		UserProject userProject = new UserProject();
		userProject.setUserProjectId(rs.getInt("userProjectId"));
		userProject.setUserId(rs.getInt("userId"));
		userProject.setProjectId(rs.getInt("projectId"));
		return userProject;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, UserProject userProject) throws SQLException {
		stmt.setInt(1, userProject.getUserId());
		stmt.setInt(2, userProject.getProjectId());
	}
}
