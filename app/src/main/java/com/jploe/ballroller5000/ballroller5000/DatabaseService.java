package com.jploe.ballroller5000.ballroller5000;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.jploe.ballroller5000.backend.objects.Ball;
import com.jploe.ballroller5000.backend.objects.User;
import com.jploe.ballroller5000.backend.services.BallService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by Justin on 7/30/2017.
 */

public class DatabaseService extends Service {
    private String msg = "DatabaseService: ";
    private BallService ballService;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Log.d(msg,"Starting Service");

        this.ballService = new BallService();
        String healthReport = healthCheck();

        Toast.makeText(this, healthReport, Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    private String healthCheck() {
        Log.d(msg,"IN HEALTH CHECK");
        try{
            String link = "http://127.0.0.1:3306";

            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line="";

            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }

            in.close();
            return sb.toString();
        } catch(Exception e){
            Log.e(msg,e.toString());
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        this.ballService = null;
        super.onDestroy();
        Log.d(msg,"Stopping Service");
        Toast.makeText(this, "Map Service Destroyed", Toast.LENGTH_LONG).show();
    }



    public Intent getAllBalls(){
        List<Ball> balls = null;
        //Remove this
        User user = new User("Jploe");
        user.setId(1);
        balls = ballService.getAllBallsForUser(user);
        if(balls!=null){
            //Do the stuff
        }
        return null;

    }
}
