package com.example.pratigya.hamblaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pratigya.hamblaster.AddReminderActivity;
import com.example.pratigya.hamblaster.R;

public class RemindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        Intent i=new Intent(RemindersActivity.this, AddReminderActivity.class);
        startActivity(i);
    }
}
