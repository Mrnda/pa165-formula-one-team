package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.rest.RootWebContext;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Adel Chakouri
 */

@WebAppConfiguration
@ContextConfiguration(classes = { RootWebContext.class})
public class RaceControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RaceFacade raceFacade;

    @InjectMocks
    private RaceController raceController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(raceController)
                .build();
    }

    @Test
    public void getValidRaceTest() throws Exception {
        //Given
        List<RaceDTO> race = this.createRace();
        doReturn(race.get(0)).when(raceFacade).findById(1l);
        doReturn(race.get(1)).when(raceFacade).findById(2l);

        //Then
        mockMvc.perform(get("/race/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.location").value("Location"));

        mockMvc.perform(get("/race/2")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.location").value("France"));
    }

    @Test
    public void getInvalidRaceTest() throws Exception {
        //when
        when(raceFacade.findById(1)).thenThrow(EntityNotFoundException.class);

        //Then
        mockMvc.perform(get("/race/1")).andExpect(status().isNotFound());
    }

    @Test
    public void getRaces() throws Exception {
        //Given
        doReturn(Collections.unmodifiableList(this.createRace())).when(raceFacade).getAll();

        //Then
        mockMvc.perform(get("/race/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].title").value("GP Monaco"))
                .andExpect(jsonPath("$.[?(@.id==2)].title").value("GP France"));
    }

    @Test
    public void deleteRace() throws Exception {
        //Then
        mockMvc.perform(delete("/race/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteRaceNonExisting() throws Exception {
        //Given
        doThrow(EntityNotFoundException.class).when(raceFacade).remove(1);

        //Then
        mockMvc.perform(delete("/race/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addRace_returnsOk() throws Exception {
        //Given
        List<RaceDTO> race = this.createRace();
        when(raceFacade.add(race.get(0))).thenReturn(race.get(0).getId());
        when(raceFacade.findById(race.get(0).getId())).thenReturn(race.get(0));
        String json = convertToString(race.get(0));

        //Then
        mockMvc.perform(post("/race/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToString(race.get(0))));
    }

    @Test
    public void addRace_returnsException() throws Exception {
        //Given
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId(-1);
        when(raceFacade.add(raceDTO)).thenThrow(FormulaOneTeamException.class);
        String json = convertToString(raceDTO);

        //Then
        mockMvc.perform(post("/race/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateRace_returnsException() throws Exception {
        //Given
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId(0);
        String json = convertToString(raceDTO);
        doThrow(FormulaOneTeamException.class).when(raceFacade).update(raceDTO, 0);

        //Then
        mockMvc.perform(put("/race/0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    private List<RaceDTO> createRace() {
        RaceDTO raceOne = new RaceDTO();
        raceOne.setId(1l);
        raceOne.setTitle("GP Monaco");
        raceOne.setLocation("Location");
        raceOne.setDate(new Date());

        RaceDTO raceTwo = new RaceDTO();
        raceTwo.setId(2l);
        raceTwo.setTitle("GP France");
        raceTwo.setLocation("France");
        raceTwo.setDate(new Date());

        return Arrays.asList(raceOne, raceTwo);
    }

    private String convertToString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
