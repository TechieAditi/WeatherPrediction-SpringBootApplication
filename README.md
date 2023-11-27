# Weather Prediction Spring Boot Application

## Project Overview

The **weatherPrediction-SpringBootApplication** is a Spring Boot application designed for weather prediction using the OpenAPI from WeatherMap. It provides information on today's weather and predicts weather conditions for the next 4 days. The application also offers personalized suggestions based on various weather conditions, enhancing the user experience.

## Note

This application uses the WeatherMap OpenAPI, and you need to bring your own API key. Make sure to obtain an API key from WeatherMap and use it in the application to make REST calls to the OpenAPI.

## Features

- **Today's Weather Endpoint:** `/weather/{city}` returns today's weather in JSON format.
- **4-Day Weather Forecast Endpoint:** `/forecast/{city}` provides weather predictions for the next 4 days with personalized suggestions.

## Personalized Suggestions

The application provides personalized suggestions based on the following conditions:

1. **Rain Prediction:** If rain is predicted in the next 3 days, the suggestion is to "Carry an umbrella."
2. **High Temperature:** If the temperature goes above 40 degrees Celsius, the suggestion is to "Use sunscreen lotion."
3. **High Winds:** If the wind speed is at 10mph, the suggestion is "It’s too windy, watch out!"
4. **Thunderstorms:** In the case of thunderstorms, the suggestion is "Don’t step out! A storm is brewing!"

Users can dynamically view results by changing input parameters to the specified endpoints.

## API Endpoints

- `/weather/{city}`: Returns today's weather in JSON format.
- `/forecast/{city}`: Provides weather predictions for the next 4 days with personalized suggestions.

## Swagger Implementation

The application includes Swagger for easy exploration of the underlying REST APIs. Access Swagger UI using the link: [Swagger UI](http://localhost:8081/swagger-ui.html) once the application is up and running.

## Technologies Used

- Java
- Spring Boot
- Swagger Implementation with Spring Boot
- RESTful API Creation

## Dependencies

This backend component complements the Weather Application UI. For the UI, use this [link](https://github.com/TechieAditi/WeatherForcastApp-ReactJSApplication) to clone the UI part.

## Installation

To get started with this project:

1. Clone the repository using the provided git URL or download the ZIP folder: [GitHub Repository](https://github.com/TechieAditi/WeatherPrediction-SpringBootApplication).
2. Run a Maven build to load all dependencies into the local Maven repository.
3. Configure the embedded Tomcat to run on port 8081 (or your preferred port) in the service configuration.
4. Access the Swagger UI at [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html) to explore the available APIs once the application is up.

**Note:** Ensure you have Java 11 and a supporting IDE for Maven build.

Feel free to explore, contribute, and customize this Weather Prediction Spring Boot Application!

---
**Note:** Replace the placeholder `{city}` in the endpoint paths with the actual city name when making API requests.
