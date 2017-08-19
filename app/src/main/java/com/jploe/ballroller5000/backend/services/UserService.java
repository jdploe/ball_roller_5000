package com.jploe.ballroller5000.backend.services;

import com.jploe.ballroller5000.backend.database.BallDao;
import com.jploe.ballroller5000.backend.objects.Location;
import com.jploe.ballroller5000.backend.objects.User;

public class UserService {
	BallDao ballDao = new BallDao();
	
	public Location getCurrentLocation(){
		Location currentLocation = new Location(41.946448F, -87.646619F);
		return currentLocation;
	}

	public void connect(User user) {
		// TODO Auto-generated method stub
		
	}
	
	public void getAllBalls(User user){
		
	}
}
