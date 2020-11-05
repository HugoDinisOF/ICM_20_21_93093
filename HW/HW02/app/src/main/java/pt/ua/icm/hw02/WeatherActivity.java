package pt.ua.icm.hw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import pt.ua.icm.hw02.datamodel.Weather;

public class WeatherActivity extends AppCompatActivity {
    private List<Weather> weatherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        weatherList = (List<Weather>)b.getSerializable("weather");
        String city = b.getString("city");
        Log.d("oncreate", "onCreate: "+weatherList.get(0).getTMax());
        WeatherFragment fragment = WeatherFragment.newInstance(weatherList,city);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_weather_solo,fragment).commit();
        //TODO fragment implementation
    }
}