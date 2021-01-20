package com.example.myapplication;

public class Plant {
    public int temperature;
    public int light;
    public int moisture;


    public Plant() {

    }
    public Plant(int t, int l, int m) {
        this.temperature =t;
        this.light = l;
        this.moisture=m;
    }
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getMoisture() {
        return moisture;
    }

    public void setMoisture(int moisture) {
        this.moisture = moisture;
    }


}
