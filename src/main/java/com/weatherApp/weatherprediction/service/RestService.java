package com.weatherApp.weatherprediction.service;

import com.weatherApp.weatherprediction.Exceptions.DataNotFoundException;
import com.weatherApp.weatherprediction.Exceptions.ServerException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.rmi.UnknownHostException;

public class RestService {
    public String getRestCall(String restApi) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(restApi, String.class);
            if (result == null) {
                throw new DataNotFoundException("No data found for the query passed");
            }
            return result;
        } catch (DataNotFoundException e){
            throw new DataNotFoundException("No data found for the query passed");
        } catch (RuntimeException e) {
            throw new ServerException("exception while making rest call to open api. Service is inaccessible");
        }
    }
}


