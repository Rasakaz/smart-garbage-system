package com.team27.garbageSystem.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Trucks")
public class Truck {

    private Route Route;
    private int TruckNumber;

    public Truck(){}

    public Truck(Route route, int truckNumber) {
        Route = route;
        TruckNumber = truckNumber;
    }

    // Getters
    public Route getRoute() { return Route; }
    public int getTruckNumber() { return TruckNumber; }

    // Setters
    public void setRoute(Route route) { Route = route; }
    public void setTruckNumber(int truckNumber) { TruckNumber = truckNumber; }
}
