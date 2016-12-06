package com.example.pratigya.hamblaster;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class RecipeAdapter extends FirebaseRecyclerAdapter<Event, RecipeHolder> {
    private static final String TAG = RecipeAdapter.class.getSimpleName();
    private Context context;
    public RecipeAdapter(Class<Event> modelClass, int modelLayout, Class<RecipeHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }
    @Override
    protected void populateViewHolder(RecipeHolder viewHolder, Event model, int position) {
        viewHolder.event_name.setText("Event:  "+model.getevent_name());
      //  viewHolder.event_venue.setText("Venue:   "+model.getevent_venue());
     //   viewHolder.event_time.setText("Time:   "+model.getevent_time());
        //viewHolder.selectedDate.setText("Date:   "+model.getSelectedDate());
        Glide.with(context).load(model.getdownload()).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.eventImage);

    }
}