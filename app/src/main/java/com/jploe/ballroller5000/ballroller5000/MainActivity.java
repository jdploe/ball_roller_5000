package com.jploe.ballroller5000.ballroller5000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    private final String msg = "MainActivity :";
    private EditText usernameField,passwordField;
    private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);

        status = (TextView)findViewById(R.id.textView6);
        role = (TextView)findViewById(R.id.textView7);
        method = (TextView)findViewById(R.id.textView9);
    }

    public void login(View view) throws ExecutionException, InterruptedException {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Get Method");
        SigninActivity loginActivity = new SigninActivity(this, status, role, 0);
        loginActivity.execute(username,password);
        String result = loginActivity.get();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        if(result.equals("Success")){
            //call landing page (just call maps for today
            Intent mapIntent = new Intent(view.getContext(), LandingActivity.class);
            startActivityForResult(mapIntent, 0);
        }
    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Post Method");
        new SigninActivity(this,status,role,1).execute(username,password);
    }


}



/*

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    String msg = "MainActivity : ";

    Intent mDatabaseService;

    */
/** Called when the activity is first created. *//*

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");
    }

    private void getFreshData() {
        Log.d(msg,"Getting Fresh Data");
        getDatabaseService();

        Log.d(msg,"Got Fresh Data");
    }

    private void getDatabaseService() {
        Intent serviceIntent = new Intent(this.getApplicationContext(), DatabaseService.class);
        mDatabaseService = serviceIntent;
        startService(serviceIntent);
    }

    */
/** Called when the activity is about to become visible. *//*

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
        getFreshData();
    }

    */
/** Called when the activity has become visible. *//*

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    */
/** Called when another activity is taking focus. *//*

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    */
/** Called when the activity is no longer visible. *//*

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    */
/** Called just before the activity is destroyed. *//*

    @Override
    public void onDestroy() {
        stopService(mDatabaseService);
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    public void goToMapView(View view){
        Log.d(msg,"In goToMapView");
        Intent mapIntent = new Intent(view.getContext(), MapsActivity.class);
        startActivityForResult(mapIntent, 0);
    }

    public void goToBallListView(View view){
        Log.d(msg,"In goToBallListView");
    }

}
*/