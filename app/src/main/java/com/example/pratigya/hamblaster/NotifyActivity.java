package com.example.pratigya.hamblaster;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This is the Main Activity of our app.
 * Here we allow the user to select a date,
 * we then set a notification for that date to appear in the status bar
 *
 * @author paul.blundell
 */
public class NotifyActivity extends Activity  {
    private static final String TAG = NotifyActivity.class.getSimpleName();
    // This is a handle so that we can call methods on our service
    private ScheduleClient scheduleClient;
    // This is the date picker used to select the date for our notification
    private DatePicker picker;
public String message;
    public Date startDate;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        Bundle bundle = getIntent().getExtras();
         message = bundle.getString("date");
        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        // Get a reference to our date picker
        picker = (DatePicker) findViewById(R.id.TimePicker);
    }

    /**
     * This is the onClick called from the XML to set a new notification
     */
    public void onDateButtonClick(View v){
        // Get the date from our datepicker
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

               try {
                    startDate = df.parse(message);
                    String newDateString = df.format(startDate);
                   Log.e(TAG, "Task Title " + newDateString);

                   Calendar cal = Calendar.getInstance();
                   cal.setTime(startDate);

                   int month = cal.get(Calendar.MONTH);
                   int day = cal.get(Calendar.DAY_OF_MONTH);
                   int year = cal.get(Calendar.YEAR);

                   cal.set(year, month, day);
                   cal.set(Calendar.HOUR_OF_DAY, 0);
                   cal.set(Calendar.MINUTE, 0);
                   cal.set(Calendar.SECOND, 0);
                //   Log.e(TAG,"alarm set" + newDateString);
                   scheduleClient.setAlarmForNotification(cal);
                   Log.e(TAG,"alarm set" + newDateString);
                   // Notify the user what they just did
                //   Toast.makeText(this, "Notification set for: "+ day +"/"+ (month+1) +"/"+ year, Toast.LENGTH_SHORT).show();
                  //  System.out.println(newDateString);
               } catch (ParseException e) {
                   e.printStackTrace();
                }
        Toast.makeText(this, "" +"Default Notification set ", Toast.LENGTH_SHORT).show();
    }
    public void onDateSelectedButtonClick(View v){
        // Get the date from our datepicker
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();
        // Create a new calendar set to the date chosen
        // we set the time to midnight (i.e. the first minute of that day)
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
        scheduleClient.setAlarmForNotification(c);
        // Notify the user what they just did
        Toast.makeText(this, "Notification set for: "+ day +"/"+ (month+1) +"/"+ year, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        if(scheduleClient != null)
            scheduleClient.doUnbindService();
        super.onStop();
    }
}