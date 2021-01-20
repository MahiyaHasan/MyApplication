package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Custom1Adapter  extends ArrayAdapter<Plant>{

        Activity context;
        List<Plant> plantList;

        public Custom1Adapter(Activity context,List<Plant>plantList) {
            super(context, R.layout.samplelayout, plantList);
            this.context = context;
            this.plantList = plantList;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            View view=layoutInflater.inflate(R.layout.samplelayout,null);
            Plant plant=plantList.get(position);
            TextView textView8=view.findViewById(R.id.textView8);
            TextView textView9=view.findViewById(R.id.textView9);
            TextView textView10=view.findViewById(R.id.textView10);
            textView8.setText("Threshold Temperature:"+String.valueOf(plant.getTemperature()));
            textView9.setText("Threshold Moisture:"+String.valueOf(plant.getMoisture()));
            textView10.setText("Threshold Light:"+String.valueOf(plant.getLight()));


            return view;
        }
    }


