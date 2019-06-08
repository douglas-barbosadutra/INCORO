package it.incoro.app.service;

import it.incoro.app.service.dto.UtenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Utente.
 */
public interface UtenteService {

    /**
     * Save a utente.
     *
     * @param utenteDTO the entity to save
     * @return the persisted entity
     */
    UtenteDTO save(UtenteDTO utenteDTO);

    /**
     * Get all the utentes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" utente.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtenteDTO> findOne(String id);

    /**
     * Delete the "id" utente.
     *
     * @param id the id of the entity
     */
    void delete(String id);
    
    Optional<UtenteDTO> findUsernameAndPassword(String username, String password);
	
}
