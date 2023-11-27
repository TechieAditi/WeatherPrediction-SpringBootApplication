package com.weatherApp.weatherprediction.utils;

import com.weatherApp.weatherprediction.constant.Constants;
import com.weatherApp.weatherprediction.model.ForecastWeather;
import com.weatherApp.weatherprediction.model.WeatherCondition;


public class SuggestionHelper {
    public String giveSuggestion(ForecastWeather fw){

        if(fw.getWeather().getMain().equalsIgnoreCase(String.valueOf(WeatherCondition.Rain))){
            return Constants.RAINY_DAY_SUGGESTION;
        } else if (fw.getMain().getTemp()>40) {
            return Constants.SUNNY_DAY_SUGGESTION;

        } else if (fw.getMain().getWindSpeed()>10) {
            return  Constants.WINDY_DAY_SUGGESTION;

        } else if ((fw.getWeather().getMain().equalsIgnoreCase(String.valueOf(WeatherCondition.Thunderstorms)))) {
            return Constants.STORMY_DAY_SUGGESTION;
        } else{
            return null;
        }


    }
}
