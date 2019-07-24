package com.techelevator.dbclasses;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCReservationDAO implements ReservationDAO{
	private JdbcTemplate jdbcTemplate;
	private Reservation reservation;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
}

	@Override
	public List <Reservation> getAvailableSites(int desiredPark, int desiredCampGround, Date fromDate, Date toDate) {
		String sql = "SELECT campground.name, site.site_id, site.max_occupancy, site.accessible, site.utilities, campground.daily_fee FROM reservation RIGHT JOIN site ON site.site_id = reservation.site_id RIGHT JOIN campground ON campground.campground_id = site.campground_id RIGHT JOIN park ON campground.park_id = park.park_id WHERE park.park_id = ? AND campground.campground_id =? AND ((to_date NOT BETWEEN ?  AND ? AND from_date NOT BETWEEN ? AND ?) OR(to_date IS NULL AND from_date IS NULL))LIMIT 5";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, desiredPark, desiredCampGround, fromDate, toDate, fromDate, toDate);
		
		List<Reservation>allSites = new ArrayList<Reservation>();
		
		while(result.next()) {
			allSites.add(mapRowToSite(result));
		}
		return allSites;
	}
	
	@Override
	public boolean reservationAvailable(int site, Date fromDate, Date toDate) {
		String sql = "SELECT site.site_id FROM site FULL OUTER JOIN reservation ON reservation.site_id = site.site_id WHERE site.site_id = ? AND ((to_date NOT BETWEEN ? AND ? AND from_date NOT BETWEEN ? AND ?) OR (to_date IS NULL AND from_date IS NULL))";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, site, fromDate, toDate, fromDate, toDate);
		
		if(result.next()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public int addReservationToTable(int site, String reservationName, Date fromDate, Date toDate, LocalDate createDate) {
		
		reservation = new Reservation();

		String sqlNextResId = "SELECT nextval('reservation_reservation_id_seq')";
		SqlRowSet resultID = jdbcTemplate.queryForRowSet(sqlNextResId);
		resultID.next();
		int nextResID = resultID.getInt(1);
		
		String sql = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, nextResID, site, reservationName, fromDate, toDate, createDate);
			
			reservation.setReservation_id((int)(nextResID));
			reservation.setSiteNumber(site);
			reservation.setReservationName(reservationName);
			reservation.setFromDate(fromDate);
			reservation.setToDate(toDate);
			reservation.setCreateDate(createDate);
			
			return reservation.getReservation_id();
		
	}

	
	private Reservation mapRowToSite(SqlRowSet result) {
		Reservation site = new Reservation();
		site.setCampgroundName(result.getString("name"));
		site.setSiteNumber(result.getInt("site_id"));
		site.setMax(result.getInt("max_occupancy"));
		site.setAccesible(result.getBoolean("accessible"));
		site.setUtilities(result.getBoolean("utilities"));
		site.setFee(result.getDouble("daily_fee"));
	
		return site;
	}

	


	}