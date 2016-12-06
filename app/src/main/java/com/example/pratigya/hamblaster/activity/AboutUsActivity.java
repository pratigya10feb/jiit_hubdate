package com.example.pratigya.hamblaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pratigya.hamblaster.R;

public class AboutUsActivity extends AppCompatActivity {


    Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        click=(Button)findViewById(R.id.button2);
        click.setOnClickListener(new View.OnClickListener()
                                 {
                                     public void onClick(View v)
                                     {
                                         Intent i=new Intent(getApplicationContext(),AboutUsActivity2.class);
                                         startActivity(i);
                                     }
                                 }
        );
    }
}