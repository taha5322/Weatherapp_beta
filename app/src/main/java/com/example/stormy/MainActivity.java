package com.example.stormy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.stormy.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private CurrentWeather currentWeather;
    private TextView darkSkyAttribution;
    private ImageView iconImageView;
    final double latitude = 37.8267;
    final double longitude = -122.4233;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getForecast(latitude,longitude);

    }

    private void getForecast(double latitude, double longitude) {
        //setContentView(R.layout.activity_main);

        //how to data bind to using an xml file. Second parameter is the xml file's id
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        iconImageView = findViewById(R.id.iconImageView);

        //used to make the link work
        darkSkyAttribution = findViewById(R.id.darkSkyAttribution);
        darkSkyAttribution.setMovementMethod(LinkMovementMethod.getInstance());

        String link ="https://api.darksky.net/forecast/";
        String apiKey = "c043e98b63e555588028b88476bd1ef1";


        String URL = "https://api.darksky.net/forecast/c043e98b63e555588028b88476bd1ef1/43.6532,79.3832";

        if(isNetworkAvailible()) {

            //client is needed to send requests to the dark sky server
            OkHttpClient client = new OkHttpClient();

            //request is sent to server
            Request request = new Request.Builder()
                    .url(URL)
                    .build();

            //call is what takes the request and sends it to server
            Call call = client.newCall(request);

            //Response response = call.execute(); don't use because it is synchronous

            //this is an asychronous method.
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    try {

                        String jsonData = response.body().string();

                        //response.body().string() is the entire string message
                        //from the response.

                        if (response.isSuccessful()) {//checks to see if we have response
                            currentWeather = getCurrentDetails(jsonData);

                            final CurrentWeather displayWeather = new CurrentWeather(
                                    currentWeather.getLocationLabel(),
                                    currentWeather.getIcon(),
                                    currentWeather.getTime(),
                                    currentWeather.getTemperature(),
                                    currentWeather.getHumidity(),
                                    currentWeather.getPrecipChance(),
                                    currentWeather.getSummary(),
                                    currentWeather.getTimeZone()
                            );
                            //setWeather() sets the binding 'CurrentWeather' class for the XML viw
                            binding.setWeather(displayWeather);

//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                }
//                            });
                            //if you get a thread exception, put bottom code in the run()
                            Drawable drawable = getResources().getDrawable(currentWeather.getIconId());
                            iconImageView.setImageDrawable(drawable);

                            Log.v(TAG,"time is: " + currentWeather.getFormatteeTime());

                        } else {
                            alertUserAboutProblem();
                        }
                    } catch (IOException e) {
                        Log.d(TAG, "onResponse: IO exception caught", e);
                    } catch (JSONException e){
                        Log.e(TAG, "onResponse: Json exception caught", e);
                    }


                }
            });

            //NOTE: the Call class has an execute function which then
            //retrieves the response
            //if you only do Response response = call.execute();
            //you will get an error because of no try catch

        } else{
            Toast.makeText(this, R.string.network_unavailible_message,Toast.LENGTH_LONG).show();
        }
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG,"From JSON: " + timezone);

        //object inside the json file which has data we care about
        JSONObject currentlyObject = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setHumidity(currentlyObject.getDouble("humidity"));
        currentWeather.setTime(currentlyObject.getLong("time"));
        currentWeather.setIcon(currentlyObject.getString("icon"));
        currentWeather.setLocationLabel("Alcatraz Island");
        currentWeather.setPrecipChance(currentlyObject.getDouble("precipProbability"));
        currentWeather.setSummary(currentlyObject.getString("summary"));
        currentWeather.setTemperature(currentlyObject.getDouble("apparentTemperature"));
        currentWeather.setTimeZone(timezone);

        return currentWeather;
    }


    private boolean isNetworkAvailible() {
        //connectivity manager answers queries about the state of network connectivity
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //needs a permission in android manifest called ACCESS_NETWORK_STATE
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailible = false;

        //checks to see if network is connected
          if(networkInfo.isConnected() && networkInfo != null){
              isAvailible = true;
              Log.d(TAG, "we have connection");
        } else{
              isAvailible = false;
              Log.d(TAG, "no connection");
          }
          return isAvailible;
    }

    private void alertUserAboutProblem() {
        //makes an instance of the dialog class we made
        AlertDialogFragment dialog = new AlertDialogFragment();

        //shows the dialog on the activity. Fragment manager, u jus use getFragmentManager
        dialog.show(getFragmentManager(), "Error dialog");
    }

    public void refreshOnclick(View view) {
        Toast.makeText(this,"Getting weather", Toast.LENGTH_LONG).show();
        getForecast(latitude,longitude);
    }
}
