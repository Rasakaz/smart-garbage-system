package com.team27.garbageSystem.controllers;

import com.team27.garbageSystem.Entities.Administrator;
import com.team27.garbageSystem.Entities.User;
import com.team27.garbageSystem.Entities.Worker;
import com.team27.garbageSystem.Models.ServiceResponse;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final MongoTemplate mongoTemplate;
    public static User sessionUser = null; //session user


    public LoginController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

//    public LoginController() {}

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/administrator")
    public String administratorLogin(Model model) {
        if(sessionUser == null){
            return "redirect:/login";
        }
        model.addAttribute("User", sessionUser);
        return "administratorHome";
    }

    @GetMapping("/worker")
    public String workerLogin(Model model) {
        if(sessionUser == null){
            return "redirect:/login";
        }
        model.addAttribute("User", sessionUser);
        return "workerHome";
    }


    @RequestMapping(value = "/login/checkDetails", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> loginCheck(@RequestBody Map<String, String> parameters) {
        sessionUser = null;
        List<Administrator> adminUser = checkAdminUser(parameters);
        if(adminUser != null && adminUser.size() == 1 && adminUser.get(0).getPermission().equals("Admin")){
            sessionUser = new Administrator(
                adminUser.get(0).getId(),
                adminUser.get(0).getUserName(),
                adminUser.get(0).getPassword(),
                adminUser.get(0).getFirstName(),
                adminUser.get(0).getLastName(),
                adminUser.get(0).getPermission(),
                ((Administrator)adminUser.get(0)).getSalary(),
                ((Administrator)adminUser.get(0)).getSeniority()
            );
            return new ServiceResponse("success",  adminUser.get(0).getPermission());
        } else {
            List<Worker> workerUser = checkWorkerUser(parameters);
            if(workerUser != null && workerUser.size() == 1 && workerUser.get(0).getPermission().equals("Worker")) {
                sessionUser = new Worker(
                        workerUser.get(0).getId(),
                        workerUser.get(0).getUserName(),
                        workerUser.get(0).getPassword(),
                        workerUser.get(0).getFirstName(),
                        workerUser.get(0).getLastName(),
                        workerUser.get(0).getPermission(),
                        ((Worker) workerUser.get(0)).getSalary(),
                        ((Worker) workerUser.get(0)).getSeniority()
                );
                return new ServiceResponse("success", workerUser.get(0).getPermission());
            }
        }
        return new ServiceResponse<>("failed", "response");
    }

    private List<Administrator> checkAdminUser(Map<String, String> parameters) {
        return mongoTemplate.find(new Query().addCriteria(
                new Criteria().andOperator(Criteria.where("UserName")
                                .is(parameters.get("UserName")),
                        Criteria.where("Password")
                                .is(parameters.get("Password")))), Administrator.class);
    }

    private List<Worker> checkWorkerUser(Map<String, String> parameters) {
        return mongoTemplate.find(new Query().addCriteria(
                new Criteria().andOperator(Criteria.where("UserName")
                                .is(parameters.get("UserName")),
                        Criteria.where("Password")
                                .is(parameters.get("Password")))), Worker.class);
    }
}


