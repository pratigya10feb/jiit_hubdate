package com.example.pratigya.hamblaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import static com.example.pratigya.hamblaster.R.id.webView3;


public class webview3 extends AppCompatActivity {
    private WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview3);
        webView1 = (WebView) findViewById(webView3);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("https://www.hackerearth.com/");
    }
}