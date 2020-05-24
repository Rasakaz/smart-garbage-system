package com.team27.garbageSystem.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @org.springframework.data.annotation.Id
    private String Id;
    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Permission;

    public User () {} // default c'tor


    public User(String userName, String password, String firstName, String lastName, String permission){ // parameters c'tor
        this.Id = null; // give it null value because this is the mongodb id and it define it when add to mongodb
        this.UserName = userName;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Permission = permission;
    }

    public User(String id, String userName, String password, String firstName, String lastName, String permission){ // parameters c'tor
        this.Id = id; // give it null value because this is the mongodb id and it define it when add to mongodb
        this.UserName = userName;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Permission = permission;
    }

    // Getters
    public String getId() {
        return Id;
    }
    public String getUserName() { return UserName; }
    public String getPassword() {
        return Password;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getPermission() {
        return Permission;
    }


    // Setters
    public void setUserName(String userName) {
        UserName = userName;
    }
    public void setPassword(String password) {
        UserName = password;
    }
    public void setFirstName(String firstName) {
        UserName = firstName;
    }
    public void setLastName(String lastName) {
        UserName = lastName;
    }
    public void setPermission(String permission) {
        UserName = permission;
    }

    @Override
    public String toString() {
        return "{ User: " + "\n" + "{ Id: " + Id + "," + "\n"
                + "UserName: " + UserName + "," + "\n"
                + "Password: " + Password + "," + "\n"
                + "FirstName: " + FirstName + "," + "\n"
                + "LastName: " + LastName + "," + "\n"
                + "Permission: " + Permission + "}\n";

    }
}
