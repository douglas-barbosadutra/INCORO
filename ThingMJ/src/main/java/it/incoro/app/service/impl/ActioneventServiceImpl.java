package it.incoro.app.service.impl;

import it.incoro.app.service.ActioneventService;
import it.incoro.app.domain.Actionevent;
import it.incoro.app.repository.ActioneventRepository;
import it.incoro.app.service.dto.ActioneventDTO;
import it.incoro.app.service.mapper.ActioneventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
/**
 * Service Implementation for managing Actionevent.
 */
@Service
public class ActioneventServiceImpl implements ActioneventService {

    private final Logger log = LoggerFactory.getLogger(ActioneventServiceImpl.class);

    private final ActioneventRepository actioneventRepository;

    private final ActioneventMapper actioneventMapper;

    public ActioneventServiceImpl(ActioneventRepository actioneventRepository, ActioneventMapper actioneventMapper) {
        this.actioneventRepository = actioneventRepository;
        this.actioneventMapper = actioneventMapper;
    }

    /**
     * Save a actionevent.
     *
     * @param actioneventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ActioneventDTO save(ActioneventDTO actioneventDTO) {
        log.debug("Request to save Actionevent : {}", actioneventDTO);
        Actionevent actionevent = actioneventMapper.toEntity(actioneventDTO);
        actionevent = actioneventRepository.save(actionevent);
        return actioneventMapper.toDto(actionevent);
    }

    /**
     * Get all the actionevents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<ActioneventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actionevents");
        return actioneventRepository.findAll(pageable)
            .map(actioneventMapper::toDto);
    }


    /**
     * Get one actionevent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<ActioneventDTO> findOne(String id) {
        log.debug("Request to get Actionevent : {}", id);
        return actioneventRepository.findById(id)
            .map(actioneventMapper::toDto);
    }

    /**
     * Delete the actionevent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Actionevent : {}", id);
        actioneventRepository.deleteById(id);
    }
}
