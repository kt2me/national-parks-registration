package com.techelevator.dbclasses;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationDAO {
	
	public List <Reservation> getAvailableSites(int desiredPark, int desiredCampGround, Date fromDate, Date toDate);
	//public void reservationAvailable(List <Reservation> campsites, int requestedSite);

	public boolean reservationAvailable(int site, Date fromDate, Date toDate);
	//public void makeReservation(int site, Date FromDate, Date toDate, String reservationName);
	public int addReservationToTable(int site, String reservationName, Date fromDate, Date toDate, LocalDate createDate);


}
