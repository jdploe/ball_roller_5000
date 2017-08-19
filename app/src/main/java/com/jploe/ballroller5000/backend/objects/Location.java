package com.jploe.ballroller5000.backend.objects;

public class Location {
	private float longitude;
	private float latitude;
	
	
	public Location(float longitude, float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
}
