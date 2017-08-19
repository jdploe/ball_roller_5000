package com.jploe.ballroller5000.backend.constants;

public class NamedQueries {
		public static final String getAllBallsForByUser = "SELECT * FROM sandbox.ball WHERE user_id = ?";
		
		public static final String getBallByUserAndBallName = "SELECT * FROM sandbox.ball WHERE user_id =? and name = ?";
		
		public static final String storeBall = "INSERT INTO sandbox.ball SET user_id = ?,name=?,diameter=?";
		
		public static final String rollBall = "UPDATE sandbox.ball SET is_rolling = true, velocity = ?, starting_date = ?,longitude=?,latitude=? where name = ? and user_id = ?";
}
