package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;

    int flags[];
    int arrows[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[]arrows, int[] flags) {
        this.context = context;

        this.flags = flags;
        this.arrows = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }
    public int getCount() {
        return flags.length;
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
        view = inflter.inflate(R.layout.listdata, null);

        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(flags[i]);
        icon.setImageResource(arrows[i]);
        return view;
    }
}
