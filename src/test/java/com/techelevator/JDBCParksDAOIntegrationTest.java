package com.techelevator;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.dbclasses.JDBCParksDAO;
import com.techelevator.dbclasses.Parks;
import com.techelevator.dbclasses.ParksDAO;


public class JDBCParksDAOIntegrationTest extends DAOIntegrationTest{
	
	private ParksDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Parks testPark1;
	private Parks testPark2;
	
	
	@Before
	public void setup() {
		dao = new JDBCParksDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		testPark1 = createPark("Oletangy Test", 66, "Ohio", new Date (2019-12-01), 9999, (long)4895429, "A lovely park");
		testPark2 = createPark("East Village Test", 67, "New York", new Date (2019-12-01), 5555, (long)431890483, "A crappy park");
	}

	@Test
	public void get_all_departments() {
		//arrange
		List<Parks>startingList = dao.displayAllParks();
		int startingSize = startingList.size();
	
		
		addParksToDatabase(testPark1);
		addParksToDatabase(testPark2);

		//ACT
		List<Parks> newParkList = dao.displayAllParks();
		//Assert
		Assert.assertEquals(startingSize + 2, newParkList.size());
	}
	@Test
	public void display_park_information() {
		addParksToDatabase(testPark1);
		addParksToDatabase(testPark2);

		//ACT
		List<Parks> newParkList = dao.displayParkInformation(66);
		Parks parkInfo = newParkList.get(0);
		int parkArea = parkInfo.getArea();
		//Assert
		Assert.assertEquals(parkArea, 9999);
	}
	
	
	private void addParksToDatabase(Parks park){
		String sql = "INSERT INTO park (name, park_id, location, establish_date, area, visitors, description) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, park.getName(), park.getId(), park.getLocation(), park.getEstablishDate(), park.getArea(), park.getVisitors(), park.getDescription());
	}
	
	
	private Parks createPark(String name, int id, String location, Date established, int area, long visitors, String description) {
		Parks thePark = new Parks();
		thePark.setName(name);
		thePark.setId(id);
		thePark.setLocation(location);
		thePark.setEstablishDate(established);
		thePark.setArea(area);
		thePark.setVisitors(visitors);
		thePark.setDescription(description);
		
		return thePark;	
	}
	
	

	
}
