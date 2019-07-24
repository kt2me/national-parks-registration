package com.techelevator;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.dbclasses.JDBCCampgroundDAO;

public class JDBCCampgroundDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCCampgroundDAO dao;

	@Before
	public void setup() {
		
		String sql = "INSERT INTO campground (park_id, campground_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (?, ?, ?, ?, ?, ?)";
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		template.update(sql, 1, 11, "Tech Elevator Campground", "01", "12", new BigDecimal(5.00));
		dao = new JDBCCampgroundDAO(getDataSource());
	}
	
	
	@Test
	public void displayAllCampgrounds_from_campground_by_park_id() {
		int afterCount = (dao.displayAllCampgrounds(1)).size();
		//still relies on data from database. need to fix in IntegrationTestDAO by creating new data to test from.
		Assert.assertEquals(4, afterCount); 
	}
}