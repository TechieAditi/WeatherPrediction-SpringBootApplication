package com.weatherApp.weatherprediction.service;
import com.weatherApp.weatherprediction.Exceptions.ClientException;
import com.weatherApp.weatherprediction.Exceptions.DataNotFoundException;
import com.weatherApp.weatherprediction.Exceptions.ServerException;
import com.weatherApp.weatherprediction.constant.Routes;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import  com.weatherApp.weatherprediction.model.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService extends MappingJackson2HttpMessageConverter   {

    private static final WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }

    private WeatherService() {
        setPrettyPrint(true);
    }


    public List<Example> getWeather(String city)  throws ServerException, ClientException, DataNotFoundException  {

        StringBuilder weatherOpenAPI = new StringBuilder(Routes.WEATHER_OPEN_API);
        weatherOpenAPI.append(city)
                .append(Routes.API_PARAMETER)
                .append(Routes.API_APP_KEY)
                .append(Routes.API_UNIT_PARAMETER);


        RestService restService = new RestService();
        String result = restService.getRestCall(String.valueOf(weatherOpenAPI));

        String description = null;
        String WeatherCondition = null;
        double temp;
        double temp_min;
        double temp_max;
        double pressure;
        int humidity;


        List<Example> weatherList = new ArrayList<>();

        JSONObject root = new JSONObject(result);

        JSONArray weatherObject = root.getJSONArray("weather");

        String cityName = root.getString("name");

        JSONObject sysObject = root.getJSONObject("sys");
        String country = sysObject.getString("country");

        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject elementInArray = weatherObject.getJSONObject(i);
            description = elementInArray.getString("description");
            WeatherCondition = elementInArray.getString("main");
        }

        JSONObject main = root.getJSONObject("main");


        temp = (int) main.getFloat("temp");
        pressure = main.getInt("pressure");
        humidity = main.getInt("humidity");
        temp_min= (int) main.getFloat("temp_min");
        temp_max= (int) main.getFloat("temp_max");



        TodaysWeather tw=new TodaysWeather();
        Example e=new Example();
        Today t=new Today();

        t.setDescription(description);

        t.setHumidity(humidity);
        t.setMain(WeatherCondition);
        t.setPressure(pressure);

        t.setTemp(temp);
        t.setTempMax(temp_max);
        t.setTempMin(temp_min);

        tw.setToday(t);
        CityDetail cityDetail = new CityDetail(cityName, country);
        e.setTodaysWeather(tw);
        e.setCityDetail(cityDetail);

        weatherList.add(e);
        return weatherList;
    }

}