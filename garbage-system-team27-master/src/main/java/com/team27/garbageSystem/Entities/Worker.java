package com.team27.garbageSystem.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Workers")
public class Worker extends User {

    private Double Salary;
    private Float Seniority;

    public Worker () {} //empty c'tor

    public Worker(String userName, String password, String firstName, String lastName, String permission, Double salary, Float seniority) {
        super(userName, password, firstName, lastName, permission);
        this.Salary = salary;
        this.Seniority = seniority;
    }

    // Getters
    public Double getSalary() { return Salary; }
    public Float getSeniority() { return Seniority; }

    // Setters
    public void setSalary(Double salary) { Salary = salary; }
    public void setSeniority(Float seniority) { Seniority = seniority; }

    @Override
    public String toString() {
        return super.toString() +
                "{ Worker: " + "\n"
                + "{ Salary: " + Salary + "," + "\n"
                + "Seniority: " + Seniority + "}"
                + "}";
    }
}
