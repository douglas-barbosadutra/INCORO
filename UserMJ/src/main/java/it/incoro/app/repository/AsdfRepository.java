package it.incoro.app.repository;

import it.incoro.app.domain.Asdf;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Asdf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AsdfRepository extends MongoRepository<Asdf, String> {

}
