package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    GridView gridView;
    String[] fruitNames = {"Apple","Orange","strawberry","Melon","Kiwi","Banana"};
    int[] fruitImages = {R.drawable.moisture,R.drawable.light,R.drawable.temperature,R.drawable.ph,R.drawable.weather,R.drawable.blank};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView=findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              /*  Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",fruitNames[i]);
                intent.putExtra("image",fruitImages[i]);
                startActivity(intent);*/

                if(i == 0)
                {
                    Intent homepageIntent = new Intent(getApplicationContext(), CheckMoisture.class);
                    startActivity(homepageIntent);
                }
                if(i == 1)
                {
                    Intent homepageIntent = new Intent(getApplicationContext(), CheckLight.class);
                    startActivity(homepageIntent);
                }
                if(i == 2)
                {
                    Intent homepageIntent = new Intent(getApplicationContext(), CheckTemperature.class);
                    startActivity(homepageIntent);
                }
                if(i == 3)
                {
                    Intent homepageIntent = new Intent(getApplicationContext(), CheckPH.class);
                    startActivity(homepageIntent);
                }
                if(i == 4)
                {
                    Intent homepageIntent = new Intent(getApplicationContext(), CheckWeather.class);
                    startActivity(homepageIntent);
                }
                if(i == 5)
                {

                }

            }
        });
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return fruitImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1=getLayoutInflater().inflate(R.layout.row_data, null);
            TextView name=view1.findViewById(R.id.fruits);
            ImageView image=view1.findViewById(R.id.images);

           // name.setText(fruitNames[i]);
            image.setImageResource(fruitImages[i]);
            return view1;
        }
    }
}
