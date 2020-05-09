package com.team27.garbageSystem.controllers;

import com.team27.garbageSystem.Entities.Administrator;
import com.team27.garbageSystem.Entities.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final MongoTemplate mongoTemplate;
    private static User sessionUser = null; //session user

    public LoginController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

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

    @RequestMapping(value = "/login/checkDetails", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(@RequestBody Map<String, String> parameters) {
        sessionUser = null;
        if(parameters.get("UserType").equals("admin")){
            System.out.println("login check");
            List<Administrator> adminUser = checkAdministratorDetails(parameters);
            if(adminUser != null && adminUser.size() == 1){
                System.out.println("inside the if");
                sessionUser = new Administrator(
                        adminUser.get(0).getId(),
                        adminUser.get(0).getUserName(),
                        adminUser.get(0).getPassword(),
                        adminUser.get(0).getFirstName(),
                        adminUser.get(0).getLastName(),
                        adminUser.get(0).getPermission(),
                        adminUser.get(0).getSalary(),
                        adminUser.get(0).getSeniority()
                );
                System.out.println("in in in");
                //return a response fail/success
                return "success";
            }
        }
        //add response that data was not good
        //return a response fail/success
        return "failed";
    }

    private List<Administrator> checkAdministratorDetails(Map<String, String> parameters) {
        System.out.println("inside check Administrator!!");
        return mongoTemplate.find(new Query().addCriteria(
                new Criteria().andOperator(Criteria.where("UserName")
                                .is(parameters.get("UserName")),
                        Criteria.where("Password")
                                .is(parameters.get("Password")))), Administrator.class);
    }

    private boolean checkWorkerDetails(Map<String, String> parameters) {
        return false;
    }

}


