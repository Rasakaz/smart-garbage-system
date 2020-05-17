package com.team27.garbageSystem.controllers;

import com.team27.garbageSystem.Entities.GarbageBin;
import com.team27.garbageSystem.Entities.Worker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdministratorController {

    private final MongoTemplate mongoTemplate;

    public AdministratorController(MongoTemplate mongoTemplate) { this.mongoTemplate = mongoTemplate; }

    @RequestMapping(value = "/administrator/showBins", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<GarbageBin>> getAllBins(){
        //model.addAttribute("Bins", mongoTemplate.findAll(GarbageBin.class));
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
}
