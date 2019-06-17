package it.incoro.app.service;

import it.incoro.app.service.dto.ThingDTO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Thing.
 */
public interface ThingService {

    /**
     * Save a thing.
     *
     * @param thingDTO the entity to save
     * @return the persisted entity
     */
    ThingDTO save(ThingDTO thingDTO);

    /**
     * Get all the things.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ThingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" thing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ThingDTO> findOne(String id);

    /**
     * Delete the "id" thing.
     *
     * @param id the id of the entity
     */
    void delete(String id);
    
}
