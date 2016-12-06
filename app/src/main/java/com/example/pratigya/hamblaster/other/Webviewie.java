package com.example.pratigya.hamblaster.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.pratigya.hamblaster.R;

public class Webviewie extends AppCompatActivity {
    private WebView webView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewie);
        webView4 = (WebView) findViewById(R.id.webVie4);
        webView4.getSettings().setJavaScriptEnabled(true);
        webView4.loadUrl("https://www.ieee.org/index.html");
    }
}
