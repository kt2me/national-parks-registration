package com.techelevator.dbclasses;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCParksDAO implements ParksDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCParksDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
}

	@Override
	public List<Parks> displayAllParks() {
		String sql = "SELECT * FROM park ORDER by name";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		
		List<Parks> allParks = new ArrayList<Parks>();
		while(result.next()) {
			allParks.add(mapRowToParks(result));
		}
		return allParks;
	}

	@Override
	public List <Parks> displayParkInformation(int id) {
		String sql = "SELECT * FROM park WHERE park_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		
		List<Parks>allParks = new ArrayList<Parks>();
		
		if(result.next()) {
			allParks.add(mapRowToParks(result));
		}
		return allParks;
	}
	
	private Parks mapRowToParks(SqlRowSet result) {
		Parks p = new Parks();
		p.setId(result.getInt("park_id"));
		p.setName(result.getString("name"));
		p.setEstablishDate(result.getDate("establish_date"));
		p.setArea(result.getInt("area"));
		p.setVisitors(result.getLong("visitors"));
		p.setLocation(result.getString("location"));
		p.setDescription(result.getString("description"));
		return p;
	}
	
}