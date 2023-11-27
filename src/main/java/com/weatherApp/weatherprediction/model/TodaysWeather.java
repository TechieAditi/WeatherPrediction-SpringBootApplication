package com.weatherApp.weatherprediction.model;
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
        "today"
})
public class TodaysWeather {

    @JsonProperty("today")
    private Today today;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("today")
    public Today getToday() {
        return today;
    }

    @JsonProperty("today")
    public void setToday(Today today) {
        this.today = today;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}
