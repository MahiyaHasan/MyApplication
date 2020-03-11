package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class CheckWeather extends AppCompatActivity {


    private WebView webView;
    Button setAlarm_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_weather);

        webView =  findViewById(R.id.webViewId);
        setAlarm_bt =  findViewById(R.id.setAlarm_bt);

        webView.loadUrl("https://weather.com/weather/tenday/l/c7f8a91d2b5efed8a9eccd27d7488d69c58f7d83a7f4fc69934d67fad6c0ef72");

        setAlarm_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoveSystem.class);
                startActivity(intent);
            }

        });
    }
}
