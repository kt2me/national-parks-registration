package com.techelevator.dbclasses;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampgroundDAO implements CampgroundDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List <Campground> displayAllCampgrounds(int id) {
		String sql = "SELECT * from campground WHERE park_id =?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		
		List<Campground> allCampgrounds = new ArrayList<Campground>();
		
		while(result.next()) {
			allCampgrounds.add(mapRowToCampground(result));
		}
		
		return allCampgrounds;
	}
	
	
	private Campground mapRowToCampground(SqlRowSet result) {
		Campground c = new Campground();
		c.setParkId(result.getInt("park_id"));
		c.setCampgroundId(result.getInt("campground_id"));
		c.setName(result.getString("name"));
		String monthFrom = result.getString("open_from_mm");
		c.setOpenFrom(setMonth(monthFrom));
		String monthTo = result.getString("open_to_mm");
		c.setOpenTo(setMonth(monthTo));
		c.setDailyFee(result.getDouble("daily_fee"));
		return c;
	}	
	
	private String setMonth(String monthInNum) {
		String monthString = null;
		
		switch(monthInNum) {
		case "01":
			monthString = "January";
			break;
		case "02":
			monthString = "February";
			break;
		case "03":
			monthString = "March";
			break;
		case "04":
			monthString = "April";
			break;
		case "05":
			monthString = "May";
			break;
		case "06":
			monthString = "June";
			break;
		case "07":
			monthString = "July";
			break;
		case "08":
			monthString = "August";
			break;
		case "09":
			monthString="September";
			break; 
		case "10":
			monthString = "October";
			break;
		case "11":
			monthString = "November";
			break;
		case "12":
			monthString = "December";
			break;
		default:
			monthString = "Invalid Month";
			break;
		}
		return monthString;		

	}
	
	
	
	
	
}
