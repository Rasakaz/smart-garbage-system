package com.team27.garbageSystem.Models;


public class ServiceResponse<T> {

    private String Status;
    private T Data;

    public ServiceResponse(String stat, T dat) {
        this.Status = stat;
        this.Data = dat;
    }

    public String getStatus() { return Status; }
    public T getData() { return Data; }

    public void setStatus(String status) { Status = status; }
    public void setData(T data) { Data = data; }
}
