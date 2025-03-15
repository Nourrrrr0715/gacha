package com.gatcha.monstre.repository;

import com.gatcha.monstre.model.Monstre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonstreRepository extends MongoRepository<Monstre, String> {}
