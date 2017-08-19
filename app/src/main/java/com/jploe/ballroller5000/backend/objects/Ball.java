package com.jploe.ballroller5000.backend.objects;


import java.util.Date;

public class Ball {
	private Long id;
	private double diameter;
	private boolean isRolling;
	private Location startingLocation;
	private double velocity;
	private String name;
	private Date startingDate;

	public Ball (double diameter, String name, Location startingLocation){
		this.diameter = diameter;
		this.startingLocation = startingLocation;
		this.startingDate = new Date();
		this.name = name;
	}
	
	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public boolean isRolling() {
		return isRolling;
	}

	public void setRolling(boolean isRolling) {
		this.isRolling = isRolling;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public Location getStartingLocation() {
		return startingLocation;
	}

	public void setStartingLocation(Location startingLocation) {
		this.startingLocation = startingLocation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
