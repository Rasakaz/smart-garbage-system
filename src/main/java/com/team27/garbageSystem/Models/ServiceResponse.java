package com.team27.garbageSystem.Models;


public class ServiceResponse<T> {

    private String status;
    private T data;


    public ServiceResponse(String stat, T dat) {
        this.status = stat;
        this.data = dat;
    }
}
