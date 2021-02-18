package com.techelevator.model.domain;

public class Space {

	
	private int spaceId;
	private String spaceName;
	private boolean isAccessible;
	private int openFrom;
	private int openTo;
	private double dailyRate;
	private String maxOccupancy;
	
	
	

	
	//getters and setters
	public int getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	public int getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(int openFrom) {
		this.openFrom = openFrom;
	}
	public int getOpenTo() {
		return openTo;
	}
	public void setOpenTo(int openTo) {
		this.openTo = openTo;
	}
	public double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	public String getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(String maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	
	
}
