package com.example.pratigya.hamblaster;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class Event {

    public String event_name;
    public String event_venue;
    public String event_time;
    public String selectedDate;
    public String hub;

public String download;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Event() {
    }

    public Event(String event_name, String event_venue, String event_time, String  download, String selectedDate,String hub) {
        this.event_name = event_name;
        this.event_venue = event_venue;
        this.event_time=event_time;
        this.download=download;
        this.selectedDate=selectedDate;
        this.hub=hub;
    }
    //Getters and Setters
    public String getdownload() {
        return download;
    }



    public String getevent_name() {
        return event_name;
    }



    public String getevent_venue() {
        return event_venue;
    }
    public String gethub() {
        return hub;
    }

    public String getevent_time()
    {
        return event_time;


}
    public String getSelectedDate()
    {
        return selectedDate;
    }
    public void setEvent_name(String event_name)
    {
        this.event_name=event_name;
    }

    public void setEvent_venue(String event_venue)
    {
        this.event_venue=event_venue;
    }
}