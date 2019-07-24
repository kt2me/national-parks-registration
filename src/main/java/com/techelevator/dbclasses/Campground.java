package com.techelevator.dbclasses;


public class Campground {
	
	private int campgroundId;
	private String name;
	private String openFrom;
	private String openTo;
	private double dailyFee;
	private int parkId;
	
	public int getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}
	public String getOpenTo() {
		return openTo;
	}
	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double d) {
		this.dailyFee = d;
	}
	
	public void setParkId(int id) {
		this.parkId= id;
	}
	
	public int getParkId() {
		return parkId;
	}

}
