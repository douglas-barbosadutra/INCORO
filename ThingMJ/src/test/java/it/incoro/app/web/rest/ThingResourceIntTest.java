package it.incoro.app.web.rest;

import it.incoro.app.ThingMjApp;

import it.incoro.app.domain.Thing;
import it.incoro.app.repository.ThingRepository;
import it.incoro.app.service.ThingService;
import it.incoro.app.service.dto.ThingDTO;
import it.incoro.app.service.mapper.ThingMapper;
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
 * Test class for the ThingResource REST controller.
 *
 * @see ThingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThingMjApp.class)
public class ThingResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ThingRepository thingRepository;


    @Autowired
    private ThingMapper thingMapper;
    

    @Autowired
    private ThingService thingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restThingMockMvc;

    private Thing thing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThingResource thingResource = new ThingResource(thingService);
        this.restThingMockMvc = MockMvcBuilders.standaloneSetup(thingResource)
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
    public static Thing createEntity() {
        Thing thing = new Thing()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return thing;
    }

    @Before
    public void initTest() {
        thingRepository.deleteAll();
        thing = createEntity();
    }

    @Test
    public void createThing() throws Exception {
        int databaseSizeBeforeCreate = thingRepository.findAll().size();

        // Create the Thing
        ThingDTO thingDTO = thingMapper.toDto(thing);
        restThingMockMvc.perform(post("/api/things")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thingDTO)))
            .andExpect(status().isCreated());

        // Validate the Thing in the database
        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeCreate + 1);
        Thing testThing = thingList.get(thingList.size() - 1);
        assertThat(testThing.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testThing.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createThingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thingRepository.findAll().size();

        // Create the Thing with an existing ID
        thing.setId("existing_id");
        ThingDTO thingDTO = thingMapper.toDto(thing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThingMockMvc.perform(post("/api/things")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Thing in the database
        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = thingRepository.findAll().size();
        // set the field null
        thing.setName(null);

        // Create the Thing, which fails.
        ThingDTO thingDTO = thingMapper.toDto(thing);

        restThingMockMvc.perform(post("/api/things")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thingDTO)))
            .andExpect(status().isBadRequest());

        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = thingRepository.findAll().size();
        // set the field null
        thing.setDescription(null);

        // Create the Thing, which fails.
        ThingDTO thingDTO = thingMapper.toDto(thing);

        restThingMockMvc.perform(post("/api/things")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thingDTO)))
            .andExpect(status().isBadRequest());

        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllThings() throws Exception {
        // Initialize the database
        thingRepository.save(thing);

        // Get all the thingList
        restThingMockMvc.perform(get("/api/things?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thing.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    

    @Test
    public void getThing() throws Exception {
        // Initialize the database
        thingRepository.save(thing);

        // Get the thing
        restThingMockMvc.perform(get("/api/things/{id}", thing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thing.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    public void getNonExistingThing() throws Exception {
        // Get the thing
        restThingMockMvc.perform(get("/api/things/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateThing() throws Exception {
        // Initialize the database
        thingRepository.save(thing);

        int databaseSizeBeforeUpdate = thingRepository.findAll().size();

        // Update the thing
        Thing updatedThing = thingRepository.findById(thing.getId()).get();
        updatedThing
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        ThingDTO thingDTO = thingMapper.toDto(updatedThing);

        restThingMockMvc.perform(put("/api/things")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thingDTO)))
            .andExpect(status().isOk());

        // Validate the Thing in the database
        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeUpdate);
        Thing testThing = thingList.get(thingList.size() - 1);
        assertThat(testThing.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testThing.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingThing() throws Exception {
        int databaseSizeBeforeUpdate = thingRepository.findAll().size();

        // Create the Thing
        ThingDTO thingDTO = thingMapper.toDto(thing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restThingMockMvc.perform(put("/api/things")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Thing in the database
        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteThing() throws Exception {
        // Initialize the database
        thingRepository.save(thing);

        int databaseSizeBeforeDelete = thingRepository.findAll().size();

        // Get the thing
        restThingMockMvc.perform(delete("/api/things/{id}", thing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Thing> thingList = thingRepository.findAll();
        assertThat(thingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Thing.class);
        Thing thing1 = new Thing();
        thing1.setId("id1");
        Thing thing2 = new Thing();
        thing2.setId(thing1.getId());
        assertThat(thing1).isEqualTo(thing2);
        thing2.setId("id2");
        assertThat(thing1).isNotEqualTo(thing2);
        thing1.setId(null);
        assertThat(thing1).isNotEqualTo(thing2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThingDTO.class);
        ThingDTO thingDTO1 = new ThingDTO();
        thingDTO1.setId("id1");
        ThingDTO thingDTO2 = new ThingDTO();
        assertThat(thingDTO1).isNotEqualTo(thingDTO2);
        thingDTO2.setId(thingDTO1.getId());
        assertThat(thingDTO1).isEqualTo(thingDTO2);
        thingDTO2.setId("id2");
        assertThat(thingDTO1).isNotEqualTo(thingDTO2);
        thingDTO1.setId(null);
        assertThat(thingDTO1).isNotEqualTo(thingDTO2);
    }
}
