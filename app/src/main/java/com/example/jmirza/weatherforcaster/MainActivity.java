package com.example.jmirza.weatherforcaster;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmirza.weatherforcaster.fragments.CurrentFragment;
import com.example.jmirza.weatherforcaster.fragments.ForcastFragment;
import com.example.jmirza.weatherforcaster.models.Forcast;
import com.example.jmirza.weatherforcaster.web_api.RetrofitClient;
import com.example.jmirza.weatherforcaster.weather_models.CurrentWeather;
import com.example.jmirza.weatherforcaster.web_api.WeatherApi;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jmirza.weatherforcaster.fragments.CurrentFragment.API_KEY;


public class MainActivity extends AppCompatActivity {
    WeatherApi weatherApi;
    TextView currentTemp;
    Toolbar toolbar;
    TabLayout tabLayout;
    MaterialSearchView searchView;
    public static int temperChange=1;
    public static int index=1;
    public static CurrentWeather weatherCurrent;
    public static Forcast weatherForcast;
    public static String location = "Dhaka,bd";
    String queryLocation = null;
    //String celcius,country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Weather Report");
        currentTemp=findViewById(R.id.currentTemp);
        currentTemp.setVisibility(View.GONE);
        searchView=findViewById(R.id.searchView);
        weatherApi = RetrofitClient.getretrofit().create( WeatherApi.class );
        tabLayout = findViewById( R.id.tablayout );
        tabLayout.addTab( tabLayout.newTab().setText( "CURRENT" ) );
        tabLayout.addTab( tabLayout.newTab().setText( "7 DAYS FORECAST" ) );
        tabLayout.setTabGravity( TabLayout.GRAVITY_FILL );
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTemp.setText(tab.getText().toString());
                if (tab.getPosition() == 0) {
                    index = 1;
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    CurrentFragment fragment = new CurrentFragment();
                    transaction.replace(R.id.selectedFragment, fragment).commit();
                } else {
                    index = 2;
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    ForcastFragment fragment = new ForcastFragment();
                    transaction.replace(R.id.selectedFragment, fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getAllWeather();
        search();

    }

    private void search() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryLocation=location;
                location=query + ",bd";
               getAllWeather();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchView.setSuggestions(getResources().getStringArray(R.array.city_item));
                searchView.showSuggestions();
                return false;
            }
        });
    }


    public void getAllWeather(){
            Call <CurrentWeather> currentWeatherCall = weatherApi.getCurrentWeather(location,API_KEY);
            currentWeatherCall.enqueue( new Callback <CurrentWeather>() {
                @Override
                public void onResponse(Call <CurrentWeather> call, Response <CurrentWeather> response) {
                    CurrentWeather currentWeather = response.body();

                    if(currentWeather!=null){
                        weatherCurrent=currentWeather;
                        if(index==1){
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            CurrentFragment fragment = new CurrentFragment();
                            transaction.replace(R.id.selectedFragment, fragment).commit();

                        }
                    }else{
                        location = queryLocation;
                        Toast.makeText(MainActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call <CurrentWeather> call, Throwable t) {
                    //Toast.makeText(MainActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
                    AlertDialog alertDialog = new AlertDialog.Builder( MainActivity.this ).create();
                    alertDialog.setTitle( "Alert" );
                    alertDialog.setMessage( "Please Check Internet your Connection" );
                    alertDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    } );
                    alertDialog.show();
                }


            });
        Call<Forcast> forcastCall=weatherApi.getForcastWeather(location,API_KEY);
        forcastCall.enqueue(new Callback<Forcast>() {
            @Override
            public void onResponse(Call<Forcast> call, Response<Forcast> response) {
                Forcast forcast=response.body();
                if(forcast!=null){
                    weatherForcast= forcast;
                    if(index==2){
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        ForcastFragment forcastFragment = new ForcastFragment();
                        transaction.replace(R.id.selectedFragment, forcastFragment).commit();
                    }

                }else {
                    location = queryLocation;
                    Toast.makeText(MainActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Forcast> call, Throwable t) {

            }
        });
    }



        // menu inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item=menu.findItem(R.id.search_bar);
        searchView.setMenuItem(item);
        return true;
    }


    public void goToHomePage(MenuItem item) {

         getAllWeather();
    }

    public void goForSearch(MenuItem item) {
        search();

    }

    public void celciusConverter(MenuItem item) {
        temperChange=1;
       getAllWeather();
    }

    public void farenConverter(MenuItem item) {
        temperChange=2;
        getAllWeather();
    }

    public void goForRefresh(MenuItem item) {
        getAllWeather();
    }
}
