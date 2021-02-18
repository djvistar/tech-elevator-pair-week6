package com.techelevator.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.domain.Category;
import com.techelevator.model.domain.Space;
import com.techelevator.model.domain.Venue;

public class VenueSqlDAO implements VenueDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public VenueSqlDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Venue> getListOfAllVenues() {
        List<Venue> allVenues= new ArrayList<Venue>();
		
		String sql = "SELECT venue.id, venue.name, city.name, state.name, venue.description" +
				"FROM venue" +
				"JOIN city ON venue.city_id = city.id" +
				"JOIN state ON city.state_abbreviation = state.abbreviation" +
				"ORDER BY venue.name ASC";
				
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		
		while (results.next()) {
			
			Venue venue = mapRowToVenue(results);
			allVenues.add(venue);
			
		}
		
		
		return allVenues;
	}

	
	
	
	@Override
	public List<Category> getCategoryListOfVenues() {
		 List<Category> allCategoriesFromVenue= new ArrayList<Category>();
		 String sql = "SELECT category.name, venue.name " +
	             "FROM category " +
			     "JOIN category_venue ON category_venue.category_id = category.id" +
	             "JOIN category_venue ON category_venue.venue_id = venue.id";
		 
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			
			
			while (results.next()) {
				
				Category category = mapRowToCategory(results);
				allCategoriesFromVenue.add(category);
				
			}
		 
		return allCategoriesFromVenue;
	}
	
	
	@Override
	public List<Space> getListOfSpacesInVenue() {
		 List<Space> allSpacesFromVenue= new ArrayList<Space>();
		 String sql = "SELECT space.*, venue.name" +
	             "FROM space" +
	             "JOIN venue ON space.venue_id = venue_id";
		 
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			
			
			while (results.next()) {
				
				Space space = mapRowToSpace(results);
				allSpacesFromVenue.add(space);
				
			}
		 
		return allSpacesFromVenue;
		
	}
	
	
	@Override
	public Venue getVenueById(String venueId) {
	Venue venue = null;
		
		String sql = "SELECT venue.* " +
	             "FROM venue " + "WHERE venue_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, venueId);
		
		if (result.next()) {
			venue = mapRowToVenue(result);
		}
		
		
		return venue;
		
	}

	




	@Override
	public List<Venue> getAvailability() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	
	private Venue mapRowToVenue(SqlRowSet results) {
		Venue venue = new Venue();
		
	    venue.setVenuId(results.getInt("venueId"));
		venue.setVenuName(results.getNString("venuName"));
		venue.setDescription(results.getString("description"));
	    venue.setCity(results.getString("city"));
        venue.setState(results.getString("state"));
		
        Category category = new Category();
        category.setCategoryId(results.getInt("categoryId"));
        category.setCategoryName(results.getString("categoryName"));
       
        venue.setCategory(category);
        
		return venue;
	}	
	
	private Category mapRowToCategory(SqlRowSet results) {
		 Category category = new Category();
	        category.setCategoryId(results.getInt("categoryId"));
	        category.setCategoryName(results.getString("categoryName"));
	        
		return category;
	}


	private Space mapRowToSpace(SqlRowSet results) {
		Space space = new Space();
		
		space.setSpaceId(results.getInt("spaceId"));
		space.setSpaceName(results.getString("sapceName"));
		space.setOpenFrom(results.getInt("openFrom"));
		space.setOpenTo(results.getInt("openTo"));
		space.setAccessible(results.getBoolean("isAccessible"));
		space.setMaxOccupancy(results.getString("maxOccupancy"));
		space.setDailyRate(results.getDouble("dailyRate"));
		
		return space;
	}

	
	
	
	
}
