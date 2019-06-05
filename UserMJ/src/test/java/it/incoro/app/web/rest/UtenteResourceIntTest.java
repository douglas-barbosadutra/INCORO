package it.incoro.app.web.rest;

import it.incoro.app.UserMjApp;

import it.incoro.app.domain.Utente;
import it.incoro.app.repository.UtenteRepository;
import it.incoro.app.service.UtenteService;
import it.incoro.app.service.dto.UtenteDTO;
import it.incoro.app.service.mapper.UtenteMapper;
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
 * Test class for the UtenteResource REST controller.
 *
 * @see UtenteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserMjApp.class)
public class UtenteResourceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    @Autowired
    private UtenteRepository utenteRepository;


    @Autowired
    private UtenteMapper utenteMapper;
    

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restUtenteMockMvc;

    private Utente utente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UtenteResource utenteResource = new UtenteResource(utenteService);
        this.restUtenteMockMvc = MockMvcBuilders.standaloneSetup(utenteResource)
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
    public static Utente createEntity() {
        Utente utente = new Utente()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .type(DEFAULT_TYPE);
        return utente;
    }

    @Before
    public void initTest() {
        utenteRepository.deleteAll();
        utente = createEntity();
    }

    @Test
    public void createUtente() throws Exception {
        int databaseSizeBeforeCreate = utenteRepository.findAll().size();

        // Create the Utente
        UtenteDTO utenteDTO = utenteMapper.toDto(utente);
        restUtenteMockMvc.perform(post("/api/utentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Utente in the database
        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeCreate + 1);
        Utente testUtente = utenteList.get(utenteList.size() - 1);
        assertThat(testUtente.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testUtente.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUtente.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    public void createUtenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = utenteRepository.findAll().size();

        // Create the Utente with an existing ID
        utente.setId("existing_id");
        UtenteDTO utenteDTO = utenteMapper.toDto(utente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUtenteMockMvc.perform(post("/api/utentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utente in the database
        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = utenteRepository.findAll().size();
        // set the field null
        utente.setPassword(null);

        // Create the Utente, which fails.
        UtenteDTO utenteDTO = utenteMapper.toDto(utente);

        restUtenteMockMvc.perform(post("/api/utentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utenteDTO)))
            .andExpect(status().isBadRequest());

        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = utenteRepository.findAll().size();
        // set the field null
        utente.setType(null);

        // Create the Utente, which fails.
        UtenteDTO utenteDTO = utenteMapper.toDto(utente);

        restUtenteMockMvc.perform(post("/api/utentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utenteDTO)))
            .andExpect(status().isBadRequest());

        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUtentes() throws Exception {
        // Initialize the database
        utenteRepository.save(utente);

        // Get all the utenteList
        restUtenteMockMvc.perform(get("/api/utentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(utente.getId())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    

    @Test
    public void getUtente() throws Exception {
        // Initialize the database
        utenteRepository.save(utente);

        // Get the utente
        restUtenteMockMvc.perform(get("/api/utentes/{id}", utente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(utente.getId()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }
    @Test
    public void getNonExistingUtente() throws Exception {
        // Get the utente
        restUtenteMockMvc.perform(get("/api/utentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUtente() throws Exception {
        // Initialize the database
        utenteRepository.save(utente);

        int databaseSizeBeforeUpdate = utenteRepository.findAll().size();

        // Update the utente
        Utente updatedUtente = utenteRepository.findById(utente.getId()).get();
        updatedUtente
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .type(UPDATED_TYPE);
        UtenteDTO utenteDTO = utenteMapper.toDto(updatedUtente);

        restUtenteMockMvc.perform(put("/api/utentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utenteDTO)))
            .andExpect(status().isOk());

        // Validate the Utente in the database
        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeUpdate);
        Utente testUtente = utenteList.get(utenteList.size() - 1);
        assertThat(testUtente.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testUtente.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUtente.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    public void updateNonExistingUtente() throws Exception {
        int databaseSizeBeforeUpdate = utenteRepository.findAll().size();

        // Create the Utente
        UtenteDTO utenteDTO = utenteMapper.toDto(utente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restUtenteMockMvc.perform(put("/api/utentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(utenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Utente in the database
        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUtente() throws Exception {
        // Initialize the database
        utenteRepository.save(utente);

        int databaseSizeBeforeDelete = utenteRepository.findAll().size();

        // Get the utente
        restUtenteMockMvc.perform(delete("/api/utentes/{id}", utente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Utente> utenteList = utenteRepository.findAll();
        assertThat(utenteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Utente.class);
        Utente utente1 = new Utente();
        utente1.setId("id1");
        Utente utente2 = new Utente();
        utente2.setId(utente1.getId());
        assertThat(utente1).isEqualTo(utente2);
        utente2.setId("id2");
        assertThat(utente1).isNotEqualTo(utente2);
        utente1.setId(null);
        assertThat(utente1).isNotEqualTo(utente2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UtenteDTO.class);
        UtenteDTO utenteDTO1 = new UtenteDTO();
        utenteDTO1.setId("id1");
        UtenteDTO utenteDTO2 = new UtenteDTO();
        assertThat(utenteDTO1).isNotEqualTo(utenteDTO2);
        utenteDTO2.setId(utenteDTO1.getId());
        assertThat(utenteDTO1).isEqualTo(utenteDTO2);
        utenteDTO2.setId("id2");
        assertThat(utenteDTO1).isNotEqualTo(utenteDTO2);
        utenteDTO1.setId(null);
        assertThat(utenteDTO1).isNotEqualTo(utenteDTO2);
    }
}
