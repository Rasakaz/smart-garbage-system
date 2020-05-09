package com.team27.garbageSystem.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Administrators")
public class Administrator extends User {

    private Double Salary;
    private Float Seniority;

    public Administrator () {}

    public Administrator(String userName, String password, String firstName, String lastName, String permission, double salary, float seniority) {
        super(userName, password, firstName, lastName, permission);
        this.Salary = salary;
        this.Seniority = seniority;
    }

    public Administrator(String id, String userName, String password, String firstName, String lastName, String permission, double salary, float seniority) {
        super(id, userName, password, firstName, lastName, permission);
        this.Salary = salary;
        this.Seniority = seniority;
    }

    // Getters
    public Double getSalary() { return Salary; }
    public Float getSeniority() { return Seniority; }

    // Setters
    public void setSalary(double salary) { this.Salary = salary; }
    public void setSeniority(float seniority) { this.Seniority = seniority; }

    @Override
    public String toString() {
        return super.toString() +
                "{ Administrator: " + "\n"
                + "{ Salary: " + Salary + "," + "\n"
                + "Seniority: " + Seniority + "}"
                + "}";
    }
}
