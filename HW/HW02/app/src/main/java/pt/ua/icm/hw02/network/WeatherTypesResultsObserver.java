package pt.ua.icm.hw02.network;

import java.util.HashMap;

import pt.ua.icm.hw02.datamodel.WeatherType;

public interface WeatherTypesResultsObserver {
    public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection);
    public void onFailure(Throwable cause);
}
