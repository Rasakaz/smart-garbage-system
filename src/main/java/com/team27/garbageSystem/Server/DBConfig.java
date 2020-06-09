package com.team27.garbageSystem.Server;

import com.team27.garbageSystem.Entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.sound.sampled.Line;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


@EnableMongoRepositories(basePackages = {"com.team27"})
@Configuration
public class DBConfig {
/*
    this class is invoke each time when the application start up, and will inject all the
    data to the data base.
 */

    private final MongoTemplate mongoTemplate;

    public DBConfig(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
        InjectAdministrators();
        ParseBinsAndAddToDB();
        InjectWorkersToDB();
        InjectTrucksToDB();

        System.out.println("\n\nDBConfig done successfully.\n");
    }

/*
    InjectTruckToDB - function that inject all the trucks to the db by the line and route.
 */
    private void InjectTrucksToDB(){
        //first clean all truck collection
        mongoTemplate.dropCollection(Truck.class);
        List<Route> routes = new ArrayList<>(mongoTemplate.findAll(Route.class));
        for(Route r: routes){
            mongoTemplate.save(new Truck(r, new Random().nextInt(1000000)));
        }

    }

    /*
        InjectRoutesToDB - function that get a list of names of all the neighborhood that have the bins,
        then map the Garbage bins by their neighborhood location and add it to route with line number and all the
        garbage in the line.

     */
    private void InjectRoutesToDB(List<String> neighborhoods){
        //first clean the collection in the data base
        mongoTemplate.dropCollection(Route.class);
        List<GarbageBin> bins = new ArrayList<>(mongoTemplate.findAll(GarbageBin.class));
        Map<String, List<GarbageBin>> bin_neighbor = new HashMap<String, List<GarbageBin>>();
        for(String n: neighborhoods){
            List<GarbageBin> temp_bin = new ArrayList<>();
            for(GarbageBin bin: bins){
                if(bin.getNeighborhood().equals(n)){
                    temp_bin.add(bin);
                }
            }
            bin_neighbor.put(n, temp_bin);
        }
        List<GarbageBin> minG = new ArrayList<>();
        int LineCounter = 1;
        for(Map.Entry<String, List<GarbageBin>> entry: bin_neighbor.entrySet()){
            if(entry.getValue().size() < 15){
                for(GarbageBin gBin: entry.getValue()){
                    minG.add(gBin);
                }//end for add the min
            }
            else {
                List<GarbageBin> LineBins = new ArrayList<>();
                for(GarbageBin gBin: entry.getValue()){
                    LineBins.add(gBin);
                    if(LineBins.size() == 30){
                        mongoTemplate.save(new Route(LineBins, LineCounter));
                        LineCounter++;
                        LineBins.clear();
                    }
                }//end for add the max
                if(LineBins.size() > 10) {
                    mongoTemplate.save(new Route(LineBins, LineCounter));
                    LineCounter++;
                } else {
                    for(GarbageBin gBin: LineBins){
                        minG.add(gBin);
                    }
                }
            }
        }//end run on the Map
        mongoTemplate.save(new Route(minG, LineCounter));
    }



    private void ParseBinsAndAddToDB(){
        try{
            List<String> lines = Files.readAllLines(Paths.get("src//main//resources//static//garbage-bins.csv"), StandardCharsets.UTF_8);
            mongoTemplate.dropCollection(GarbageBin.class); //clean GarbageBin collection
            List<String> neighborhoods = new ArrayList<>();
            for(int i = 1; i < lines.size(); i++){
                String [] result = lines.get(i).split(",");
                if(!neighborhoods.contains(result[1])){
                    neighborhoods.add(result[1]);
                }
                mongoTemplate.save(new GarbageBin(i, result[1], Double.parseDouble(result[2]), Double.parseDouble(result[3])));
            }
            InjectRoutesToDB(neighborhoods); // after inject all the garbage bins -- then inject the Routes with mapping
          //  mongoTemplate.findAll(GarbageBin.class).forEach(System.out::println);

        } catch(Exception er){
            System.out.println(er.getMessage());
        }
    }

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

//        mongoTemplate.findAll(Worker.class).forEach(System.out::println);
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
//        mongoTemplate.findAll(Administrator.class).forEach(System.out::println);
    }
}
