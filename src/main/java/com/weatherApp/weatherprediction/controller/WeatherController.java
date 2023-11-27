package com.weatherApp.weatherprediction.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import com.weatherApp.weatherprediction.Exceptions.DataNotFoundException;
import com.weatherApp.weatherprediction.Exceptions.ServerException;
import com.weatherApp.weatherprediction.service.ForeCastService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.weatherApp.weatherprediction.model.Example;

import com.weatherApp.weatherprediction.service.WeatherService;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final ForeCastService foreCastService;

    @Autowired
    public WeatherController(WeatherService weatherService, ForeCastService foreCastService) {
        this.weatherService = weatherService;
        this.foreCastService = foreCastService;
    }


    @RequestMapping(method = RequestMethod.GET, value="forecast/{city}")
    public List<Example> getForecast(
            @PathVariable String city)  {
        try{
            return this.foreCastService.getForecast(city);
        }catch (ServerException | ParseException serverException){
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service Exception Occurred");
        } catch (DataNotFoundException dataNotFoundException){
            throw new ResponseStatusException(HttpStatus.OK, "No data found for the requested query");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "weather/{city}")
    public List<Example> getWeather(
            @PathVariable String city) {
        try {
            return this.weatherService.getWeather(city);
        } catch (ServerException serverException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service Exception Occurred");
        } catch (DataNotFoundException dataNotFoundException) {
            throw new ResponseStatusException(HttpStatus.OK, "No data found for the requested query");
        }
    }



}