package com.weatherApp.weatherprediction.Exceptions;

public class ClientException extends RuntimeException{
    public ClientException(String message){
        super(message);
    }
}
