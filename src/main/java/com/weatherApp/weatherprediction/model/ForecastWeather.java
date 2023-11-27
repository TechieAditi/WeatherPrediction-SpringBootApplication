package com.weatherApp.weatherprediction.model;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;



import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "time"
})

public class ForecastWeather {

    @JsonProperty("main")
    private  Main main;
    @JsonProperty("weather")
    private Weather weather;

    @JsonProperty("suggestion")
    private String suggestion;

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    @JsonProperty("weather")
    public Weather getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(Weather weather) {
        this.weather = weather;
    }


    @JsonProperty("date")
    private String date;


    @JsonProperty("date")
    public String getDate() {
        return date;
    }


    @JsonProperty("date")
    public void setDate(String date) {
        this.date= date;
    }

    @JsonProperty("suggestion")
    public void setSuggestion(String suggestion){
        this.suggestion = suggestion;
    }

    @JsonProperty("suggestion")
    public String setSuggestion(){
        return this.suggestion;
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getDate() );
        return buffer.toString();
    }

}