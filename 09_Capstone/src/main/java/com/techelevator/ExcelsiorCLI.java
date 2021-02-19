package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.dao.VenueDAO;
import com.techelevator.model.dao.VenueSqlDAO;
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
	

	
	
	//MENU TITLE METHODS
	//String makeReservationMenu() 
	//String venueSpacesMenu() 
	//String spaceDetailsMenu()
	//String venueDetailsMenu()
	
	
	// Reminder: No System.out.printlns in this class
	public void run() {

		boolean running = true;
		
			while (running) {
				
				String choice = menu.printMainMenu();
			
				if (choice.equals("1")) {
					
					
					List<Venue>listAllVenues = venueDAO.getListOfAllVenues();
					menu.printListOfVenues(listAllVenues);
	
			
				}
						
			}

		
		
		
		
		
		// Availability check
//		 if userinput start date > reservation startdate && userinput start date <reservation end date
//		then not available
//		 next line
//		else if 
//				if userinput end date > reservation startdate && userinput end date <reservation end date
//		 then not available
//		next line
//		else if open month is null---available add to list
//		 else if requested date outside of open from and open to, not available, next line
//		else 
//		input into list as Available

	}
}
