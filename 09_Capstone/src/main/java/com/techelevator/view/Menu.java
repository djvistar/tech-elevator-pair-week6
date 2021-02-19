package com.techelevator.view;

import java.util.List;
import java.util.Scanner;


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

	}
 public void printVenue(Venue venue) {
	 
	 int i =1;
	
	 if (venue == null) {
			System.out.println("No results found... Pleast try again.");
			return;
		} else {
			 
			System.out.println( i +" " + venue.getVenueName());
			 i++;
		}
	 
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
			System.out.println(String.format("%-25s $%s", "State: ", venue.getState()));
			System.out.println(String.format("%-25s $%s", "Categories: ", venue.getCategory()));
			System.out.println("\n*********** *** *** ***  ************\n");

		}
	}

}
