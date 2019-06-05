package it.incoro.app.service.impl;

import it.incoro.app.service.AsdfService;
import it.incoro.app.domain.Asdf;
import it.incoro.app.repository.AsdfRepository;
import it.incoro.app.service.dto.AsdfDTO;
import it.incoro.app.service.mapper.AsdfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
/**
 * Service Implementation for managing Asdf.
 */
@Service
public class AsdfServiceImpl implements AsdfService {

    private final Logger log = LoggerFactory.getLogger(AsdfServiceImpl.class);

    private final AsdfRepository asdfRepository;

    private final AsdfMapper asdfMapper;

    public AsdfServiceImpl(AsdfRepository asdfRepository, AsdfMapper asdfMapper) {
        this.asdfRepository = asdfRepository;
        this.asdfMapper = asdfMapper;
    }

    /**
     * Save a asdf.
     *
     * @param asdfDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AsdfDTO save(AsdfDTO asdfDTO) {
        log.debug("Request to save Asdf : {}", asdfDTO);
        Asdf asdf = asdfMapper.toEntity(asdfDTO);
        asdf = asdfRepository.save(asdf);
        return asdfMapper.toDto(asdf);
    }

    /**
     * Get all the asdfs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<AsdfDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Asdfs");
        return asdfRepository.findAll(pageable)
            .map(asdfMapper::toDto);
    }


    /**
     * Get one asdf by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<AsdfDTO> findOne(String id) {
        log.debug("Request to get Asdf : {}", id);
        return asdfRepository.findById(id)
            .map(asdfMapper::toDto);
    }

    /**
     * Delete the asdf by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Asdf : {}", id);
        asdfRepository.deleteById(id);
    }
}
