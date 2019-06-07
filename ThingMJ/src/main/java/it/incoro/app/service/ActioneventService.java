package it.incoro.app.service;

import it.incoro.app.service.dto.ActioneventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Actionevent.
 */
public interface ActioneventService {

    /**
     * Save a actionevent.
     *
     * @param actioneventDTO the entity to save
     * @return the persisted entity
     */
    ActioneventDTO save(ActioneventDTO actioneventDTO);

    /**
     * Get all the actionevents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActioneventDTO> findAll(Pageable pageable);


    /**
     * Get the "id" actionevent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ActioneventDTO> findOne(String id);

    /**
     * Delete the "id" actionevent.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
