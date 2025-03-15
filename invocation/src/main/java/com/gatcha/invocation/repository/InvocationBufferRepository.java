package com.example.gatcha.repository;

import com.example.gatcha.model.InvocationBuffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvocationBufferRepository extends MongoRepository<InvocationBuffer, String> {
}