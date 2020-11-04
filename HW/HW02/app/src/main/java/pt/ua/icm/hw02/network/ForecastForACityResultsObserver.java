package pt.ua.icm.hw02.network;

import java.util.HashMap;
import java.util.List;

import pt.ua.icm.hw02.datamodel.Weather;
import pt.ua.icm.hw02.datamodel.WeatherType;

public interface ForecastForACityResultsObserver {
    public void receiveForecastList(List<Weather> forecast);
    public void onFailure(Throwable cause);
}
