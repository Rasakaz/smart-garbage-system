package com.team27.garbageSystem.Server;

import com.team27.garbageSystem.Entities.Administrator;
import com.team27.garbageSystem.Entities.GarbageBin;
import com.team27.garbageSystem.Repository.AdministratorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@EnableMongoRepositories(basePackages = {"com.team27"})
@Configuration
public class DBConfig {
/*
    this class is invoke each time when the application start up, and will inject all the
    data to the data base.
 */

    // !!!!!! CHECK THE OPTION TO WORK WITH MongoTemplate !!!!! ~!~!!~!~!~!~~~!
    private final MongoTemplate mongoTemplate;

    public DBConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public void ParseBinsAndAddToDB(){
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

    @Bean
    CommandLineRunner commandLineRunner(AdministratorRepository administratorRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // first clean the repository
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
            }//end run
        };//end return
    }//end CommandLine function
}
