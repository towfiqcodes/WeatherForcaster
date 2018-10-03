package com.example.jmirza.weatherforcaster.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jmirza.weatherforcaster.R;
import com.example.jmirza.weatherforcaster.models.Forcast;
import com.example.jmirza.weatherforcaster.web_api.RetrofitClient;
import com.example.jmirza.weatherforcaster.web_api.WeatherApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.jmirza.weatherforcaster.MainActivity.temperChange;
import static com.example.jmirza.weatherforcaster.MainActivity.weatherForcast;


public class ForcastFragment extends Fragment {
    WeatherApi weatherApi;
    TextView todayMinForecast;
    TextView todayDate;
    TextView todayMaxForecast;

    TextView tomorrowMinForecast;
    TextView tomorrowDate;
    TextView tomorrowMaxForecast;

    TextView thirdDay;
    TextView thirdMinForecast;
    TextView thirdDate;
    TextView thirdMaxForecast;

    TextView fourthDay;
    TextView fourthMinForecast;
    TextView fourthDate;
    TextView fourthMaxForecast;

    TextView fifthDay;
    TextView fifthMinForecast;
    TextView fifthDate;
    TextView fifthMaxForecast;
    TextView locationTV;


    public ForcastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forcast, container, false);
        weatherApi = RetrofitClient.getretrofit().create( WeatherApi.class );
        locationTV = view.findViewById(R.id.LocationTV);
        todayMinForecast = view.findViewById(R.id.todayMin);
        todayDate = view.findViewById(R.id.todayDate);
        todayMaxForecast = view.findViewById(R.id.todayMax);

        tomorrowMinForecast = view.findViewById(R.id.tomorrowMin);
        tomorrowDate = view.findViewById(R.id.tomorrowDate);
        tomorrowMaxForecast = view.findViewById(R.id.tomorrowMax);

        thirdDay = view.findViewById(R.id.thirdDay);
        thirdMinForecast = view.findViewById(R.id.thirdMin);
        thirdDate = view.findViewById(R.id.thirdDate);
        thirdMaxForecast = view.findViewById(R.id.thirdMax);

        fourthDay = view.findViewById(R.id.fourthDay);
        fourthMinForecast = view.findViewById(R.id.fourthMin);
        fourthDate = view.findViewById(R.id.fourthDate);
        fourthMaxForecast = view.findViewById(R.id.fourthMax);

        fifthDay = view.findViewById(R.id.fifthDay);
        fifthMinForecast = view.findViewById(R.id.fifthMin);
        fifthDate = view.findViewById(R.id.fifthDate);
        fifthMaxForecast = view.findViewById(R.id.fifthMax);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
     //   setAllData(weatherForcast);
        setForecastTemp( weatherForcast );

    }
    private void setForecastTemp(Forcast forcast) {
        locationTV.setText(forcast.getCity().getName());
        setFirstDay( forcast );;
        setSecondDay(forcast);
        setThirdDay(forcast);
        setFourthDay(forcast);
        setFifthDay(forcast);

    }


   /* private void setFifthDay(Forcast weather) {
        String temp = null, minTemp = null, maxTemp = null;
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(weather.getList().get(32).getDt().longValue() * 1000);
        if (temperChange == 1) {

            minTemp = String.format("%.0f", weather.getList().get(32).getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", weather.getList().get(32).getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (temperChange == 2) {

            minTemp = String.format("%.0f", weather.getList().get(32).getMain().getTempMinFarenheit()) + "\u2109";
            maxTemp = String.format("%.0f", weather.getList().get(32).getMain().getTempMaxFarenheit()) + "\u2109";
        }
        fifthMaxForecast.setText(maxTemp);
        fifthMinForecast.setText(minTemp);

        String formattedDate = getDate(weather.getList().get(32).getDt().longValue() * 1000);
        fifthDate.setText(formattedDate);
        fifthDay.setText(weekday_name);
    }*/
    private String getDate(long dateTime) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(dateTime);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = DateFormat.format("dd-MMM-yyyy", cal).toString();
        return date;
    }


   private void setFirstDay(Forcast forcast) {
       if(temperChange==1){
           String todayMin=String.format("%.0f", forcast.getList().get(0).getMain().getTempMinCentigrate()) + "\u2103";
           todayMinForecast.setText(todayMin);
           String todayMax=String.format("%.0f", forcast.getList().get(0).getMain().getTempMaxCentigrate())+"\u2103";
           todayMaxForecast.setText(todayMax);
       }
        if(temperChange==2){
            String todayMin=String.format("%.0f", forcast.getList().get(0).getMain().getTempMinFarenheit()) + "\u2109";
            todayMinForecast.setText(todayMin);
            String todayMax=String.format("%.0f", forcast.getList().get(0).getMain().getTempMaxFarenheit())+"\u2109";
            todayMaxForecast.setText(todayMax);
        }
        String todayDateFormate = getDate(forcast.getList().get(0).getDt().longValue() * 1000);
        todayDate.setText(todayDateFormate);
    }
    private void setSecondDay(Forcast forcast){
       if(temperChange==1){
           String tomorrowMin=String.format("%.0f", forcast.getList().get(8).getMain().getTempMinCentigrate()) + "\u2103";
           tomorrowMinForecast.setText(tomorrowMin);
           String tomorrowMax=String.format("%.0f", forcast.getList().get(8).getMain().getTempMaxCentigrate())+"\u2103";
           tomorrowMaxForecast.setText(tomorrowMax);
       }
        if(temperChange==2){
            String tomorrowMin=String.format("%.0f", forcast.getList().get(8).getMain().getTempMinFarenheit()) + "\u2109";
            tomorrowMinForecast.setText(tomorrowMin);
            String tomorrowMax=String.format("%.0f", forcast.getList().get(8).getMain().getTempMaxFarenheit())+"\u2109";
            tomorrowMaxForecast.setText(tomorrowMax);
        }
        String tomorrowDateFormate = getDate(forcast.getList().get(8).getDt().longValue() * 1000);
        tomorrowDate.setText(tomorrowDateFormate);
    }
 private void setThirdDay(Forcast forcast){
       if(temperChange==1){
           String thirdMin=String.format("%.0f", forcast.getList().get(16).getMain().getTempMinCentigrate()) + "\u2103";
           thirdMinForecast.setText(thirdMin);
           String thirdMax=String.format("%.0f", forcast.getList().get(16).getMain().getTempMaxCentigrate())+"\u2103";
           thirdMaxForecast.setText(thirdMax);
       }
        if(temperChange==2){
            String thirdMin=String.format("%.0f", forcast.getList().get(16).getMain().getTempMinFarenheit()) + "\u2109";
            thirdMinForecast.setText(thirdMin);
            String thirdMax=String.format("%.0f", forcast.getList().get(16).getMain().getTempMaxFarenheit())+"\u2109";
            thirdMaxForecast.setText(thirdMax);
        }
     String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forcast.getList().get(16).getDt().longValue() * 1000);
thirdDay.setText( weekday_name );
     String thirdDateFormate = getDate(forcast.getList().get(16).getDt().longValue() * 1000);
        thirdDate.setText(thirdDateFormate);
    }
    private void setFourthDay(Forcast forcast){
       if(temperChange==1){
           String fourthMin=String.format("%.0f", forcast.getList().get(24).getMain().getTempMinCentigrate()) + "\u2103";
           fourthMinForecast.setText(fourthMin);
           String fourthMax=String.format("%.0f", forcast.getList().get(24).getMain().getTempMaxCentigrate())+"\u2103";
           fourthMaxForecast.setText(fourthMax);
       }
        if(temperChange==2){
            String fourthMin=String.format("%.0f", forcast.getList().get(24).getMain().getTempMinFarenheit()) + "\u2109";
            fourthMinForecast.setText(fourthMin);
            String fourthMax=String.format("%.0f", forcast.getList().get(24).getMain().getTempMaxFarenheit())+"\u2109";
            fourthMaxForecast.setText(fourthMax);
        }
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forcast.getList().get(24).getDt().longValue() * 1000);
       fourthDay.setText( weekday_name );
        String fourthDateFormate = getDate(forcast.getList().get(24).getDt().longValue() * 1000);
        fourthDate.setText(fourthDateFormate);
    }
private void setFifthDay(Forcast forcast){
       if(temperChange==1){
           String fifthMin=String.format("%.0f", forcast.getList().get(32).getMain().getTempMinCentigrate()) + "\u2103";
           fifthMinForecast.setText(fifthMin);
           String fifthMax=String.format("%.0f", forcast.getList().get(32).getMain().getTempMaxCentigrate())+"\u2103";
           fifthMaxForecast.setText(fifthMax);
       }
        if(temperChange==2){
            String fifthMin=String.format("%.0f", forcast.getList().get(32).getMain().getTempMinFarenheit()) + "\u2109";
            fifthMinForecast.setText(fifthMin);
            String fifthMax=String.format("%.0f", forcast.getList().get(32).getMain().getTempMaxFarenheit())+"\u2109";
            fifthMaxForecast.setText(fifthMax);
        }
    String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forcast.getList().get(32).getDt().longValue() * 1000);
   fifthDay.setText( weekday_name );
    String fifthDateFormate = getDate(forcast.getList().get(32).getDt().longValue() * 1000);
        fifthDate.setText(fifthDateFormate);
    }


}






