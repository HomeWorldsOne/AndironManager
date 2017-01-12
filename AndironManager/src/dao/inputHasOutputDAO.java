package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.InputHasOutput;

public class inputHasOutputDAO extends BaseDAO {

	public static List<InputHasOutput> selectAllByInputId(int inputId) {
		List<InputHasOutput> inputHasOutputList = new ArrayList<InputHasOutput>();
		String sql = "SELECT * FROM inputHasOutput WHERE inputId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, inputId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					inputHasOutputList.add(parseInputHasOutput(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inputHasOutputList;
	}

	public static List<InputHasOutput> selectAllByProjectId(int outputId) {
		List<InputHasOutput> inputHasOutputList = new ArrayList<InputHasOutput>();
		String sql = "SELECT * FROM inputHasOutput WHERE outputId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, outputId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					inputHasOutputList.add(parseInputHasOutput(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inputHasOutputList;
	}

	// Select specific inputHasOutput based on inputHasOutputId
	public static InputHasOutput selectById(int inputHasOutputId) {
		String sql = "SELECT * FROM inputHasOutput WHERE inputHasOutputId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, inputHasOutputId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseInputHasOutput(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert InputHasOutput class into database row
	public static void add(InputHasOutput inputHasOutput) {
		String sql = "INSERT INTO `inputHasOutput`(`inputId`, `outputId`)" + "VALUES (?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, inputHasOutput);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Update InputHasOutput class in database row
	public static void update(InputHasOutput inputHasOutput) {
		String sql = "UPDATE `inputHasOutput` SET `inputId`=?, `outputId`=?" + "WHERE `inputHasOutputId` = ?";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, inputHasOutput);
			stmt.setInt(3, inputHasOutput.getInputHasOutputId());
			stmt.executeUpdate();
			System.out.println("INPUTHASOUTPUT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Switch based on User existence
	public static void addOrUpdate(InputHasOutput inputHasOutput) {
		if (inputHasOutput.getInputHasOutputId() > 0) {
			update(inputHasOutput);
		} else {
			add(inputHasOutput);
		}
	}

	// Builds user from a database
	public static InputHasOutput parseInputHasOutput(ResultSet rs) throws SQLException {
		InputHasOutput inputHasOutput = new InputHasOutput();
		inputHasOutput.setOutputId(rs.getInt("outputId"));
		inputHasOutput.setInputId(rs.getInt("inputId"));
		return inputHasOutput;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, InputHasOutput inputHasOutput) throws SQLException {
		stmt.setInt(1, inputHasOutput.getInputId());
		stmt.setInt(2, inputHasOutput.getOutputId());
	}
}
