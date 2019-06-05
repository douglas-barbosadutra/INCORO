package it.incoro.app.service;

import it.incoro.app.service.dto.AsdfDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Asdf.
 */
public interface AsdfService {

    /**
     * Save a asdf.
     *
     * @param asdfDTO the entity to save
     * @return the persisted entity
     */
    AsdfDTO save(AsdfDTO asdfDTO);

    /**
     * Get all the asdfs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AsdfDTO> findAll(Pageable pageable);


    /**
     * Get the "id" asdf.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AsdfDTO> findOne(String id);

    /**
     * Delete the "id" asdf.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
