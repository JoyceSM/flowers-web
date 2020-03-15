package com.ait.flowers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlowersDao {
//•	Getting details on all products(entities)
	public List<Flowers> findAll() {
		List<Flowers> list = new ArrayList<Flowers>();
		Connection c = null;
		String sql = "SELECT * FROM products ORDER BY product_name";
		try {
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
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

	protected Flowers processRow(ResultSet rs) throws SQLException {
		Flowers product = new Flowers();
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setDescription(rs.getString("product_description"));
		product.setMainColor(rs.getString("main_color"));
		product.setAvailable(rs.getString("available"));
		product.setOccasionId(rs.getInt("occasion_id"));
		product.setEnviroment(rs.getString("enviroment"));
		product.setDimensions(rs.getString("dimensions"));
		product.setPrice(rs.getDouble("price"));
		product.setPicture(rs.getString("picture"));

		return product;
	}

//•	Get details for one product based on its id
	public Flowers findById(int id) {
		String sql = "SELECT * FROM products WHERE product_id = ? ";
		Flowers product = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = processRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}

		return product;
	}

//•	Adding a product 
	public Flowers create(Flowers product) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("INSERT INTO products "
					+ "(product_name, product_description, main_color,available,occasion_id, enviroment, dimensions, price, picture)"
					+ " VALUES (?,?,?,?,?,?,?,?,?)", new String[] { "product_id" });
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getMainColor());
			ps.setString(4, product.getAvailable());
			ps.setInt(5, product.getOccasionId());
			ps.setString(6, product.getEnviroment());
			ps.setString(7, product.getDimensions());
			ps.setDouble(8, product.getPrice());
			ps.setString(9, product.getPicture());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
// Update the id in the returned object. This is important as this value must be returned.
			int id = rs.getInt(1);
			product.setProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return product;

	}

//•	Updating details for a product
	public Flowers update(Flowers product) {
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE products SET product_name=?, product_description=?, main_color=?, available=?, occasion_id=?, enviroment=?, dimensions=?, "
							+ "price=?, picture=? WHERE product_id=?");
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getMainColor());
			ps.setString(4, product.getAvailable());
			ps.setInt(5, product.getOccasionId());
			ps.setString(6, product.getEnviroment());
			ps.setString(7, product.getDimensions());
			ps.setDouble(8, product.getPrice());
			ps.setString(9, product.getPicture());
			ps.setInt(10, product.getProductId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionHelper.close(c);
		}
		return product;
	}

//•	Deleting a product	
	public boolean remove(int id) {
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE product_id=?");
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionHelper.close(c);
		}

	}

// • Searches (determined by your application, based on user stories)
	public List<Flowers> findByDescription(String query) {
		List<Flowers> list = new ArrayList<Flowers>();
		Connection c = null;
		String sql = "SELECT * FROM products WHERE product_description LIKE ? ORDER BY product_name";

		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
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

// • Searches (determined by your application, based on your user stories)
	public List<Flowers> findByOccasion(String query) {
		List<Flowers> list = new ArrayList<Flowers>();
		Connection c = null;
		String sql = "SELECT * FROM products INNER JOIN occasion ON products.occasion_id = occasion.occasion_id WHERE occasion LIKE ?";

		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + query + "%");
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

}
