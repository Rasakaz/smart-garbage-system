package com.team27.garbageSystem.Server;

import com.team27.garbageSystem.Entities.Administrator;
import com.team27.garbageSystem.Entities.GarbageBin;
import com.team27.garbageSystem.Entities.Worker;
import com.team27.garbageSystem.Repository.AdministratorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// @EnableMongoRepositories(basePackages = {"com.team27"})
// @Configuration
public class DBConfig {
/*
    this class is invoke each time when the application start up, and will inject all the
    data to the data base.
 */

    // !!!!!! CHECK THE OPTION TO WORK WITH MongoTemplate !!!!! ~!~!!~!~!~!~~~!
    private final MongoTemplate mongoTemplate;

    public DBConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        InjectAdministrators();
        InjectWorkersToDB();
        ParseBinsAndAddToDB();
    
    }

//     @Bean
//     public void InjectToDB() {
//         InjectAdministrators();
//         InjectWorkersToDB();
//         ParseBinsAndAddToDB();
//     }

    private void InjectWorkersToDB() {
        mongoTemplate.dropCollection(Worker.class);//clean workers collection before inject

        //inject workers to DB
        mongoTemplate.save(new Worker(
                "worker1",
                "1234worker1",
                "Liam",
                "Ava",
                "Worker",
                6500.0,
                (float) 1.5), "Workers");

        mongoTemplate.save(new Worker(
                "worker2",
                "1234worker2",
                "Thomas",
                "Harry",
                "Worker",
                7500.0,
                (float) 3.1), "Workers");

        mongoTemplate.save(new Worker(
                "worker3",
                "1234worker3",
                "Robers",
                "Oscar",
                "Worker",
                5500.0,
                (float) 0.3), "Workers");

        mongoTemplate.save(new Worker(
                "worker4",
                "1234worker4",
                "Joseph",
                "James",
                "Worker",
                6350.0,
                (float) 0.9), "Workers");

        mongoTemplate.findAll(Worker.class).forEach(System.out::println);

    }

    private void ParseBinsAndAddToDB(){
        try{
            List<String> lines = Files.readAllLines(Paths.get("src//main//resources//static//garbage-bins.csv"));
            mongoTemplate.dropCollection(GarbageBin.class); //clean GarbageBin collection
            for(int i = 1; i < lines.size(); i++){
                String [] result = lines.get(i).split(",");
                mongoTemplate.save(new GarbageBin(i, Double.parseDouble(result[2]), Double.parseDouble(result[3])));
            }
            mongoTemplate.findAll(GarbageBin.class).forEach(System.out::println);
        } catch(Exception er){
            System.out.println(er.getMessage());
        }
    }

    private void InjectAdministrators(){
        mongoTemplate.dropCollection(Administrator.class);
        // inject to mongodb the administrators
        mongoTemplate.save(new Administrator(
                "admin1",
                "1234admin1",
                "Ofir",
                "Haim",
                "Admin",
                16500,
                (float) 1.5), "Administrators");


        mongoTemplate.save(new Administrator(
                "admin2",
                "1234admin2",
                "Yaakov",
                "Levi",
                "Admin",
                16500,
                (float) 1.5), "Administrators");

        mongoTemplate.save(new Administrator(
                "admin3",
                "1234admin3",
                "Som",
                "Sansu",
                "Admin",
                11150,
                (float) 0.95), "Administrators");

        mongoTemplate.save(new Administrator(
                "admin4",
                "1234admin4",
                "Fedu",
                "Bout",
                "Admin",
                9500,
                (float) 0.1), "Administrators");


        // pull all the administrators that injected to check correct
        mongoTemplate.findAll(Administrator.class).forEach(System.out::println);
    }
}
