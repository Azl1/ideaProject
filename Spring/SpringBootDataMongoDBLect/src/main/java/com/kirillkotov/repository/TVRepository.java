package com.kirillkotov.repository;

import com.kirillkotov.model.TV;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVRepository extends MongoRepository<TV, String> {
}
