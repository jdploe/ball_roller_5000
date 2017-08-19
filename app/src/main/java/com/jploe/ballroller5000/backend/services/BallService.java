package com.jploe.ballroller5000.backend.services;

import android.util.Log;

import com.jploe.ballroller5000.backend.database.BallDao;
import com.jploe.ballroller5000.backend.objects.Ball;
import com.jploe.ballroller5000.backend.objects.Location;
import com.jploe.ballroller5000.backend.objects.User;

import java.util.List;



public class BallService {
	
	private BallDao ballDao;
	private String msg = "BallService: ";

	public BallService (){
		Log.d(msg,"Creating BallService");
		this.ballDao = new BallDao();
	}

	public Ball createBall(double diameter, String name, User user, Location currentLocation){
		Ball ball = new Ball(diameter,name,currentLocation);
		ballDao.store(ball,user);
		return ball;
	}
	
	public Ball rollBall(Ball ball, double velocity, User user){
		ball.setRolling(true);
		ball.setVelocity(velocity);
		
		ballDao.rollBall(ball,velocity,user);
		
		return ball;
		//update ball in db here
	}

	public List<Ball> getAllBallsForUser(User user) {
		return ballDao.getAllBallsForUser(user);
	}

	public Ball getBallByUserAndBallName(User user, String ballName) {
		return ballDao.getBallByUserAndBallName(user,ballName);
	}
}
