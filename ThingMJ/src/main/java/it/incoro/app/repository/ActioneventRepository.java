package it.incoro.app.repository;

import it.incoro.app.domain.Actionevent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Actionevent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActioneventRepository extends MongoRepository<Actionevent, String> {

}
