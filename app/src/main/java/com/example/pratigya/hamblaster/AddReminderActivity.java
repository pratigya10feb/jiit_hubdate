package com.example.pratigya.hamblaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
public class AddReminderActivity extends AppCompatActivity {
    private static final String TAG = AddReminderActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapterAddReminder recyclerViewAdapter;

    private DatabaseReference databaseReference;
    private List<Event> allEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        allEvent = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView)findViewById(R.id.event_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                taskDeletion(dataSnapshot);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void getAllTask(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
            Event taskTitle = singleSnapshot.getValue(Event.class);
            allEvent.add(new Event(taskTitle.getevent_name(),taskTitle.getevent_venue(),taskTitle.getevent_time(),taskTitle.getdownload(),taskTitle.getSelectedDate(),taskTitle.gethub()));
            recyclerViewAdapter = new RecyclerViewAdapterAddReminder(AddReminderActivity.this, allEvent);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
    private void taskDeletion(DataSnapshot dataSnapshot){
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            String taskTitle = singleSnapshot.getValue(String.class);
            for(int i = 0; i < allEvent.size(); i++){
                if(allEvent.get(i).getevent_name().equals(taskTitle)){
                    allEvent.remove(i);
                }
            }
            Log.d(TAG, "Task tile " + taskTitle);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerViewAdapter = new RecyclerViewAdapterAddReminder(AddReminderActivity.this, allEvent);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}