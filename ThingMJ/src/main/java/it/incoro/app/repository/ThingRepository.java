package it.incoro.app.repository;

import it.incoro.app.domain.Thing;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Thing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThingRepository extends MongoRepository<Thing, String> {
	
}
