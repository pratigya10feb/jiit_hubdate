package com.example.pratigya.hamblaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class webview2 extends AppCompatActivity {
    private WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview2);
        webView1 = (WebView) findViewById(R.id.webView2);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("https://www.hackerrank.com/contests");
    }
}