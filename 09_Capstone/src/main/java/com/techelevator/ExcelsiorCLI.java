package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.dao.VenueSqlDAO;
import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;
import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelsiorCLI {

	private Menu menu;
	private VenueDAO venueDAO;
    private BigDecimal days;
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
				
				//menu.printAvailableSpaces(availableSpaces); hold for later
				handleReservationSubMenu();
				//create submenumethod for here
				//menu.makeReservationMenu();
				
			}
			else if (submenuChoice.equalsIgnoreCase("r")) {
				isLooping= false;
			
			}
		}
		}
		public void handleReservationSubMenu() {
			boolean reservationRunning = true;
			
				Date startDate = null;
				SimpleDateFormat dateFormat;
				menu.startReservationProcess();
			while(reservationRunning) {
//	newReservation.getstartdate();
//	newReservation.getEndDate();
//	newReservation.getNumberOfAttendees();
	
	
			///******Keep for calendar stuff	
//				try {
//					startDate = new SimpleDateFormat("MM-dd-yyyy").parse(startDateinput);
//					
//				
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//				Calendar cal =Calendar.getInstance();
//				cal.setTime(startDate);
//				
//				
//				cal.add(Calendar.DAY_OF_MONTH, daysForReservation);
//				Date endDate = cal.getTime();
//				
//			
//				
//				List<Space> spacesToReserve = venueDAO.getAvailability();
//				if (startDate < getOpenFrom() && openFrom && openTo = null) {
//					
//					
				//}
				//menu.printSpacesAvailabeleForReservation(spacesToReserve);
				//variables to be used
				//start date
				//endDate
				//Attendees
				
//				Date arrivalDate = null;
//				System.out.println("What is the arrival date? yyyy-mm-dd");
//				String arrivalDateS = input.nextLine();
//				int arrivalMonth = Integer.parseInt(arrivalDateS.substring(5, 7));
//				try {
//					arrivalDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDateS);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				LocalDate localArrivalDate = LocalDate.parse(arrivalDateS);
//				System.out.println("What is the departure date? yyyy-mm-dd");
//				String departureDateS = input.nextLine();

			
			
			
			
//			} 
				
				
//				Prompted			System.out.println("How many days will you need the space?");
			
				}
				
			
				
			
			
				
				
// days = int
				
				
//	Prompted			System.out.println("How many people will be in attendance?");
// attendees = int
			}
			
		
				
//				scanner.nextLine();

//				scanner.nextLine();

//				scanner.nextLine();
				// checks input against our list
				// prints screen of spaces available based on needs
	
			}
			
			
		
	


				
				
				
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
				
			
