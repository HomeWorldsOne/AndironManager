package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Input;

public class InputDAO extends BaseDAO {

	// Select specific input based on inputId
	public static Input selectById(int inputId) {
		String sql = "SELECT * FROM input WHERE inputId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, inputId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseInput(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert Input class into database row
	public static void add(Input input) {
		String sql = "INSERT INTO `input`(`dataName`, `fileUrl`)" + "VALUES (?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, input);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Update Input class in database row
	public static void update(Input input) {
		String sql = "UPDATE `input` SET `dataName`=?, `fileUrl`=?" + "WHERE `inputId` = ?";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, input);
			stmt.setInt(3, input.getInputId());
			stmt.executeUpdate();
			System.out.println("INPUT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Switch based on User existence
	public static void addOrUpdate(Input input) {
		if (input.getInputId() > 0) {
			update(input);
		} else {
			add(input);
		}
	}

	// Builds user from a database
	public static Input parseInput(ResultSet rs) throws SQLException {
		Input input = new Input();
		input.setDataName(rs.getString("dataName"));
		input.setFileUrl(rs.getString("fileUrl"));
		return input;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, Input input) throws SQLException {
		stmt.setString(1, input.getDataName());
		stmt.setString(2, input.getFileUrl());
	}
}
