package com.team27.garbageSystem.Repository;

import com.team27.garbageSystem.Entities.Administrator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends MongoRepository<Administrator, String> {} // Administrator is the class that we want to store, String is the key
