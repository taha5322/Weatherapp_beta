package com.example.stormy.weather;

import com.example.stormy.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// Created by Taha Siddiqui
// 2020-03-27
public class Current {
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

    public Current() {
    }

    public Current(String locationLabel, String icon, long time,
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
        int iconId = R.drawable.sunny;
        return Forecast.getIconId(icon);
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

        //String wrongTime = format.format(dateTime);

//        StringBuffer buffer = new StringBuffer(wrongTime);
//
//        char firstDidget = wrongTime.charAt(0);
//        char secondDidget = wrongTime.charAt(1);
//        int i;
//        for( i=2; i<8; i++){
//            if( ((int) firstDidget) == i ){
//                buffer.replace(0,1,Integer.toString(i+2));
//            }
//        }
//        if((int)firstDidget==1 && secondDidget == ':'){
//            buffer.replace(0,1, Integer.toString( (int)firstDidget +2) );
//        } else if((int)firstDidget==1 && (int)secondDidget==1){
//            buffer.replace(0,2,"1");
//        } else if((int)firstDidget==1 && (int)secondDidget==2){
//            buffer.replace(0,2,"2");
//        }
//        return buffer.toString();
        Calendar cal;
        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        String correctTime = format.format(cal.getTime());
//        return Integer.toString(firstDidget);
        return correctTime;
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
