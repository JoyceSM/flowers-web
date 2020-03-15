package com.ait.flowers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.flowers.ConnectionHelper;
import com.ait.flowers.Flowers;
import com.ait.flowers.FlowersDao;

class FlowersDaoTest {

	private Connection cn;
	private FlowersDao dao;

	@BeforeEach
	public void init() throws ClassNotFoundException, SQLException {
		cn = ConnectionHelper.getConnection();
		dao = new FlowersDao();

	}

	@AfterEach
	public void finalize() throws SQLException {
		cn.close();
	}

	@Test
	void testFindAll() {
		List<Flowers> list = dao.findAll();
		assertTrue(list.size() > 0);

	}

	@Test
	void testFindById() {
		Flowers product = dao.findById(1007);
		assertEquals("Peace", product.getProductName());

	}

	@Test
	void testCreateAndDelete() {
		Flowers product = new Flowers();
		product.setProductName("Sunflower");
		product.setDescription("A gorgeous flower for sunny days");
		product.setMainColor("Yellow");
		product.setAvailable("Y");
		product.setOccasionId(3);
		product.setEnviroment("Outdoor");
		product.setDimensions("15x10x5");
		product.setPrice(20.00);
		product.setPicture("img.test");
		assertEquals("Sunflower", product.getProductName());
		assertEquals("A gorgeous flower for sunny days", product.getDescription());
		assertEquals("Yellow", product.getMainColor());
		assertEquals("Y", product.getAvailable());
		assertEquals(3, product.getOccasionId());
		assertEquals("Outdoor", product.getEnviroment());
		assertEquals("15x10x5", product.getDimensions());
		assertEquals(20.00, product.getPrice());
		assertEquals("img.test", product.getPicture());
		Flowers np = dao.create(product);
		assertNotEquals(0, product.getProductId());
		boolean delete = dao.remove(np.getProductId());
		assertTrue(delete);

	}

	@Test
	void testUpdate() {
		Flowers up = dao.findById(1006);
		assertEquals("A Dream", up.getProductName());
		up.setProductName("A Sweet Dream");
		dao.update(up);
		Flowers updated = dao.findById(1006);
		assertEquals("A Sweet Dream", updated.getProductName());
		updated.setProductName("A Dream");
		dao.update(updated);
	}

	@Test
	void testSearchByDescription() {
		List<Flowers> list = dao.findByDescription("roses");
		assertTrue(list.size() > 0);
		assertTrue(list.get(0).getDescription().toLowerCase().contains("roses"));
	}

	@Test
	void testSearchByOccasion() {
		List<Flowers> list = dao.findByOccasion("birthday");
		assertTrue(list.size() > 0);
	}

}
