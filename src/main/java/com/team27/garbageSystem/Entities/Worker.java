package com.team27.garbageSystem.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Workers")
public class Worker extends User {

    private Double Salary;
    private Float Seniority;
    private Truck Truck;

    public Worker () {} //empty c'tor

    public Worker(String userName, String password, String firstName, String lastName, String permission, Double salary, Float seniority) {
        super(userName, password, firstName, lastName, permission);
        this.Salary = salary;
        this.Seniority = seniority;
        this.Truck = null;
    }

    public Worker(String id, String userName, String password, String firstName, String lastName, String permission, double salary, float seniority) {
        super(id, userName, password, firstName, lastName, permission);
        this.Salary = salary;
        this.Seniority = seniority;
        this.Truck = null;
    }

    // Getters
    public Double getSalary() { return Salary; }
    public Float getSeniority() { return Seniority; }
    public Truck getTruck() { return Truck; }

    // Setters
    public void setSalary(Double salary) { Salary = salary; }
    public void setSeniority(Float seniority) { Seniority = seniority; }
    public void setTruck(Truck truck) { Truck = truck; }

    @Override
    public String toString() {
        return super.toString() +
                "{ Worker: " + "\n"
                + "{ Salary: " + Salary + "," + "\n"
                + "Seniority: " + Seniority + "}"
                + "}";
    }
}
