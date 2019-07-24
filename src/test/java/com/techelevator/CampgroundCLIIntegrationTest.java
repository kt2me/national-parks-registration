package com.techelevator;

//import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.dbclasses.CampgroundDAO;
import com.techelevator.dbclasses.JDBCCampgroundDAO;

public class CampgroundCLIIntegrationTest {


	private static SingleConnectionDataSource dataSource;
	private CampgroundCLI dao;
	
	@BeforeClass
	public static void setupDataSource() {
		
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		dataSource.setAutoCommit(false);
	}
	 
	@AfterClass
	public static void closeDataSource() throws SQLException {
		
		dataSource.destroy();
	}

	@Before
	public void setup() {
		
		String sqlInsertParks = "INSERT INTO park name, location, establish_date, area, visitors, description) VALUES (?, ?, ?, ?, ?, ?)";
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update(sqlInsertParks, 1, 2, "Tech Elevator Campground", "01", "12", 35.00); 
		dao = new CampgroundCLI(); //can't do this but getParks is in this class so can't test without moving(?)
	}
	
	@After
	public void rollback() throws SQLException {
		
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		
		return dataSource;
	}
	/*
	@Test
	public void displayAllParks_from_campground_by_park_id() {
		
		//beforeCount gets or sets a value to indic. # of entries before target entry
		int beforeCount = (dao.displayAllParks(8)).size();
		String sql = "INSERT INTO parks (park_id, ( name, location, establish_date, area, visitors, description )) VALUES (?, ?, ?, ?, ?, ?)";
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update(sql, 1, 2, "camp", "01", "12", 35.00); //new BigDecimal(1.00))
		int afterCount = (dao.displayAllParks(9)).size();
		Assert.assertEquals(afterCount, beforeCount + 1);
	}*/
}