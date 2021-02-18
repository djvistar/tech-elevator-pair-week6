package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.domain.Category;
import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;



public interface VenueDAO {

	  List<Venue> getListOfAllVenues();
	  List<Category>getCategoryListOfVenues();
	  List<Venue> getAvailability();
	  List<Space> getListOfSpacesInVenue();
	Venue getVenueById(String venueId);
	  
	
	
}
