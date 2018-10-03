package com.example.jmirza.weatherforcaster.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmirza.weatherforcaster.R;
import com.example.jmirza.weatherforcaster.web_api.RetrofitClient;
import com.example.jmirza.weatherforcaster.weather_models.CurrentWeather;
import com.example.jmirza.weatherforcaster.web_api.WeatherApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jmirza.weatherforcaster.MainActivity.temperChange;
import static com.example.jmirza.weatherforcaster.MainActivity.weatherCurrent;
import static java.util.Locale.getDefault;


public class CurrentFragment extends Fragment {
    WeatherApi weatherApi;
    String weekDay = "";
    public static String API_KEY = "cd8e16a66e62e5394e93dd4a40e58e20";
    TextView currentTemp, currentDate, currentDay, currentCity,maxValue,minValue,sunrise,sunset,humidity,pressure,description;
    ImageView imageView;


    public CurrentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_current,container,false );
        weatherApi= RetrofitClient.getretrofit().create(WeatherApi.class);
        currentTemp = view.findViewById( R.id.currentTemp );
        currentDate = view.findViewById( R.id.currentDate );
        currentDay = view.findViewById( R.id.currentDay );
        currentCity = view.findViewById( R.id.currentCity );
        maxValue=view.findViewById( R.id.maxTemp );
        minValue=view.findViewById( R.id.minTemp );
        sunrise=view.findViewById( R.id.riseTime );
        sunset=view.findViewById( R.id.setTime );
        humidity=view.findViewById( R.id.showHumidity );
        pressure=view.findViewById( R.id.showPressure );
        description=view.findViewById( R.id.descriptionTV );
        imageView=view.findViewById(R.id.iconID);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllCurrentWeather(weatherCurrent);
    }


    public String getTime(long time) {
        Calendar cal = Calendar.getInstance( Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format( "hh:mm a", cal).toString();
        return date;
    }


    public void getAllCurrentWeather(CurrentWeather weatherCurrent){
        if(temperChange==1) {

            String celcius = String.format( "%.0f", weatherCurrent.getMain().getTempCelcius() ) + "\u2103";
            currentTemp.setText( celcius );
            //minimum temerature
            String valueOfMin =String.format( "%.0f", weatherCurrent.getMain().getTempMinCelcius() )+ "\u2103";
            minValue.setText( valueOfMin );
            //maximum temerature
            String valueOfMax = String.format( "%.0f", weatherCurrent.getMain().getTempMaxCelcius() ) + "\u2103";
            maxValue.setText( valueOfMax );
        }if (temperChange==2){

            String celcius = String.format( "%.0f", weatherCurrent.getMain().getTempFahrenheit() ) + "\u2109";
            currentTemp.setText( celcius );
            //minimum temerature
            String valueOfMin =String.format( "%.0f", weatherCurrent.getMain().getTempMinFahrenheit() ) + "\u2109";
            minValue.setText( valueOfMin );
            //maximum temerature
            String valueOfMax =String.format( "%.0f", weatherCurrent.getMain().getTempMaxFahrenheit() ) + "\u2109";
            maxValue.setText( valueOfMax );
        }
       int tempInC = (int) (weatherCurrent.getMain().getTemp() - 273);
        description.setText(weatherCurrent.getWeather().get(0).getDescription().toString());
        if(description.getText().toString().contains("ain")){
            imageView.setImageResource(R.drawable.rain_icon);
        }
        else if(description.getText().toString().contains("aze")){
            imageView.setImageResource(R.drawable.haze_icon);
        }
        else if(description.getText().toString().contains("loud")){
            imageView.setImageResource(R.drawable.cloud_icon);
        }
        else {
            imageView.setImageResource(R.drawable.sunny);
        }

        currentCity.setText( weatherCurrent.getName().toString());
        //currentDate
        Date date = new Date();;
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        String d = simpleDate.format( date );
        currentDate.setText( d );

        //Day name
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat( "EEEE", getDefault() );
        weekDay = dayFormat.format( calendar.getTime() );
        currentDay.setText( weekDay );
        //description
        String descriptionValue=weatherCurrent.getWeather().get(0).getDescription();
        description.setText( descriptionValue );
        sunrise.setText( getTime( weatherCurrent.getSys().getSunrise().longValue()*1000 ) );
        sunset.setText( getTime( weatherCurrent.getSys().getSunset().longValue()*1000 ) );

    }
    private void setCurrentTemperature(String location) {


        Call<CurrentWeather> weatherCall = weatherApi.getCurrentWeather(location, API_KEY);
        weatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                CurrentWeather weather = response.body();
                weatherCurrent = weather;
                getAllCurrentWeather(weatherCurrent);
               // currentTemp.setText(weather.getMain().getTemp().toString());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });
    }




}
