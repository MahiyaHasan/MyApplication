package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
     ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageview=findViewById(R.id.imageview);
        Intent intent = getIntent();
        String img= intent.getExtras().getString("image");
        Picasso.get().load(img).into(imageview);
    }
}