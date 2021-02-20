package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.dao.VenueSqlDAO;
import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;
import com.techelevator.view.Menu;

import java.util.List;

public class ExcelsiorCLI {

	private Menu menu;
	private VenueDAO venueDAO;

	public ExcelsiorCLI(DataSource datasource) {
		this.menu = new Menu();
		venueDAO = new VenueSqlDAO(datasource);

	}

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	// MENU TITLE METHODS
	// String makeReservationMenu()
	// String venueSpacesMenu()
	// String spaceDetailsMenu()
	// String venueDetailsMenu()

	// Reminder: No System.out.printlns in this class
	public void run() {

		boolean running = true;

		while (running) {

			String choice = menu.printMainMenu();

			if (choice.equals("1")) {

				List<Venue> listAllVenues = venueDAO.getListOfAllVenues();
				menu.printListOfVenues(listAllVenues);
				
				handleSubmenuOfAllVenues();

			} else if (choice.equalsIgnoreCase("q")) {

				running = false;
			}
			menu.closeProgram();
		}
	}

	
	private void handleSubmenuOfAllVenues() {
		boolean venuemenurunning = true;
		while(venuemenurunning) {
		menu.menuOfAllVenues();
		
		String userInputListChoice = menu.menuOfAllVenues(); //venuelistchoice is our row_id we're searching for
		int intVenueChoiceForSearch = Integer.parseInt(userInputListChoice);
		Venue venue = venueDAO.getVenueById(intVenueChoiceForSearch);
		
		menu.printVenueDetails(venue);
		handleSubMenu();

	}


	}
	private void handleSubMenu() {

		boolean isLooping = true;
		while (isLooping) {

			String submenuChoice = menu.venueDetailsMenu();
			if (submenuChoice.equalsIgnoreCase("1")) {

				List<Space> listOfSpaces = venueDAO.getListOfSpacesInVenue();

				menu.printListOfSpace(listOfSpaces);
			}
			
			else if(submenuChoice.equalsIgnoreCase("2")) {
				List<Space> availableSpaces = venueDAO.getAvailability();
				
				menu.printAvailableSpaces(availableSpaces);
				
				//get user input for date, days, and venue, and occupants
				// Availability check
//				 if userinput start date > reservation startdate && userinput start date <reservation end date
//				then not available
//				 next line
//				else if 
//						if userinput end date > reservation startdate && userinput end date <reservation end date
//				 then not available
//				next line
//				else if open month is null---available add to list
//				 else if requested date outside of open from and open to, not available, next line
//				else 
//				input into list as Available
				
			}
			else if (submenuChoice.equalsIgnoreCase("r")) {
				isLooping= false;
			
			}

		}
		
	}

}
