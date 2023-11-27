# weatherPrediction-SpringBootApplication
A Spring Boot application for Weather Prediction that uses OpenAPI from weatherMap and returns today's weather and prdiction for next 4 days

API :
/weather/{city} : gives today's weather in json format
/forecast/{city} gives weather with suggestions bease on weather for next 4 days

This application also contains Swagger implemnetation to know more about the underlying rest apis

To get started with this project :
Clone it with the git url or downlaod the zip folder
The embedded tomcat runs on 8081 on my local, you can set the same for your self by configuring it on service configuration 
Use this link :  http://localhost:8081/swagger-ui.html once your application is up to get the api details from swagger

