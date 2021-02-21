package com.techelevator.view;

import java.util.List;
import java.util.Scanner;

import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;

public class Menu {

	private Scanner scanner;

	public Menu() {
		scanner = new Scanner(System.in);

	}

	public String printMainMenu() {

		System.out.println("Please Make A Selection From Below:");
		System.out.println("1 List of Venues");
		System.out.println("Q.  Quit");

		return scanner.nextLine();
	}

	public String menuOfAllVenues() {
		// need to look at output with list of venues and appropriate choices in menu
		// Print to screen list of Venues
		System.out.println("Which venue would you like to view?");
		System.out.println("Enter Number of the venue");
//		System.out.println();
//		System.out.println();
//		System.out.println();
		
		return scanner.nextLine();
	}

	public String venueDetailsMenu() {

		System.out.println("Please Make A Selection From Below:");
		System.out.println("1. View Spaces"); // Prints list of spaces in venue with details, goes to space Details menu
		System.out.println("2. Search for a Reservation");
		System.out.println("R. Return to previous menu");

		return scanner.nextLine();
	}

	public String spaceDetailsMenu() {
		// print screen list of spaces
		System.out.println("1. Reserve a Space");
		System.out.println("R. Return to previous menu");
		return scanner.nextLine();

	}

	public String venueSpacesMenu() {
		// Print Spaces List
		System.out.println("Please Make A Selection From Below:");
		System.out.println("1. Reserve a Space");
		System.out.println("R. Return to previous menu");

		return scanner.nextLine();
	}

	public String makeReservationMenu() {

		System.out.println("When do you need the space?");
		scanner.nextLine();
		System.out.println("How many days will you need the space?");
		scanner.nextLine();
		System.out.println("How many people will be in attendance?");
		scanner.nextLine();
		
		// checks input against our list
		// prints screen of spaces available based on needs

		return scanner.nextLine();
	}

	public void printListOfVenues(List<Venue> venuesToPrint) {

		System.out.println("\n********* Venue Results **********\n");

		if (venuesToPrint.isEmpty()) {
			System.out.println("No Results Found!");
			return;
		}

		for (Venue venue : venuesToPrint) {

			printVenue(venue);
		}
		System.out.println("\n************************************\n");
		// functionality, print the pick a venue menu here;
	}

	public void printVenue(Venue venue) {

		if (venue == null) {
			System.out.println("No results found... Pleast try again.");
			return;
		} else {

			System.out.println(String.format("%-5s %-35s %-5s %s", "Venue Name: ", venue.getVenueName() ," Venue Number: ", venue.getVenueId())); //venue.getRowId() + " " +

		}
		//"-25s, %s"
	}

	public void printVenueDetails(Venue venue) {

		if (venue == null) {
			System.out.println("No results found... Pleast try again.");
			return;
		} else {

			System.out.println(venue.getVenueId());

			System.out.println(String.format("%-25s %s", "Venue ID: ", venue.getVenueId()));
			System.out.println(String.format("%-25s %s", "Venue Name: ", venue.getVenueName()));
			System.out.println(String.format("%-25s %s", "City: ", venue.getCity()));
			System.out.println(String.format("%-25s %s", "State: ", venue.getState()));
			System.out.println(String.format("%-25s %s", "Categories: ", venue.getCategory()));
			System.out.println("\n*********** *** *** ***  ************\n");

		}
	}
	public void printListOfSpace(List<Space> SpacesToPrint) {

		System.out.println("\n********* Venue Results **********\n");

		if (SpacesToPrint.isEmpty()) {
			System.out.println("No Results Found!");
			return;
		}

		for (Space space : SpacesToPrint) {

			printSpace(space);
		}
		System.out.println("\n************************************\n");
		// functionality, print the pick a venue menu here;
	}
	public void printSpace(Space space) {
		
		//System.out.println(space.getSpaceId()+" "+space.getSpaceName()+" "+space.isAccessible()+" " + space.getOpenFrom()+ " "+ space.getOpenTo()+" "+space.getDailyRate()+space.getMaxOccupancy());
		System.out.println(String.format("%-5s %-35s %-10s %-25s %-25s %s", space.getSpaceId(), space.getSpaceName(), space.getOpenFrom(), space.getOpenTo(), space.getDailyRate(), space.getMaxOccupancy())); //venue.getRowId() + " " +

		
		
	}

	public void closeProgram() {

		System.out.println("Exiting Program");
	}

	public void invalidChoice() {

		System.out.println("invalid choice, Try Again.");
	}

	
	
	public void printAvailableSpaces(List<Space> availableSpaces) {
		
		System.out.println("\n********* Available Spaces  **********\n");
		System.out.println(String.format("%-5s %-35s %-10s %-25s %-25s %s", "Id", "Name", "Open", "Close", "Daily Rate", "Max. Occupancy\n")); //venue.getRowId() + " " +
		System.out.println(" \n                                                       \n ");

		if (availableSpaces.isEmpty()) {
			System.out.println("No Results Found!");
			return;
		}

		for (Space space : availableSpaces) {

			printSpace(space);
		}
		System.out.println("\n************************************\n");
		
	}

	
	public void printSpacesAvailable(Space space) {
		
		//System.out.println(space.getSpaceId()+" "+space.getSpaceName()+" "+space.isAccessible()+" " + space.getOpenFrom()+ " "+ space.getOpenTo()+" "+space.getDailyRate()+space.getMaxOccupancy());
		System.out.println(String.format("%-5s %-35s %-10s %-25s %-25s %s", space.getSpaceId(), space.getSpaceName(), space.getDailyRate(), space.getMaxOccupancy())); //venue.getRowId() + " " +

		
		
	}
	
	public void printSpacesAvailabeleForReservation(List<Space> spacesToReserve) {
		System.out.println("\n********* Available Spaces  **********\n");
		System.out.println(String.format("%-5s %-35s %-10s %-25s %s", "Id", "Name", "Daily Rate", "Max. Occupancy", "Total Cost\n")); //venue.getRowId() + " " +
		System.out.println(" \n                                                       \n ");

		if (spacesToReserve.isEmpty()) {
			System.out.println("No Results Found!");
			return;
		}

		for (Space space : spacesToReserve) {

			printSpace(space);
		}
		System.out.println("\n************************************\n");
		
	}

	
	
}
