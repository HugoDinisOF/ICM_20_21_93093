package pt.ua.icm.hw02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import pt.ua.icm.hw02.datamodel.City;
import pt.ua.icm.hw02.datamodel.Weather;
import pt.ua.icm.hw02.datamodel.WeatherType;
import pt.ua.icm.hw02.network.CityResultsObserver;
import pt.ua.icm.hw02.network.ForecastForACityResultsObserver;
import pt.ua.icm.hw02.network.IpmaWeatherClient;
import pt.ua.icm.hw02.network.WeatherTypesResultsObserver;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    public static HashMap<Integer, WeatherType> weatherDescriptions;
    private RecyclerView mrecyclerview;
    private boolean mIsDualPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO utilizar o savedInstanceState
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            Log.d("d","deu este");
            if(savedInstanceState.getSerializable("citylist") != null){
                cities = (HashMap<String, City>)savedInstanceState.getSerializable("citylist");
                //LoadCityList();

            }
            else{
                Log.d("d","deu este 2");
                callWeatherForecastForACityList();
            }

        }catch(Exception e){
            Log.d("d","deu este 3");
            callWeatherForecastForACityList();}
        View v = findViewById(R.id.fragment_container_weather);
        mIsDualPane = v != null &&
                v.getVisibility() == View.VISIBLE;
        Log.d("IsDualPane", String.valueOf(mIsDualPane));
    }
    private void callWeatherForecastForACityList() {

        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                MainActivity.this.cities = citiesCollection;
                LoadCityList();
            }

            @Override
            public void onFailure(Throwable cause) {
                Log.d("MainActivity","fail");
            }
        });

    }

    private void LoadCityList(){
        Set<String> s=cities.keySet();
        ListFragment fragment = ListFragment.newInstance(s,new OnClickCity());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_list,fragment).addToBackStack(null).commit();
        mrecyclerview=(RecyclerView) findViewById(R.id.recyclerview);
    }
    public class OnClickCity implements View.OnClickListener, Serializable, Parcelable {
        static final long serialVersionUID = 42L;
        @Override
        public void onClick(View v) {
            String city = ((TextView)((LinearLayout) v).getChildAt(0)).getText().toString();
            Log.d("Onclick",city);
            viewWeather(city);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
    public void viewWeather(String city){
        City c=cities.get(city);
        MainActivity m = this;
        client.retrieveForecastForCity(c.getGlobalIdLocal(), new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                if (mIsDualPane) {
                    //TODO Fazer a transaction para mudar o fragment
                    WeatherFragment fragment = WeatherFragment.newInstance(forecast,city);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container_weather,fragment).addToBackStack(null).commit();
                }else{
                    Intent i = new Intent(m,WeatherActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("weather", (Serializable) forecast);
                    b.putString("city",city);
                    i.putExtras(b);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("citylist",cities);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try{
            Log.d("d","deu este");
            if(savedInstanceState.getSerializable("citylist") != null){
                cities = (HashMap<String, City>)savedInstanceState.getSerializable("citylist");
                //LoadCityList();

            }
            else{
                Log.d("d","deu este 2");
                callWeatherForecastForACityList();
            }

        }catch(Exception e){
            Log.d("d","deu este 3");
            callWeatherForecastForACityList();}
    }
}