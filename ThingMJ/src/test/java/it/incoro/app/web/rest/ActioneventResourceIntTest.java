package it.incoro.app.web.rest;

import it.incoro.app.ThingMjApp;

import it.incoro.app.domain.Actionevent;
import it.incoro.app.repository.ActioneventRepository;
import it.incoro.app.service.ActioneventService;
import it.incoro.app.service.dto.ActioneventDTO;
import it.incoro.app.service.mapper.ActioneventMapper;
import it.incoro.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static it.incoro.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ActioneventResource REST controller.
 *
 * @see ActioneventResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThingMjApp.class)
public class ActioneventResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    @Autowired
    private ActioneventRepository actioneventRepository;


    @Autowired
    private ActioneventMapper actioneventMapper;
    

    @Autowired
    private ActioneventService actioneventService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restActioneventMockMvc;

    private Actionevent actionevent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActioneventResource actioneventResource = new ActioneventResource(actioneventService);
        this.restActioneventMockMvc = MockMvcBuilders.standaloneSetup(actioneventResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Actionevent createEntity() {
        Actionevent actionevent = new Actionevent()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE);
        return actionevent;
    }

    @Before
    public void initTest() {
        actioneventRepository.deleteAll();
        actionevent = createEntity();
    }

    @Test
    public void createActionevent() throws Exception {
        int databaseSizeBeforeCreate = actioneventRepository.findAll().size();

        // Create the Actionevent
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(actionevent);
        restActioneventMockMvc.perform(post("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isCreated());

        // Validate the Actionevent in the database
        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeCreate + 1);
        Actionevent testActionevent = actioneventList.get(actioneventList.size() - 1);
        assertThat(testActionevent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testActionevent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testActionevent.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    public void createActioneventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actioneventRepository.findAll().size();

        // Create the Actionevent with an existing ID
        actionevent.setId("existing_id");
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(actionevent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActioneventMockMvc.perform(post("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actionevent in the database
        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = actioneventRepository.findAll().size();
        // set the field null
        actionevent.setName(null);

        // Create the Actionevent, which fails.
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(actionevent);

        restActioneventMockMvc.perform(post("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isBadRequest());

        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = actioneventRepository.findAll().size();
        // set the field null
        actionevent.setDescription(null);

        // Create the Actionevent, which fails.
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(actionevent);

        restActioneventMockMvc.perform(post("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isBadRequest());

        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = actioneventRepository.findAll().size();
        // set the field null
        actionevent.setType(null);

        // Create the Actionevent, which fails.
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(actionevent);

        restActioneventMockMvc.perform(post("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isBadRequest());

        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllActionevents() throws Exception {
        // Initialize the database
        actioneventRepository.save(actionevent);

        // Get all the actioneventList
        restActioneventMockMvc.perform(get("/api/actionevents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actionevent.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    

    @Test
    public void getActionevent() throws Exception {
        // Initialize the database
        actioneventRepository.save(actionevent);

        // Get the actionevent
        restActioneventMockMvc.perform(get("/api/actionevents/{id}", actionevent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(actionevent.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }
    @Test
    public void getNonExistingActionevent() throws Exception {
        // Get the actionevent
        restActioneventMockMvc.perform(get("/api/actionevents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateActionevent() throws Exception {
        // Initialize the database
        actioneventRepository.save(actionevent);

        int databaseSizeBeforeUpdate = actioneventRepository.findAll().size();

        // Update the actionevent
        Actionevent updatedActionevent = actioneventRepository.findById(actionevent.getId()).get();
        updatedActionevent
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE);
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(updatedActionevent);

        restActioneventMockMvc.perform(put("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isOk());

        // Validate the Actionevent in the database
        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeUpdate);
        Actionevent testActionevent = actioneventList.get(actioneventList.size() - 1);
        assertThat(testActionevent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testActionevent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testActionevent.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    public void updateNonExistingActionevent() throws Exception {
        int databaseSizeBeforeUpdate = actioneventRepository.findAll().size();

        // Create the Actionevent
        ActioneventDTO actioneventDTO = actioneventMapper.toDto(actionevent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restActioneventMockMvc.perform(put("/api/actionevents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actioneventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actionevent in the database
        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteActionevent() throws Exception {
        // Initialize the database
        actioneventRepository.save(actionevent);

        int databaseSizeBeforeDelete = actioneventRepository.findAll().size();

        // Get the actionevent
        restActioneventMockMvc.perform(delete("/api/actionevents/{id}", actionevent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Actionevent> actioneventList = actioneventRepository.findAll();
        assertThat(actioneventList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Actionevent.class);
        Actionevent actionevent1 = new Actionevent();
        actionevent1.setId("id1");
        Actionevent actionevent2 = new Actionevent();
        actionevent2.setId(actionevent1.getId());
        assertThat(actionevent1).isEqualTo(actionevent2);
        actionevent2.setId("id2");
        assertThat(actionevent1).isNotEqualTo(actionevent2);
        actionevent1.setId(null);
        assertThat(actionevent1).isNotEqualTo(actionevent2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActioneventDTO.class);
        ActioneventDTO actioneventDTO1 = new ActioneventDTO();
        actioneventDTO1.setId("id1");
        ActioneventDTO actioneventDTO2 = new ActioneventDTO();
        assertThat(actioneventDTO1).isNotEqualTo(actioneventDTO2);
        actioneventDTO2.setId(actioneventDTO1.getId());
        assertThat(actioneventDTO1).isEqualTo(actioneventDTO2);
        actioneventDTO2.setId("id2");
        assertThat(actioneventDTO1).isNotEqualTo(actioneventDTO2);
        actioneventDTO1.setId(null);
        assertThat(actioneventDTO1).isNotEqualTo(actioneventDTO2);
    }
}
