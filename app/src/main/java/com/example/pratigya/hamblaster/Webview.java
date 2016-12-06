package com.example.pratigya.hamblaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class Webview extends AppCompatActivity {
    private WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView1 = (WebView) findViewById(R.id.webVie1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("https://www.codechef.com");
    }
}