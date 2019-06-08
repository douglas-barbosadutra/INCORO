package it.incoro.app.repository;

import it.incoro.app.domain.Utente;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Spring Data MongoDB repository for the Utente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtenteRepository extends MongoRepository<Utente, String> {
//	@Query("{ 'username' : ?0 }")
    Optional<Utente> findByUsernameAndPassword(String username, String password);

}
