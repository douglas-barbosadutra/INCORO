package it.incoro.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.incoro.app.service.AsdfService;
import it.incoro.app.web.rest.errors.BadRequestAlertException;
import it.incoro.app.web.rest.util.HeaderUtil;
import it.incoro.app.web.rest.util.PaginationUtil;
import it.incoro.app.service.dto.AsdfDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Asdf.
 */
@RestController
@RequestMapping("/api")
public class AsdfResource {

    private final Logger log = LoggerFactory.getLogger(AsdfResource.class);

    private static final String ENTITY_NAME = "asdf";

    private final AsdfService asdfService;

    public AsdfResource(AsdfService asdfService) {
        this.asdfService = asdfService;
    }

    /**
     * POST  /asdfs : Create a new asdf.
     *
     * @param asdfDTO the asdfDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new asdfDTO, or with status 400 (Bad Request) if the asdf has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asdfs")
    @Timed
    public ResponseEntity<AsdfDTO> createAsdf(@RequestBody AsdfDTO asdfDTO) throws URISyntaxException {
        log.debug("REST request to save Asdf : {}", asdfDTO);
        if (asdfDTO.getId() != null) {
            throw new BadRequestAlertException("A new asdf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AsdfDTO result = asdfService.save(asdfDTO);
        return ResponseEntity.created(new URI("/api/asdfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asdfs : Updates an existing asdf.
     *
     * @param asdfDTO the asdfDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated asdfDTO,
     * or with status 400 (Bad Request) if the asdfDTO is not valid,
     * or with status 500 (Internal Server Error) if the asdfDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asdfs")
    @Timed
    public ResponseEntity<AsdfDTO> updateAsdf(@RequestBody AsdfDTO asdfDTO) throws URISyntaxException {
        log.debug("REST request to update Asdf : {}", asdfDTO);
        if (asdfDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AsdfDTO result = asdfService.save(asdfDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, asdfDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asdfs : get all the asdfs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of asdfs in body
     */
    @GetMapping("/asdfs")
    @Timed
    public ResponseEntity<List<AsdfDTO>> getAllAsdfs(Pageable pageable) {
        log.debug("REST request to get a page of Asdfs");
        Page<AsdfDTO> page = asdfService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asdfs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /asdfs/:id : get the "id" asdf.
     *
     * @param id the id of the asdfDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the asdfDTO, or with status 404 (Not Found)
     */
    @GetMapping("/asdfs/{id}")
    @Timed
    public ResponseEntity<AsdfDTO> getAsdf(@PathVariable String id) {
        log.debug("REST request to get Asdf : {}", id);
        Optional<AsdfDTO> asdfDTO = asdfService.findOne(id);
        return ResponseUtil.wrapOrNotFound(asdfDTO);
    }

    /**
     * DELETE  /asdfs/:id : delete the "id" asdf.
     *
     * @param id the id of the asdfDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asdfs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAsdf(@PathVariable String id) {
        log.debug("REST request to delete Asdf : {}", id);
        asdfService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
