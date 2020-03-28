package com.example.stormy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// Created by Taha Siddiqui
// 2020-03-27
public class CurrentWeather {
    private String locationLabel;
    private String icon;
    private long time;
    private double temperature;
    private double humidity;
    private double precipChance;
    private String summary;
    private String timeZone;


    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public CurrentWeather() {
    }

    public CurrentWeather(String locationLabel, String icon, long time,
                          double temperature, double humidity, double precipChance,
                          String summary, String timeZone) {
        this.locationLabel = locationLabel;
        this.icon = icon;
        this.time = time;
        this.temperature = Math.round(temperature);
        this.humidity = humidity;
        this.precipChance = precipChance;
        this.summary = summary;
        this.timeZone = timeZone;
    }

    public int getIconId(){
        int iconId=0;
        switch(icon) {

            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;
        }
        return iconId;
    }

    public String getFormatteeTime(){
        //you need the pattern formqtted in that way to get the: Hour: minute: am/pm
        SimpleDateFormat format = new SimpleDateFormat("h:mm:a");

        format.setTimeZone(TimeZone.getTimeZone(timeZone));

        Date dateTime = new Date(time*1000);
        //format() returns a formatted time when you give UNIX time
        //in miliseconds. The JSON gives us UNIX time in seconds
        //I multiplied by thousand and made a date object which is what
        //the format() needs as an object to return the time while formatted.
        return format.format(dateTime);
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
