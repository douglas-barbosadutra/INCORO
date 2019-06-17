package it.incoro.app.service.impl;

import it.incoro.app.service.ThingService;
import it.incoro.app.domain.Thing;
import it.incoro.app.repository.ThingRepository;
import it.incoro.app.service.dto.ThingDTO;

import it.incoro.app.service.mapper.ThingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Thing.
 */
@Service
public class ThingServiceImpl implements ThingService {

    private final Logger log = LoggerFactory.getLogger(ThingServiceImpl.class);

    private final ThingRepository thingRepository;

    private final ThingMapper thingMapper;

    public ThingServiceImpl(ThingRepository thingRepository, ThingMapper thingMapper) {
        this.thingRepository = thingRepository;
        this.thingMapper = thingMapper;
    }

    /**
     * Save a thing.
     *
     * @param thingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ThingDTO save(ThingDTO thingDTO) {
        log.debug("Request to save Thing : {}", thingDTO);
        Thing thing = thingMapper.toEntity(thingDTO);
        thing = thingRepository.save(thing);
        return thingMapper.toDto(thing);
    }

    /**
     * Get all the things.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<ThingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Things");
        return thingRepository.findAll(pageable)
            .map(thingMapper::toDto);
    }


    /**
     * Get one thing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<ThingDTO> findOne(String id) {
        log.debug("Request to get Thing : {}", id);
        return thingRepository.findById(id)
            .map(thingMapper::toDto);
    }

    /**
     * Delete the thing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Thing : {}", id);
        thingRepository.deleteById(id);
    }
    
   
}
