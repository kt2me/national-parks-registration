package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.dbclasses.Campground;

public class CampgroundTest {
	
	private Campground unitTestCampground;
	
	@Before
	public void setup() {
		//inst. campground()
		unitTestCampground = new Campground();
	}
	
	@Test
	public void unit_testing_campground_getters_and_setters() {
		
		//getters tests.
		int campGroundId = unitTestCampground.getCampgroundId();
		String name = unitTestCampground.getName();
		String openFrom = unitTestCampground.getOpenFrom();
		String openTo = unitTestCampground.getOpenTo();
		double dailyFee = unitTestCampground.getDailyFee();
		int parkId = unitTestCampground.getParkId();
		//setters tests.
		unitTestCampground.setName("test");
		unitTestCampground.setOpenFrom("01");
		unitTestCampground.setOpenTo("01");
		unitTestCampground.setOpenTo("12");
		unitTestCampground.setDailyFee(5.00);
		unitTestCampground.setDailyFee(-5.00);
		unitTestCampground.setParkId(1);
	}
}
