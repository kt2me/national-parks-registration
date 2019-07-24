package com.techelevator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import com.techelevator.dbclasses.JDBCReservationDAO;

public class JDBCReservationDAOIntegrationTest extends DAOIntegrationTest{

	private JDBCReservationDAO dao;
	
	//testing that reservation was added with 4
	@Test
	public void add_Reservation_to_table_sucessful() {
												   //ReservationDAO interface params
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
		int afterCount = (dao.addReservationToTable( 622, "Test", formatter.parse("2019-07-01"), formatter.parse("2019-07-05"), LocalDate.parse("2019-06-24")));
		//still relies on data from database. need to fix in IntegrationTestDAO by creating new data to test from.
		Assert.assertEquals(4, afterCount); 
		} catch(Exception formatError) { 
			System.out.println("Format Error");
		}
	}
}
