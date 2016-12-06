package com.example.pratigya.hamblaster.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pratigya.hamblaster.R;
import com.example.pratigya.hamblaster.Webview;
import com.example.pratigya.hamblaster.webview2;
import com.example.pratigya.hamblaster.webview3;

public class OsdcActivity extends AppCompatActivity {
    private Button ha,co,hae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osdc);
        co = (Button) findViewById(R.id.button5);
       ha = (Button) findViewById(R.id.button9);
     hae = (Button) findViewById(R.id.button10);
        co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OsdcActivity.this, Webview.class));
            }
        });
      ha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OsdcActivity.this, webview2.class));
            }
        });
        hae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OsdcActivity.this, webview3.class));
            }
        });
    }
}
