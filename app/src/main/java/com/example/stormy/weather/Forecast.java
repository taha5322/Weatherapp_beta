package com.example.stormy.weather;

import com.example.stormy.R;

// Created by Taha Siddiqui
// 2020-04-04
public class Forecast {
    private Current current;
    private Hour[] hourlyForecast;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Hour[] getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(Hour[] hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public static int getIconId(String iconString){
        int iconId = R.drawable.clear_day;
        switch(iconString) {
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
            case "clear-night":
                iconId = R.drawable.clear_night;
                break;
            case "clear-day":
                iconId= R.drawable.clear_day;
                break;
        }
        return iconId;
    }

}
