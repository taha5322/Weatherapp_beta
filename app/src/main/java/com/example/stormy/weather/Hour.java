package com.example.stormy.weather;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// Created by Taha Siddiqui
// 2020-04-04
public class Hour implements Serializable {
    private long time;
    private String summary;
    private double temperature;
    private String icon;
    private String timeZone;

    public Hour() {
    }

    public Hour(long time, String summary, double temperature, String icon, String timeZone) {
        this.time = time;
        this.summary = summary;
        this.temperature = temperature;
        this.icon = icon;
        this.timeZone = timeZone;
    }

    public String  getTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:aa");
        format.setTimeZone(TimeZone.getTimeZone(timeZone));

        Date date = new Date(time*1000);
        return format.format(date);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTemperature() {
        return (int) Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getIcon() {
        return Forecast.getIconId(icon);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
