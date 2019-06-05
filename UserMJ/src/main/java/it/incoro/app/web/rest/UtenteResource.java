package it.incoro.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.incoro.app.service.UtenteService;
import it.incoro.app.web.rest.errors.BadRequestAlertException;
import it.incoro.app.web.rest.util.HeaderUtil;
import it.incoro.app.web.rest.util.PaginationUtil;
import it.incoro.app.service.dto.UtenteDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Utente.
 */
@RestController
@RequestMapping("/api")
public class UtenteResource {

    private final Logger log = LoggerFactory.getLogger(UtenteResource.class);

    private static final String ENTITY_NAME = "utente";

    private final UtenteService utenteService;

    public UtenteResource(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    /**
     * POST  /utentes : Create a new utente.
     *
     * @param utenteDTO the utenteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new utenteDTO, or with status 400 (Bad Request) if the utente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/utentes")
    @Timed
    public ResponseEntity<UtenteDTO> createUtente(@Valid @RequestBody UtenteDTO utenteDTO) throws URISyntaxException {
        log.debug("REST request to save Utente : {}", utenteDTO);
        if (utenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new utente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UtenteDTO result = utenteService.save(utenteDTO);
        return ResponseEntity.created(new URI("/api/utentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /utentes : Updates an existing utente.
     *
     * @param utenteDTO the utenteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated utenteDTO,
     * or with status 400 (Bad Request) if the utenteDTO is not valid,
     * or with status 500 (Internal Server Error) if the utenteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/utentes")
    @Timed
    public ResponseEntity<UtenteDTO> updateUtente(@Valid @RequestBody UtenteDTO utenteDTO) throws URISyntaxException {
        log.debug("REST request to update Utente : {}", utenteDTO);
        if (utenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UtenteDTO result = utenteService.save(utenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, utenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /utentes : get all the utentes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of utentes in body
     */
    @GetMapping("/utentes")
    @Timed
    public ResponseEntity<List<UtenteDTO>> getAllUtentes(Pageable pageable) {
        log.debug("REST request to get a page of Utentes");
        Page<UtenteDTO> page = utenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/utentes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /utentes/:id : get the "id" utente.
     *
     * @param id the id of the utenteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the utenteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/utentes/{id}")
    @Timed
    public ResponseEntity<UtenteDTO> getUtente(@PathVariable String id) {
        log.debug("REST request to get Utente : {}", id);
        Optional<UtenteDTO> utenteDTO = utenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(utenteDTO);
    }

    /**
     * DELETE  /utentes/:id : delete the "id" utente.
     *
     * @param id the id of the utenteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/utentes/{id}")
    @Timed
    public ResponseEntity<Void> deleteUtente(@PathVariable String id) {
        log.debug("REST request to delete Utente : {}", id);
        utenteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
