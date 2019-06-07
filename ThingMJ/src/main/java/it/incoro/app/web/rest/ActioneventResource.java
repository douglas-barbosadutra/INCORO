package it.incoro.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.incoro.app.service.ActioneventService;
import it.incoro.app.web.rest.errors.BadRequestAlertException;
import it.incoro.app.web.rest.util.HeaderUtil;
import it.incoro.app.web.rest.util.PaginationUtil;
import it.incoro.app.service.dto.ActioneventDTO;
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
 * REST controller for managing Actionevent.
 */
@RestController
@RequestMapping("/api")
public class ActioneventResource {

    private final Logger log = LoggerFactory.getLogger(ActioneventResource.class);

    private static final String ENTITY_NAME = "actionevent";

    private final ActioneventService actioneventService;

    public ActioneventResource(ActioneventService actioneventService) {
        this.actioneventService = actioneventService;
    }

    /**
     * POST  /actionevents : Create a new actionevent.
     *
     * @param actioneventDTO the actioneventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new actioneventDTO, or with status 400 (Bad Request) if the actionevent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/actionevents")
    @Timed
    public ResponseEntity<ActioneventDTO> createActionevent(@Valid @RequestBody ActioneventDTO actioneventDTO) throws URISyntaxException {
        log.debug("REST request to save Actionevent : {}", actioneventDTO);
        if (actioneventDTO.getId() != null) {
            throw new BadRequestAlertException("A new actionevent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActioneventDTO result = actioneventService.save(actioneventDTO);
        return ResponseEntity.created(new URI("/api/actionevents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /actionevents : Updates an existing actionevent.
     *
     * @param actioneventDTO the actioneventDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated actioneventDTO,
     * or with status 400 (Bad Request) if the actioneventDTO is not valid,
     * or with status 500 (Internal Server Error) if the actioneventDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/actionevents")
    @Timed
    public ResponseEntity<ActioneventDTO> updateActionevent(@Valid @RequestBody ActioneventDTO actioneventDTO) throws URISyntaxException {
        log.debug("REST request to update Actionevent : {}", actioneventDTO);
        if (actioneventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActioneventDTO result = actioneventService.save(actioneventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, actioneventDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /actionevents : get all the actionevents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of actionevents in body
     */
    @GetMapping("/actionevents")
    @Timed
    public ResponseEntity<List<ActioneventDTO>> getAllActionevents(Pageable pageable) {
        log.debug("REST request to get a page of Actionevents");
        Page<ActioneventDTO> page = actioneventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/actionevents");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /actionevents/:id : get the "id" actionevent.
     *
     * @param id the id of the actioneventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the actioneventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/actionevents/{id}")
    @Timed
    public ResponseEntity<ActioneventDTO> getActionevent(@PathVariable String id) {
        log.debug("REST request to get Actionevent : {}", id);
        Optional<ActioneventDTO> actioneventDTO = actioneventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actioneventDTO);
    }

    /**
     * DELETE  /actionevents/:id : delete the "id" actionevent.
     *
     * @param id the id of the actioneventDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/actionevents/{id}")
    @Timed
    public ResponseEntity<Void> deleteActionevent(@PathVariable String id) {
        log.debug("REST request to delete Actionevent : {}", id);
        actioneventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
