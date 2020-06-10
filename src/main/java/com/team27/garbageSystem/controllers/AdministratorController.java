package com.team27.garbageSystem.controllers;

import com.team27.garbageSystem.Entities.GarbageBin;
import com.team27.garbageSystem.Entities.Route;
import com.team27.garbageSystem.Entities.Truck;
import com.team27.garbageSystem.Entities.Worker;
import com.team27.garbageSystem.Models.ServiceResponse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AdministratorController {

    private final MongoTemplate mongoTemplate;

    public AdministratorController(MongoTemplate mongoTemplate) { this.mongoTemplate = mongoTemplate; }

    @RequestMapping(value = "/administrator/showBins", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<GarbageBin>> getAllBins(){
        List<GarbageBin> bins = new ArrayList<>(mongoTemplate.findAll(GarbageBin.class));
        Map<String, List<GarbageBin>> res = new HashMap<>();
        res.put("bins",bins);
        return res;
    }

    @RequestMapping(value = "/administrator/showWorkers", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Worker>> getAllWorkers(){
        List<Worker> workers = new ArrayList<>(mongoTemplate.findAll(Worker.class));
        Map<String, List<Worker>> res = new HashMap<>();
        res.put("workers",workers);
        return res;
    }

    @RequestMapping(value = "/administrator/getBinsAndLines", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Integer>> getBinsAndLines(){
        List<GarbageBin> bins = new ArrayList<>(mongoTemplate.findAll(GarbageBin.class));
        List<Route> routes = new ArrayList<>(mongoTemplate.findAll(Route.class));
        Map<String, List<Integer>> res = new HashMap<>();
        List<Integer> binsId = new ArrayList<>();
        List<Integer> lines = new ArrayList<>();
        for(GarbageBin b: bins){
            binsId.add(b.getId());
        }
        for(Route r: routes){
            lines.add(r.getLineNumber());
        }
        res.put("id", binsId);
        res.put("line", lines);
        return res;
    }

    @RequestMapping(value = "/administrator/addBinToLine", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> addBinToLine(@RequestBody Map<String, String> parameters){
        List<Route> routes = new ArrayList<>(mongoTemplate.findAll(Route.class));
        List<GarbageBin> bins = new ArrayList<>(mongoTemplate.findAll(GarbageBin.class));
        GarbageBin bin = null;
        String data = "";
        for(GarbageBin b: bins){
            if(b.getId() == Integer.parseInt(parameters.get("id"))){
                bin = b;
                break;
            }
        }
        for(Route r: routes){
            if(r.getLineNumber() == Integer.parseInt(parameters.get("line"))){
                    for(GarbageBin b: r.getLineBins()){
                        if(b.getId() == Integer.parseInt(parameters.get("id"))){
                        data = "Bin is already in this line!!";
                        return new ServiceResponse("sucsses", data);
                        }
                }
                r.getLineBins().add(bin);
                mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("LineNumber").is(Integer.parseInt(parameters.get("line")))), Update.update("LineBins", r.getLineBins()), Route.class);
                data = "Bin added successfully.";
                break;
            }
        }
        return new ServiceResponse("sucsses", data);
    }

    @RequestMapping(value = "/administrator/getTruckAndRoutes", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Integer>> getTruckAndRoutes(){
        List<Truck> trucks = new ArrayList<>(mongoTemplate.findAll(Truck.class));
        List<Route> routes = new ArrayList<>(mongoTemplate.findAll(Route.class));
        Map<String, List<Integer>> res = new HashMap<>();
        List<Integer> truckNumber = new ArrayList<>();
        List<Integer> lines = new ArrayList<>();
        for(Truck t: trucks){
            truckNumber.add(t.getTruckNumber());
        }
        for(Route r: routes){
            lines.add(r.getLineNumber());
        }
        res.put("truck", truckNumber);
        res.put("line", lines);
        return res;
    }

    @RequestMapping(value = "/administrator/changeTruckRoute", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> changeTruckRoute(@RequestBody Map<String, String> parameters){
        List<Route> routes = new ArrayList<>(mongoTemplate.findAll(Route.class));
        List<Truck> trucks = new ArrayList<>(mongoTemplate.findAll(Truck.class));
        String data = "Error!!";
        for(Route r: routes){
            if(r.getLineNumber() == Integer.parseInt(parameters.get("line"))){
                for(Truck t: trucks){
                    if(t.getTruckNumber() == Integer.parseInt(parameters.get("truck"))){
                        Route rTmp = t.getRoute();
                        t.getRoute().setLineBins(rTmp.getLineBins());
                        t.setRoute(r);
                        mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("TruckNumber").is(Integer.parseInt(parameters.get("truck")))), Update.update("Route", r), Truck.class);
                        //swap the route with the truck that was worked with the route to change
                        for(Truck tt: trucks){
                            if(tt.getRoute().getLineNumber() == r.getLineNumber()){
                                tt.getRoute().setLineBins(t.getRoute().getLineBins());
                                mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("TruckNumber").is(tt.getTruckNumber())), Update.update("Route", tt), Truck.class);
                                break;
                            }
                        }
                        data = "Truck line update successfully.";
                        return new ServiceResponse("sucsses", data);
                    }
                }
            }
        }
        return new ServiceResponse("failed", data);
    }

}
