package com.team27.garbageSystem.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GarbageBins")
public class GarbageBin {
    private int Id;
    private Double Longitude; // the x coordinate of the garbage bin
    private Double Latitude; // the y coordinate of the garbage bin

    public GarbageBin() {} // empty c'tor

    public GarbageBin(int id, Double longitude, Double latitude) {
        this.Id = id;
        this.Longitude = longitude;
        this.Latitude = latitude;
    }

    // Getters
    public int getId() { return Id; }
    public Double getLongitude() { return Longitude; }
    public Double getLatitude() { return Latitude; }

    // Setters
    public void setId(int id) { Id = id; }
    public void setLongitude(Double longitude) { Longitude = longitude; }
    public void setLatitude(Double latitude) { Latitude = latitude; }

    @Override
    public String toString() {
        return "{ Longitude: " + Longitude + ",\n"
                + "Latitude: " + Latitude + "}";
    }


}

