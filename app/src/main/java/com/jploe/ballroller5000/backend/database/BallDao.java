package com.jploe.ballroller5000.backend.database;

import android.util.Log;

import com.jploe.ballroller5000.backend.constants.NamedQueries;
import com.jploe.ballroller5000.backend.constants.connectionConstants;
import com.jploe.ballroller5000.backend.objects.Ball;
import com.jploe.ballroller5000.backend.objects.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BallDao {
	private static final String PROPERTY_FILE_NAME = "database.properties";
	private boolean isConnected = false;
	private Connection connection;
	private String msg = "BallDao";

	public BallDao(){
		Log.d(msg,"Created BallDao");
		connect();
	}
	
	public Connection connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return null;
		}  
		Map<String,String> properties = getProperties();
		String host = null;
		String userName = null;
		String password = null;
		if(properties!=null){
			host = properties.get(connectionConstants.HOST);
			userName = properties.get(connectionConstants.USERNAME);	
			password = properties.get(connectionConstants.PASSWORD);
		}
		else{
			System.out.println("PROPERTIES ARE NULL");
			return null;
		}
		Connection conn= null;
		try {
			conn = DriverManager.getConnection(host,userName,password);
			isConnected = true;
			Log.d(msg,"CONNECTION MADE");
			connection = conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return conn;
	}

	private Map<String, String> getProperties() {
		Map<String,String> properties = null;
		try (BufferedReader br = new BufferedReader(new FileReader(PROPERTY_FILE_NAME))) {
			String sCurrentLine;
			properties = new HashMap<String,String>();
			while ((sCurrentLine = br.readLine()) != null) {
				String [] propertyNameValue = sCurrentLine.split(" ");
				//System.out.println("KEY: " + propertyNameValue[0] + "     VALUE: " + propertyNameValue[1]);
				if(propertyNameValue[0] !=null && propertyNameValue[1] != null){
					properties.put(propertyNameValue[0], propertyNameValue[1]);					
				}
				else{
					System.out.println("PROPERTIES ARE NULL");
				}	
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public boolean isConnected(){
		return this.isConnected ;
	}

	public Ball getByNameUserSpecific(User user, String name){
		return null;
	}

	public List<Ball> getAllBallsForUser(User user) {
		Connection conn = getConnection();
		List<Ball> retBalls = new ArrayList<Ball>();
		try {
			PreparedStatement ps = conn.prepareStatement(NamedQueries.getAllBallsForByUser);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()){
				String name = rs.getString("name");
				Integer id = rs.getInt("id");
				Ball ball = new Ball(50,name,null);
				ball.setId((long) id);
				ball.setName(name);
				retBalls.add(ball);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return retBalls;
	}

	private void disconnect() {
		try {
			getConnection().close();
			isConnected = false;
			System.out.println("DISCONNECTED");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private Connection getConnection() {
		if(isConnected()){
			return this.connection;			
		}
		else{
			connect();
			return this.connection;
		}
	}

	public Ball getBallByUserAndBallName(User user, String ballName) {
		Connection conn = getConnection();
		Ball retBall = null;
		try {
			PreparedStatement ps = conn.prepareStatement(NamedQueries.getBallByUserAndBallName);
			ps.setInt(1,user.getId());
			ps.setString(2, ballName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				int diameter = rs.getInt("diameter");
				retBall = new Ball(diameter,name,null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return retBall;
	}
	
	public void store(Ball ball, User user){
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(NamedQueries.storeBall);
			ps.setInt(1, user.getId());
			ps.setString(2, ball.getName());
			ps.setDouble(3,ball.getDiameter());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Stored ball: " + ball.getName());
		disconnect();
	}
	
	public void rollBall(Ball ball, double velocity, User user) {
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(NamedQueries.rollBall);
			ps.setDouble(1, ball.getVelocity());
			java.util.Date now = new java.util.Date();
			ps.setDate(2,new Date(now.getTime()));
			ps.setFloat(3, user.getCurrentLocation().getLongitude());
			ps.setFloat(4, user.getCurrentLocation().getLatitude());
			ps.setString(5,ball.getName());
			ps.setInt(6, user.getId());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ball Rolling: " + ball.getName() + "Velocity: " + ball.getVelocity());
		disconnect();
	}

}
