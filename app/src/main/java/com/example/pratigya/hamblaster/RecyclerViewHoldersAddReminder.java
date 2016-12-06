package com.example.pratigya.hamblaster;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
public class RecyclerViewHoldersAddReminder extends RecyclerView.ViewHolder{
    private static final String TAG = RecyclerViewHoldersAddReminder.class.getSimpleName();
    public ImageView markIcon;
    public TextView categoryTitle;
    public ImageView addreminderIcon;

    public Context context;
    private List<Event> EventObject;
    public RecyclerViewHoldersAddReminder(final View itemView, final List<Event> EventObject) {
        super(itemView);
        this.EventObject = EventObject;
        categoryTitle = (TextView)itemView.findViewById(R.id.task_reminder);
        markIcon = (ImageView)itemView.findViewById(R.id.task_mark);

        addreminderIcon = (ImageView)itemView.findViewById(R.id.task_add_reminder);
        addreminderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "add reminder icon has been clicked", Toast.LENGTH_LONG).show();
                String taskTitle = EventObject.get(getAdapterPosition()).getSelectedDate();
                Log.d(TAG, "Task Title " + taskTitle);
                String startDateString = taskTitle;
                AddNotificationActivity ana=new AddNotificationActivity(startDateString,v.getContext());

            }
        });
    }
}