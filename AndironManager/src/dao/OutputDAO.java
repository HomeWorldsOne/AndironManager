package dao;

//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Output;

public class OutputDAO extends BaseDAO {

	// Select specific output based on outputId
	public static Output selectById(int outputId) {
		String sql = "SELECT * FROM output WHERE outputId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, outputId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseOutput(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert Output class into database row
	public static void add(Output output) {
		String sql = "INSERT INTO `output`(`outputFileName`, `outputAlgorithm`, `dateMade`, `outputFileUrl`)" + "VALUES (?,?,?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, output);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Update Output class in database row
	public static void update(Output output) {
		String sql = "UPDATE `output` SET `outputFileName`=?, `outputAlgorithm`=?, `dateMade`=?, `outputFileUrl`=?" + "WHERE `outputId` = ?";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, output);
			stmt.setInt(5, output.getOutputId());
			stmt.executeUpdate();
			System.out.println("INPUT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Switch based on User existence
	public static void addOrUpdate(Output output) {
		if (output.getOutputId() > 0) {
			update(output);
		} else {
			add(output);
		}
	}

	// Builds user from a database
	public static Output parseOutput(ResultSet rs) throws SQLException {
		Output output = new Output();
		output.setOutputFileName(rs.getString("outputFileName"));
		output.setOutputAlgorithm(rs.getString("outputAlgorithm"));
		output.setDateMade(rs.getString("dateMade"));
		output.setOutputFileUrl(rs.getString("outputFileUrl"));
		return output;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, Output output) throws SQLException {
		stmt.setString(1, output.getOutputFileName());
		stmt.setString(2, output.getOutputAlgorithm());
		stmt.setString(3, output.getDateMade());
		stmt.setString(4, output.getOutputFileUrl());
	}
}
