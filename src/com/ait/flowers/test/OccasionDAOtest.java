package com.ait.flowers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.flowers.ConnectionHelper;
import com.ait.flowers.Occasion;
import com.ait.flowers.OccasionDAO;

class OccasionDAOtest {

	private Connection cn;
	private OccasionDAO dao;

	@BeforeEach
	public void init() throws ClassNotFoundException, SQLException {
		cn = ConnectionHelper.getConnection();
		dao = new OccasionDAO();

	}

	@AfterEach
	public void finalize() throws SQLException {
		cn.close();
	}

	@Test
	void testFindAll() {
		List<Occasion> list = dao.findAllOccasion();
		assertTrue(list.size() > 0);

	}

}
