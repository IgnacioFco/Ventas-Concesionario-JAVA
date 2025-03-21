package com.ProyectoConcJPASpring.ProyectoC.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorldClockResponse {
    
    @JsonProperty("currentDateTime")
    private String currentDateTime;
    
    @JsonProperty("utcOffset")
    private String utcOffset;
    
    @JsonProperty("dayOfTheWeek")
    private String dayOfTheWeek;
    
    @JsonProperty("timeZoneName")
    private String timeZoneName;
    
    // Getters y Setters
    public String getCurrentDateTime() {
        return currentDateTime;
    }
    
    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
    
    public String getUtcOffset() {
        return utcOffset;
    }
    
    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }
    
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
    
    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
    
    public String getTimeZoneName() {
        return timeZoneName;
    }
    
    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }
}
