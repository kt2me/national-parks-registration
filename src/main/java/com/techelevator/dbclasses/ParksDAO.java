package com.techelevator.dbclasses;

import java.util.List;

public interface ParksDAO {
	
	public List<Parks> displayAllParks();
	
	public List<Parks> displayParkInformation(int id);

}
