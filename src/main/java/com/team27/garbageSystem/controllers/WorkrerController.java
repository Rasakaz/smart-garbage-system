package com.team27.garbageSystem.controllers;

import com.team27.garbageSystem.Entities.Route;
import com.team27.garbageSystem.Entities.Truck;
import com.team27.garbageSystem.Entities.Worker;
import com.team27.garbageSystem.Models.ServiceResponse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class WorkrerController {

    private final MongoTemplate mongoTemplate;

    public WorkrerController(MongoTemplate mongoTemplate) { this.mongoTemplate = mongoTemplate; }


    @RequestMapping(value = "/worker/showTruckRoute", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<Truck> getRouteTruck() {
        List<Truck> truck = new ArrayList<>(mongoTemplate.findAll(Truck.class));
        Worker worker = (Worker)LoginController.sessionUser;
        if(worker.getTruck() == null){ // if the worker doest connected to a truck then add to him truck.
            worker.setTruck(truck.get(new Random().nextInt(truck.size())));
        }
        return new ServiceResponse<Truck>("success",  worker.getTruck());
    }

}
