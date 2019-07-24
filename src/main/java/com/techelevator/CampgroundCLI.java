package com.techelevator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.dbclasses.CampgroundDAO;
import com.techelevator.dbclasses.JDBCCampgroundDAO;
import com.techelevator.dbclasses.JDBCParksDAO;
import com.techelevator.dbclasses.JDBCReservationDAO;
import com.techelevator.dbclasses.Parks;
import com.techelevator.dbclasses.ParksDAO;
import com.techelevator.dbclasses.ReservationDAO;


public class CampgroundCLI {

private Menu menu;
private ParksDAO parksDAO;
private ReservationDAO reservationDAO;
private CampgroundDAO campgroundDAO;
private List<Parks> parks;
private Parks desiredPark;
private int desiredSite;
private Date arriveDate;
private Date departDate;

public static void main(String[] args) throws ParseException {
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}

public CampgroundCLI() {
		this.menu = new Menu(System.in, System.out);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		parksDAO = new JDBCParksDAO(dataSource);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}

public void run() throws ParseException {
		getParks();
		mainMenu();
	}
			
 public void mainMenu() throws ParseException{
		while (true){
		menu.displayParkOptions(parks);
		int userInput = menu.getUserInput();
		desiredPark = parks.get(userInput-1);
		menu.displayParkInformation(desiredPark);
		commandDisplay();
		}	
	}
 
 public void commandDisplay() throws ParseException{
	 while(true) {
		 menu.displayCommandInformation();
		 int userInput = menu.getUserInput();
		 if(userInput==1) {
			 menu.displayCampgroundOptions(campgroundDAO.displayAllCampgrounds(desiredPark.getId()),desiredPark);
		 }else if (userInput==2){
			 handleProposedReservationSite();
		 }else if(userInput==3) {
			 mainMenu();
		 }
		 }
	 }


public void handleProposedReservationSite() throws ParseException{
	try {
	desiredSite = menu.getSiteChoice();
	handleProposedArriveDate();
	} catch (InputMismatchException e){
		handleProposedReservationSite();
	}
	}

SimpleDateFormat formatter1= new SimpleDateFormat("MM/dd/yyyy"); 
SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

public void handleProposedArriveDate() throws ParseException{
	String proposedArriveDate = menu.getArriveDate();
	try {
		arriveDate = formatter1.parse(proposedArriveDate);
	} catch (ParseException e) {
		menu.incorrectDateMessage();
		handleProposedArriveDate();
	}
	handleProposedDepartDate();
}

public void handleProposedDepartDate() throws ParseException {
	String proposedDepartDate = menu.getDepartDate();
	try {
		departDate = formatter1.parse(proposedDepartDate);
	} catch (ParseException e) {
		menu.incorrectDateMessage();
		handleProposedDepartDate();
	}
	showAvailableSites();
	}

	

public void showAvailableSites() throws ParseException{
	menu.displayAvailableSites(reservationDAO.getAvailableSites(desiredPark.getId(), desiredSite, arriveDate, departDate));
	makeReservation();
}
 

public void makeReservation() throws ParseException {
	int choice = menu.getReservationSite();
	if(reservationDAO.reservationAvailable(choice, arriveDate, departDate)) {
		String reservationName = menu.getReservationName();
		LocalDate createDate = LocalDate.now();
		int reservationId = reservationDAO.addReservationToTable(choice, reservationName, arriveDate, departDate, createDate);
		menu.provideReservationNumber((reservationId));	
	}else {
		menu.siteNotAvailable();		
		makeReservation();
	
	}
}

public void getParks(){
	parks = parksDAO.displayAllParks();
	}
	
	
}
