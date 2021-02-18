package com.techelevator.model.domain;

public class Venue {
	
	private int venuId;
	private String venuName;
	private String description;
	private Category category; // UPDATE LATER
	private String city;
	private String state;
	private Space space;
	
	
	
	//getters and setters
	public int getVenuId() {
		return venuId;
	}
	public void setVenuId(int venuId) {
		this.venuId = venuId;
	}
	public String getVenuName() {
		return venuName;
	}
	public void setVenuName(String venuName) {
		this.venuName = venuName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}

	
}
