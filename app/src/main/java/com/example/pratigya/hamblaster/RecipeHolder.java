package com.example.pratigya.hamblaster;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeHolder extends RecyclerView.ViewHolder{
    private static final String TAG = RecipeHolder.class.getSimpleName();
    public TextView event_name/*,event_venue,event_time,selectedDate*/;
    public ImageView eventImage;


    public RecipeHolder(View itemView) {
        super(itemView);
        event_name = (TextView)itemView.findViewById(R.id.event_name);
      //  event_venue = (TextView)itemView.findViewById(R.id.event_venue);
       // event_time = (TextView)itemView.findViewById(R.id.event_time);
       // selectedDate = (TextView)itemView.findViewById(R.id.selectedDate);
        eventImage = (ImageView)itemView.findViewById(R.id.eventImage);

    }
}