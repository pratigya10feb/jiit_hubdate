package com.example.pratigya.hamblaster;

import android.content.Context;
import android.content.Intent;

public class AddNotificationActivity  {
public String startDateString;
    public Context context;
    public AddNotificationActivity()
    {}
   public AddNotificationActivity(String startDateString, Context context)
   {
       this.startDateString=startDateString;
       this.context=context;
       Intent i = new Intent(context,NotifyActivity.class);
       //....your code here
       i.putExtra("date", startDateString);
       context.startActivity(i);
   }
}
