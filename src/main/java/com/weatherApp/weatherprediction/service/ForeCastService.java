package com.weatherApp.weatherprediction.service;
import com.weatherApp.weatherprediction.Exceptions.ClientException;
import com.weatherApp.weatherprediction.Exceptions.DataNotFoundException;
import com.weatherApp.weatherprediction.Exceptions.ServerException;
import com.weatherApp.weatherprediction.constant.Routes;
import com.weatherApp.weatherprediction.utils.SuggestionHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import  com.weatherApp.weatherprediction.model.*;
import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForeCastService extends MappingJackson2HttpMessageConverter   {

    private static final ForeCastService ourInstance = new ForeCastService();

    public static ForeCastService getInstance() {
        return ourInstance;
    }

    private ForeCastService() {
        setPrettyPrint(true);
    }
    public List<Example> getForecast(String city) throws ServerException, ClientException, DataNotFoundException, ParseException {

        SuggestionHelper suggestionHelper = new SuggestionHelper();

        StringBuilder forecastOpenAPI = new StringBuilder(Routes.FORECAST_OPEN_API);
        forecastOpenAPI.append(city)
                .append(Routes.API_PARAMETER)
                .append(Routes.API_APP_KEY)
                .append(Routes.API_UNIT_PARAMETER);
        RestService restService = new RestService();
        String result = restService.getRestCall(String.valueOf(forecastOpenAPI));


        String description = null;
        double temp=0;
        int pressure=0;
        int humidity = 0;
        int temp_min=1000;
        int temp_max=0;
        int temp_kf=0;
        int sea_level=0;
        int grnd_level=0;

        String date = null;
        String icon=null;
        String weatherCondition=null;
        int id=0;
        int windSpeed = 0;

        List<Example> weatherList = new ArrayList<>();

        JSONObject root = new JSONObject(result);
        JSONArray weatherObject = root.getJSONArray("list");


        for(int i = 0; i < weatherObject.length(); i++) {
            int nextIndex = (i+1) == weatherObject.length() ? i : i+1;
            JSONObject arrayElement = weatherObject.getJSONObject(i);

            JSONObject nextElement = weatherObject.getJSONObject(nextIndex);
            String nextDate = nextElement.getString("dt_txt");
            date = arrayElement.getString("dt_txt");
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Parse the input string to LocalDate
            LocalDate localDate = LocalDate.parse(date, inputFormatter);
            LocalDate nextLocalDate = LocalDate.parse(nextDate, inputFormatter);
            JSONObject main = arrayElement.getJSONObject("main");
            if(localDate.isEqual(LocalDate.now()) || localDate.isBefore(LocalDate.now())) {
                continue;

            }
            else{
                temp = (int) main.getFloat("temp");
                pressure =  main.getInt("pressure");
                humidity = main.getInt("humidity");
                temp_kf = main.getInt("temp_kf");
                sea_level = main.getInt("sea_level");
                grnd_level = main.getInt("grnd_level");
                description = arrayElement.getJSONArray("weather").getJSONObject(0).getString("description");
                icon = arrayElement.getJSONArray("weather").getJSONObject(0).getString("icon");
                weatherCondition = arrayElement.getJSONArray("weather").getJSONObject(0).getString("main");
                id = arrayElement.getJSONArray("weather").getJSONObject(0).getInt("id");
                windSpeed = arrayElement.getJSONObject("wind").getInt("speed");
                if(temp_min>main.getInt("temp_min"))
                    temp_min = main.getInt("temp_min");
                if(temp_max<main.getInt("temp_max"))
                    temp_max = main.getInt("temp_max");

            }
            if(nextIndex == i || nextLocalDate.isAfter(localDate))
            {
                //since data is hourly on each date populate it to object once we reach the last hour of a day
                ForecastWeather fw=new ForecastWeather();
                Main mn=new Main();
                Example e = new Example();
                Weather w = new Weather();


                mn.setTemp(temp);
                mn.setPressure((int) pressure/10);
                mn.setHumidity(humidity);
                mn.setGrndLevel(grnd_level);
                mn.setSeaLevel(sea_level);
                mn.setTempKf(temp_kf);

                mn.setTempMax(temp_max);
                mn.setTempMin(temp_min);
                mn.setWindSpeed(windSpeed);

                w.setDescription(description);
                w.setIcon(icon);
                w.setId(id);
                w.setMain(weatherCondition);
                fw.setDate(date);
                fw.setMain(mn);
                fw.setWeather(w);
                fw.setSuggestion(suggestionHelper.giveSuggestion(fw));
                e.setForecastWeather(fw);

                weatherList.add(e);
                temp_min=1000; // reinitializing for new day
                temp_max=0; // reinitializing for new day

            }
        }

        return weatherList;
    }
}

