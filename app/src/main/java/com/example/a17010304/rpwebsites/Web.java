package com.example.a17010304.rpwebsites;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;

public class Web extends AppCompatActivity {
    WebView wp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wp = findViewById(R.id.webView);
        wp.setWebViewClient(new WebViewClient());

        //school
        Intent intentReceived = getIntent();
        String getUrl = intentReceived.getStringExtra("url");
        wp.loadUrl(getUrl);
    }
}
