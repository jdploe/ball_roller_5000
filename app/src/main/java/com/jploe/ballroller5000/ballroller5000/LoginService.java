package com.jploe.ballroller5000.ballroller5000;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class LoginService extends Service {
    private final String msg = "LoginService:";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(msg,"Getting parameters from intent");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        Log.d(msg,"Doing LoginCheck");
        doLoginCheck(username,password);
        return START_STICKY;
}

    private void doLoginCheck(String username, String password) {

        boolean result = false; //Default to failure
        try{

            String link = "http://localhost:8080/BallRoller5000/BallRoller";//"http://myphpmysqlweb.hostei.com/login.php?username="+username+"& password="+password;
            Log.d(msg,"ADDRESS: " + link);
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            Log.d(msg,"RESPONSE: " + response.getEntity().getContentLength());
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line="";
            Log.d(msg,"Got input stream");
            while ((line = in.readLine()) != null) {
                sb.append(line);
                Log.d(msg,"APPENDING: " + line);
                break;
            }

            in.close();
            Log.d(msg,"Closing: " + sb.toString());
            if(sb.toString().equals("Success")){
                result=true;
            }
        } catch(Exception e){
            Log.e(msg,"",e);
        }
        finally {
            sendMessageToActivity(result);
        }
    }

    private void sendMessageToActivity(boolean result) {
        Intent intent = new Intent("login_response");
        // You can also include some extra data.
        intent.putExtra("result", result);
        intent.setAction("login_result");
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg,"Stopping Service");
        Toast.makeText(this, "Login Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
