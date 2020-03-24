package com.ait.flowers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OccasionDAO {
// • Searches all occasion
	public List<Occasion> findAllOccasion() {
		List<Occasion> list = new ArrayList<Occasion>();
		Connection c = null;
		String sql = "SELECT * FROM occasion ORDER BY occasion";
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}

		return list;

	}

	private Occasion processRow(ResultSet rs) throws SQLException {
		Occasion oc = new Occasion();
		oc.setId(rs.getInt("occasion_id"));
		oc.setOccasion(rs.getString("occasion"));
		return oc;
	}

}
