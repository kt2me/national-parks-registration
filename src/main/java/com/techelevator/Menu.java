package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import com.techelevator.dbclasses.Campground;
import com.techelevator.dbclasses.Parks;
import com.techelevator.dbclasses.Reservation;

public class Menu {
	private PrintWriter out;
	private Scanner in;

public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);	
	}	

public void displayParkOptions(List<Parks>parks){
	System.out.println("Please choose an option >>> ");
	for(Parks park : parks) {
		System.out.println(park.getId()+"||"+park.getName());
	}
	
}


public void displayParkInformation(Parks park) {
	System.out.println("\nPark Information Screen");
	System.out.println(park.getName() + " National Park");
	System.out.println("Location: " + park.getLocation());
	System.out.println("Established: " + park.getEstablishDate());
	System.out.println("Area: " + park.getArea() + " sq miles");
	System.out.println("Annual Vistors: " + park.getVisitors() + "\n");
	System.out.println(park.getDescription());
	
}
	
public void displayCommandInformation() {
	System.out.println("\nSelect a Command");
	String[] commands = {"1)View Campgrounds", "2)Search for Available Reservation", "3)Return to Previous Screen"};
	for(String command : commands) {
		System.out.println(command);
	}
}


public void displayCampgroundOptions(List <Campground> campgrounds, Parks park) {
	System.out.println("\nPark Campgrounds");
	System.out.println(park.getName() + " National Park Campgrounds\n");
	System.out.printf("%-15s %-50s %-15s %-15s %15s", "Campground ID", "Name", "Opens", "Closes", "Rate");
	for(Campground place : campgrounds) {
		System.out.printf("%n %-15s %-50s %-15s %-15s %15s %n","#"+ place.getCampgroundId(), place.getName(),place.getOpenFrom(), place.getOpenTo(), "$"+place.getDailyFee());
	}
}


public void displayAvailableSites(List<Reservation> reservations) {
	System.out.println("\nResults Matching Your Search Criteria");
	System.out.printf("%-25s %-10s %-10s %-10s %-15s %-15s %-15s", "Campground Name", "Campsite #", "Max Guests", "Max RV's", "Accessible?", "Utitilies?", "Rate");
	for(Reservation place : reservations) {
		System.out.printf("%n %-25s %-10s %-10s %-10s %-15s %-15s $%-15s %n", place.getCampgroundName(), place.getSiteNumber(), place.getMax(), place.getMaxRV(), place.isAccesible(), place.isUtilities(), place.getFee());
		System.out.println("\n");
	}
}


public int getSiteChoice() {
	System.out.println("\nWhich site should be reserved (enter 0 to cancel)? __");
	int site = getUserInput();
	return site;
}

public String getArriveDate() {
	System.out.println("When will you be arriving? __/__/____");
	String arrive = getUserInputString();
	return arrive;
}

public String getDepartDate() {
	System.out.println("When will you be leaving? __/__/____");
	String depart = getUserInputString();
	return depart;
}

public void incorrectDateMessage() {
	System.out.println("Please enter a valid date!");
}

public String getReservationName(){
System.out.println("What name would you like to use for the reservation?: ");
String reservationName = getUserInputString();
return reservationName;
}


public int getReservationSite(){
System.out.println("What site would you like to book?: ");
int reservationSite = getUserInput();
return reservationSite;
}


public int getUserInput() {
	int userInput = in.nextInt();
	in.nextLine();
	return userInput;
}
	
public String getUserInputString() {
String userInputString = in.nextLine();
return userInputString;
}


public void provideReservationNumber(int id) {
System.out.println("You're booked! You're reservation number is " + id);
}

public void siteNotAvailable() {
System.out.println("Sorry! It looks like the site is not available for that date range.");
}
}
