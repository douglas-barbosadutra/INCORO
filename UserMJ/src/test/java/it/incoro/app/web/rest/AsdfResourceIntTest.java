package it.incoro.app.web.rest;

import it.incoro.app.UserMjApp;

import it.incoro.app.domain.Asdf;
import it.incoro.app.repository.AsdfRepository;
import it.incoro.app.service.AsdfService;
import it.incoro.app.service.dto.AsdfDTO;
import it.incoro.app.service.mapper.AsdfMapper;
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
 * Test class for the AsdfResource REST controller.
 *
 * @see AsdfResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserMjApp.class)
public class AsdfResourceIntTest {

    private static final String DEFAULT_ASDXC = "AAAAAAAAAA";
    private static final String UPDATED_ASDXC = "BBBBBBBBBB";

    @Autowired
    private AsdfRepository asdfRepository;


    @Autowired
    private AsdfMapper asdfMapper;
    

    @Autowired
    private AsdfService asdfService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAsdfMockMvc;

    private Asdf asdf;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AsdfResource asdfResource = new AsdfResource(asdfService);
        this.restAsdfMockMvc = MockMvcBuilders.standaloneSetup(asdfResource)
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
    public static Asdf createEntity() {
        Asdf asdf = new Asdf()
            .asdxc(DEFAULT_ASDXC);
        return asdf;
    }

    @Before
    public void initTest() {
        asdfRepository.deleteAll();
        asdf = createEntity();
    }

    @Test
    public void createAsdf() throws Exception {
        int databaseSizeBeforeCreate = asdfRepository.findAll().size();

        // Create the Asdf
        AsdfDTO asdfDTO = asdfMapper.toDto(asdf);
        restAsdfMockMvc.perform(post("/api/asdfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asdfDTO)))
            .andExpect(status().isCreated());

        // Validate the Asdf in the database
        List<Asdf> asdfList = asdfRepository.findAll();
        assertThat(asdfList).hasSize(databaseSizeBeforeCreate + 1);
        Asdf testAsdf = asdfList.get(asdfList.size() - 1);
        assertThat(testAsdf.getAsdxc()).isEqualTo(DEFAULT_ASDXC);
    }

    @Test
    public void createAsdfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = asdfRepository.findAll().size();

        // Create the Asdf with an existing ID
        asdf.setId("existing_id");
        AsdfDTO asdfDTO = asdfMapper.toDto(asdf);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAsdfMockMvc.perform(post("/api/asdfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asdfDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Asdf in the database
        List<Asdf> asdfList = asdfRepository.findAll();
        assertThat(asdfList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAsdfs() throws Exception {
        // Initialize the database
        asdfRepository.save(asdf);

        // Get all the asdfList
        restAsdfMockMvc.perform(get("/api/asdfs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asdf.getId())))
            .andExpect(jsonPath("$.[*].asdxc").value(hasItem(DEFAULT_ASDXC.toString())));
    }
    

    @Test
    public void getAsdf() throws Exception {
        // Initialize the database
        asdfRepository.save(asdf);

        // Get the asdf
        restAsdfMockMvc.perform(get("/api/asdfs/{id}", asdf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(asdf.getId()))
            .andExpect(jsonPath("$.asdxc").value(DEFAULT_ASDXC.toString()));
    }
    @Test
    public void getNonExistingAsdf() throws Exception {
        // Get the asdf
        restAsdfMockMvc.perform(get("/api/asdfs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAsdf() throws Exception {
        // Initialize the database
        asdfRepository.save(asdf);

        int databaseSizeBeforeUpdate = asdfRepository.findAll().size();

        // Update the asdf
        Asdf updatedAsdf = asdfRepository.findById(asdf.getId()).get();
        updatedAsdf
            .asdxc(UPDATED_ASDXC);
        AsdfDTO asdfDTO = asdfMapper.toDto(updatedAsdf);

        restAsdfMockMvc.perform(put("/api/asdfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asdfDTO)))
            .andExpect(status().isOk());

        // Validate the Asdf in the database
        List<Asdf> asdfList = asdfRepository.findAll();
        assertThat(asdfList).hasSize(databaseSizeBeforeUpdate);
        Asdf testAsdf = asdfList.get(asdfList.size() - 1);
        assertThat(testAsdf.getAsdxc()).isEqualTo(UPDATED_ASDXC);
    }

    @Test
    public void updateNonExistingAsdf() throws Exception {
        int databaseSizeBeforeUpdate = asdfRepository.findAll().size();

        // Create the Asdf
        AsdfDTO asdfDTO = asdfMapper.toDto(asdf);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restAsdfMockMvc.perform(put("/api/asdfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asdfDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Asdf in the database
        List<Asdf> asdfList = asdfRepository.findAll();
        assertThat(asdfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAsdf() throws Exception {
        // Initialize the database
        asdfRepository.save(asdf);

        int databaseSizeBeforeDelete = asdfRepository.findAll().size();

        // Get the asdf
        restAsdfMockMvc.perform(delete("/api/asdfs/{id}", asdf.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Asdf> asdfList = asdfRepository.findAll();
        assertThat(asdfList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Asdf.class);
        Asdf asdf1 = new Asdf();
        asdf1.setId("id1");
        Asdf asdf2 = new Asdf();
        asdf2.setId(asdf1.getId());
        assertThat(asdf1).isEqualTo(asdf2);
        asdf2.setId("id2");
        assertThat(asdf1).isNotEqualTo(asdf2);
        asdf1.setId(null);
        assertThat(asdf1).isNotEqualTo(asdf2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AsdfDTO.class);
        AsdfDTO asdfDTO1 = new AsdfDTO();
        asdfDTO1.setId("id1");
        AsdfDTO asdfDTO2 = new AsdfDTO();
        assertThat(asdfDTO1).isNotEqualTo(asdfDTO2);
        asdfDTO2.setId(asdfDTO1.getId());
        assertThat(asdfDTO1).isEqualTo(asdfDTO2);
        asdfDTO2.setId("id2");
        assertThat(asdfDTO1).isNotEqualTo(asdfDTO2);
        asdfDTO1.setId(null);
        assertThat(asdfDTO1).isNotEqualTo(asdfDTO2);
    }
}
