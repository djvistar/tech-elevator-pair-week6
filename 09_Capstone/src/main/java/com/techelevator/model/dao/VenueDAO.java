package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.domain.Category;
import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;



public interface VenueDAO {

	  List<Venue> getListOfAllVenues();
	  List<Category>getCategoryListOfVenues();
	  List<Space> getAvailability();
	  List<Space> getAvailableSpaces();
	  List<Space> getListOfSpacesInVenue();
	  Venue getVenueById(int venueId);
	  List<Reservation>getAllReservations();
	  Reservation getReservationById(int reservationId);
	  void createReservation(Reservation reservation);
	 
	
	
}
