package com.example.pratigya.hamblaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pratigya.hamblaster.R;
import com.example.pratigya.hamblaster.SettingsActivity;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        startActivity(new Intent(SetActivity.this,SettingsActivity.class));
    }
}
