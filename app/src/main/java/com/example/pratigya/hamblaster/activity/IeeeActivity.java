package com.example.pratigya.hamblaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pratigya.hamblaster.R;
import com.example.pratigya.hamblaster.other.Webviewie;

public class IeeeActivity extends AppCompatActivity {

    private Button ha,co,hae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ieee);
        co = (Button) findViewById(R.id.butt);
        ha = (Button) findViewById(R.id.buon9);
        hae = (Button) findViewById(R.id.buon10);
        co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IeeeActivity.this, Webviewie.class));
            }
        });
        ha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IeeeActivity.this, Webviewie.class));
            }
        });
        hae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IeeeActivity.this, Webviewie.class));
            }
        });
    }
}
