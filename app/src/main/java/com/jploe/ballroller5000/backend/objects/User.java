package com.jploe.ballroller5000.backend.objects;

import com.jploe.ballroller5000.backend.services.BallService;
import com.jploe.ballroller5000.backend.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<Ball> ballsThatAreRolling;
	private List<Ball> allBalls;
	private Location currentLocation;
	private List<Friend> friends;
	private BallService ballService;
	private UserService userService;
	private Integer Id;
	
	public User (String name){
		this.name = name;
		ballService = new BallService();
		userService = new UserService();
		this.allBalls = new ArrayList<Ball>();
		this.ballsThatAreRolling = new ArrayList<Ball>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ball> getBallsThatAreRolling() {
		return ballsThatAreRolling;
	}
	
	public List<Ball> getAllBalls() {
		return ballService.getAllBallsForUser(this);
	}	
	public Location getCurrentLocation() {
		return userService.getCurrentLocation();
	}
	
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	public List<Friend> getFriends() {
		return friends;
	}
	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	
	public boolean addFriend (Friend friend){
		return this.friends.add(friend);
	}
	
	public Ball rollBall(Ball ballToBeRolled, int velocity){
		Ball ball = ballService.rollBall(ballToBeRolled, velocity,this);
		addRollingBall(ball);
		return ball;
	}
	
	private boolean addRollingBall(Ball ball) {
		return this.ballsThatAreRolling.add(ball);
	}

	public Ball createBall(String name, double diameter){
		
		Ball ball = ballService.createBall(diameter,name,this, getCurrentLocation());
		addBall(ball);
		return ball;
	}

	private boolean addBall(Ball ball) {
		return this.allBalls.add(ball);
	}

	public void connect() {
		userService.connect(this);
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Ball getBallByName(String ballName) {
		return ballService.getBallByUserAndBallName(this,ballName);
	}
	

}
