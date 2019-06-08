package it.incoro.app.service.impl;

import it.incoro.app.service.UtenteService;
import it.incoro.app.domain.Utente;
import it.incoro.app.repository.UtenteRepository;
import it.incoro.app.service.dto.UtenteDTO;
import it.incoro.app.service.mapper.UtenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
/**
 * Service Implementation for managing Utente.
 */
@Service
public class UtenteServiceImpl implements UtenteService {

    private final Logger log = LoggerFactory.getLogger(UtenteServiceImpl.class);

    private final UtenteRepository utenteRepository;

    private final UtenteMapper utenteMapper;

    public UtenteServiceImpl(UtenteRepository utenteRepository, UtenteMapper utenteMapper) {
        this.utenteRepository = utenteRepository;
        this.utenteMapper = utenteMapper;
    }

    /**
     * Save a utente.
     *
     * @param utenteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UtenteDTO save(UtenteDTO utenteDTO) {
        log.debug("Request to save Utente : {}", utenteDTO);
        Utente utente = utenteMapper.toEntity(utenteDTO);
        utente = utenteRepository.save(utente);
        return utenteMapper.toDto(utente);
    }

    /**
     * Get all the utentes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<UtenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Utentes");
        return utenteRepository.findAll(pageable)
            .map(utenteMapper::toDto);
    }


    /**
     * Get one utente by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<UtenteDTO> findOne(String id) {
        log.debug("Request to get Utente : {}", id);
        return utenteRepository.findById(id)
            .map(utenteMapper::toDto);
    }
	
	@Override
    public Optional<UtenteDTO> findUsernameAndPassword(String username, String password) {
        log.debug("Request to get Utente : {}", username);
        return utenteRepository.findByUsernameAndPassword(username, password)
            .map(utenteMapper::toDto);
    }

    /**
     * Delete the utente by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Utente : {}", id);
        utenteRepository.deleteById(id);
    }
}
