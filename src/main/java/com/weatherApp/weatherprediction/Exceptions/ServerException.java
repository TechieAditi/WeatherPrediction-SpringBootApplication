package com.weatherApp.weatherprediction.Exceptions;

public class ServerException extends RuntimeException{
    public ServerException(String message){
        super(message);
    }
}
