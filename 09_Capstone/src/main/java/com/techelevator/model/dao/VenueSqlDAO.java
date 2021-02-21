package com.techelevator.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.domain.Category;
import com.techelevator.model.domain.Reservation;
import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;

public class VenueSqlDAO implements VenueDAO {

	private JdbcTemplate jdbcTemplate;

	public VenueSqlDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Venue> getListOfAllVenues() {
		List<Venue> allVenues = new ArrayList<Venue>();

		String sql = "SELECT ROW_NUMBER ()OVER (ORDER BY venue.name ASC) AS row_id, venue.*, city.name AS city_name, state.name AS state_name "
				+
				// "SELECT venue.*, city.name AS city_name, state.name AS state_name" +
				" FROM venue" + " JOIN city ON venue.city_id = city.id "
				+ " JOIN state ON city.state_abbreviation = state.abbreviation " + " ORDER BY venue.name ASC ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {

			Venue venue = mapRowToVenue(results);
			allVenues.add(venue);

		}

		return allVenues;

	}

	@Override
	public List<Category> getCategoryListOfVenues() {
		List<Category> allCategoriesFromVenue = new ArrayList<Category>();
		String sql = "SELECT category.name AS category_name, venue.name AS venue_name" + "FROM category "
				+ "JOIN category_venue ON category_venue.category_id = category.id "
				+ "JOIN venue ON category_venue.venue_id = venue.id " + "WHERE venue.id = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {

			Category category = mapRowToCategory(results);
			allCategoriesFromVenue.add(category);

		}

		return allCategoriesFromVenue;
	}

	@Override
	public List<Space> getListOfSpacesInVenue() {
		List<Space> allSpacesFromVenue = new ArrayList<Space>();
		String sql = "SELECT space.daily_rate::money::numeric::float8 AS daily_rate, space.id AS id, space.name AS space_name, space.open_from AS open_from, space.open_to AS open_to, space.max_occupancy AS max_occupancy "
				+ "FROM space " + "JOIN venue ON space.venue_id = venue.id ";
		// "WHERE venue.id = ? ";
		// String sql = "SELECT * " + "FROM space ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {

			Space space = mapRowToSpace(results);
			allSpacesFromVenue.add(space);

		}

		return allSpacesFromVenue;

	}

	@Override
	public Venue getVenueById(int venueId) {
		Venue venue = null;

		String sql = "SELECT venue.*, city.name AS city_name, state.name AS state_name " + "FROM venue "
				+ " JOIN city ON venue.city_id = city.id "
				+ " JOIN state ON city.state_abbreviation = state.abbreviation " + " WHERE venue.id = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, venueId);

		if (result.next()) {
			venue = mapRowToVenue(result);
		}

		return venue;

	}

//Returns Booked Dates for requested Venue to be used in method(for each loop) to check availability;
	// Pay attention to the date return formatting, Ask Steve if Lost by Noon on
	// Friday.
	@Override
	public List<Space> getAvailability() {
		List<Space> reservedDatesInSpaces = new ArrayList<Space>();
		String sql = "select space.id AS id, space.name AS space_name, space.max_occupancy AS max_occupancy, space.daily_rate::money::numeric::float8 AS daily_rate, space.open_from AS open_from, space.open_to AS open_to "
				+ "FROM space " + "Left outer Join reservation ON space.id = reservation.space_id "
				+ "Join venue ON venue.id = space.venue_id " + "order by space_id ";
		// + "where venue.id = ?"; //MAY WANT TO UPDATE WHERE STATEMENT to target
		// specific space/venue as opposed to just venue not null.

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			// Crazy ass if statement HERE
			Space space = mapRowToSpace(results);
			reservedDatesInSpaces.add(space);

		}
		return reservedDatesInSpaces;

	}

	@Override
	public List<Space> getAvailableSpaces() {
		List<Space> availableSpaces = new ArrayList<Space>();
	
		String sql = "SELECT space.id AS id, space.name AS space_name, space.max_occupancy AS max_occupancy, "
				+ "space.daily_rate::money::numeric::float8 AS daily_rate, space.open_from AS open_from, space.open_to AS open_to "
				+ "FROM space "
				+ "WHERE (max_occupancy <=?) AND (open_from >= ? AND open_from <= ? OR open_from IS Null) AND space.id "
				+ "NOT IN " + "(" + "SELECT reservation.space_id " + "FROM reservation "
				+ "join space ON reservation.space_id = space.id "
				+ "WHERE (start_date <= ? AND end_date >=?) " // first place holder is requested
																					// start date, second ph is
																					// requested start date
				+ "OR (start_date < ? AND end_date >= ? ) " // first place holder is requested end
																					// date, second ph is requested end
																					// date
				+ "OR (? <= start_date AND ? >= end_date) "// first place holder is requested start
																				// date, second ph is requested end date
				+ ")";
		jdbcTemplate.queryForRowSet(sql );
		//ORDER FOR QUERY PlaceHolder VALUES: attendees, month of startdate, month of end date, inputstartdate, input startdate, input end date, input enddate, input startdate, input start date

		
		
		
		// Original Query			
//				"select space.id AS id, space.name AS space_name, space.max_occupancy AS max_occupancy, space.daily_rate::money::numeric::float8 AS daily_rate, space.open_from AS open_from, space.open_to AS open_to "
//				+ "FROM space " +
//				"Left outer Join reservation ON space.id = reservation.space_id "
//				+ "Join venue ON venue.id = space.venue_id " + "order by space_id ";
		// + "where venue.id = ?"; //MAY WANT TO UPDATE WHERE STATEMENT to target
		// specific space/venue as opposed to just venue not null.

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			Space space = mapRowToSpace(results);
			availableSpaces.add(space);

		}
		return availableSpaces;
	}

	@Override
	public List<Reservation> getAllReservations() {
		List<Reservation> allReservations = new ArrayList<Reservation>();

		String sql = "SELECT * " + "FROM Reservation";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {

			Reservation reservation = mapRowToReservation(results);
			allReservations.add(reservation);

		}

		return allReservations;
	}

	@Override
	public Reservation getReservationById(int reservationId) {
		Reservation reservation = null;

		String sql = "SELECT reservation.* " + "FROM reservation" + "WHERE reservation_id = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, reservationId);

		if (result.next()) {
			reservation = mapRowToReservation(result);
		}

		return reservation;

	}

	@Override
	public void createReservation(Reservation reservation) {

		int nextReservationId = getNextReservationId();
		// is reservation_id sequential since it is serial?
		String reservationSQL = "INSERT INTO reservation(reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) "
				+ "VALUES(?, ?, ?, ?, ?)";

		jdbcTemplate.update(reservationSQL, nextReservationId, reservation.getReservationId(),
				reservation.getNumberOfAttendees(), reservation.getStartDate(), reservation.getEndDate(),
				reservation.getReservedFor());

	}

	private Venue mapRowToVenue(SqlRowSet results) {
		Venue venue = new Venue();

		// venue.setRowId(results.getInt("row_id"));
		venue.setVenueId(results.getInt("id"));
		venue.setVenueName(results.getString("name"));
		venue.setDescription(results.getString("description"));
		venue.setCity(results.getString("city_name"));
		venue.setState(results.getString("state_name"));

//		Category category = new Category();
//	category.setCategoryId(results.getInt("category_name"));
//	category.setCategoryName(results.getString("venu_name"));
//
//	venue.setCategory(category);

		return venue;
	}

	private Category mapRowToCategory(SqlRowSet results) {
		Category category = new Category();
		category.setCategoryName(results.getString("category_name"));

		return category;
	}

	private Space mapRowToSpace(SqlRowSet results) {
		Space space = new Space();

		space.setSpaceId(results.getInt("id"));
		space.setSpaceName(results.getString("space_name"));
		space.setOpenFrom(results.getInt("open_from"));
		space.setOpenTo(results.getInt("open_to"));
		// space.setAccessible(results.getBoolean("isAccessible"));
		space.setDailyRate(results.getBigDecimal("daily_rate"));

		return space;
	}

	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation reservation = new Reservation();

		reservation.setReservationId(results.getInt("reservation_id"));
		reservation.setNumberOfAttendees(results.getInt("number_of_attendees"));
		reservation.setStartDate(results.getString("start_date"));
		reservation.setEndDate(results.getString("end_date"));
		reservation.setReservedFor(results.getString("reserved_for"));

		return reservation;
	}

	private int getNextReservationId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new reservation");
		}
	}

//	If (allReservations.get(getStartDate())))//(reservation.getStartDate() = null);{}
//		if (open_from = null) {
//			if(attendees < max_occupancy){
//				mapRowToSpace(SqlRowSet results);
//				}
//			}
//		else if (open_from !=null){
//			if (startMonth > open_from && startMonth < open_to){ //check for end date
//				if(attendees < max_occupancy){
//				mapRowToSpace(SqlRowSet results);
//			
//				}

//	ELSE IF(start_date != Null){
//		if ((userStartDate > start_date && userStartDate < end_date) || (userEndDate > start_date && userEndDate < end_date) ) { //Having trouble here with thought process
//			getNextLine(); //maybe wrong code
//	    }
//
//	    else if (open_from = null) {
//			if(attendees < max_occupancy){
//
//				mapRowToSpace(SqlRowSet results);
//			
//				}
//			}
//		else if(	Open from !=null){
//
//			if (start month > open_from && start month < open_to){
//
//				if(attendees < Max Occupancy){
//
//				mapRowToSpace(SqlRowSet results);
//	            }
//	        
//	    }

}
