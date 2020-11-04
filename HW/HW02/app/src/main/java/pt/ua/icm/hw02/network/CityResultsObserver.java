package pt.ua.icm.hw02.network;

import java.util.HashMap;

import pt.ua.icm.hw02.datamodel.City;

public interface  CityResultsObserver {
    public void receiveCitiesList(HashMap<String, City> citiesCollection);
    public void onFailure(Throwable cause);
}
