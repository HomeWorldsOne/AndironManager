package algorithms.apriori;
//Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.BaseDAO;

import algorithms.apriori.AprioriDTO;

public class AprioriDAO extends BaseDAO {

	public static List<AprioriDTO> selectAllByInputId(int outputId) {
		List<AprioriDTO> aprioriDTOList = new ArrayList<AprioriDTO>();
		String sql = "SELECT * FROM aprioriEntries WHERE outputId = ?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, outputId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					aprioriDTOList.add(parseAprioriDTO(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aprioriDTOList;
	}

	// Select specific aprioriDTO based on aprioriDTOId
	public static AprioriDTO selectById(int aprioriEntriesId) {
		String sql = "SELECT * FROM aprioriEntries WHERE aprioriEntriesId=?";

		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, aprioriEntriesId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseAprioriDTO(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Insert AprioriDTO class into database row
	public static void add(AprioriDTO aprioriDTO) {
		String sql = "INSERT INTO `aprioriEntries`(`outputId`, `value`, `numOfItems`, `support`)" + "VALUES (?,?,?,?)";
		try (Connection conn = getDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			fillPreparedStatement(stmt, aprioriDTO);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	// Builds user from a database
	public static AprioriDTO parseAprioriDTO(ResultSet rs) throws SQLException {
		AprioriDTO aprioriDTO = new AprioriDTO();
		aprioriDTO.setAprioriEntriesId(rs.getInt("aprioriEntriesId"));
		aprioriDTO.setNumOfValues(rs.getInt("numOfItems"));
		aprioriDTO.setOutputId(rs.getInt("outputId"));
		aprioriDTO.setSupport(Integer.parseInt(rs.getString("support")));
		aprioriDTO.setValues(rs.getString("value"));
		return aprioriDTO;
	}

	// Inserts User class details into a SQL statement
	public static void fillPreparedStatement(PreparedStatement stmt, AprioriDTO aprioriDTO) throws SQLException {
		stmt.setInt(1, aprioriDTO.getOutputId());
		stmt.setString(2, aprioriDTO.getValues());
		stmt.setInt(3, aprioriDTO.getNumOfValues());
		stmt.setString(4, Integer.toString(aprioriDTO.getSupport()));
	}
}
