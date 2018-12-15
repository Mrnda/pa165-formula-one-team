package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
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

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Adel Chakouri
 */
@WebAppConfiguration
@ContextConfiguration(classes = { RootWebContext.class})
public class RaceParticipationControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RaceParticipationFacade raceParticipationFacade;

    @InjectMocks
    private RaceParticipationController raceParticipationController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(raceParticipationController)
                .build();
    }

    @Test
    public void getValidRaceParticipationTest() throws Exception {
        //Given
        List<RaceParticipationDTO> raceParticipation = this.createRaceParticipation();
        doReturn(raceParticipation.get(0)).when(raceParticipationFacade).findById(1l);
        doReturn(raceParticipation.get(1)).when(raceParticipationFacade).findById(2l);

        //Then
        mockMvc.perform(get("/race-participation/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.resultPosition").value("4"));

        mockMvc.perform(get("/race-participation/2")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.resultPosition").value("5"));
    }

    @Test
    public void getInvalidRaceParticipationTest() throws Exception {
        //Given
        List<RaceParticipationDTO> raceParticipation = this.createRaceParticipation();
        when(raceParticipationFacade.findById(1)).thenThrow(EntityNotFoundException.class);

        //Then
        mockMvc.perform(get("/race-participation/1")).andExpect(status().isNotFound());
    }

    @Test
    public void getRaceParticipation() throws Exception {
        //Given
        doReturn(Collections.unmodifiableList(this.createRaceParticipation())).when(raceParticipationFacade).getAll();

        //Then
        mockMvc.perform(get("/race-participation/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].resultPosition").value(4))
                .andExpect(jsonPath("$.[?(@.id==2)].resultPosition").value(5));
    }

    @Test
    public void deleteRaceParticipation() throws Exception {
        //then
        mockMvc.perform(delete("/race-participation/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteRaceParticipationNonExisting() throws Exception {
        //Given
        doThrow(EntityNotFoundException.class).when(raceParticipationFacade).remove(1);

        //Then
        mockMvc.perform(delete("/race-participation/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addRaceParticipation_returnsOk() throws Exception {
        //Given
        List<RaceParticipationDTO> raceParticipation = createRaceParticipation();
        when(raceParticipationFacade.add(raceParticipation.get(0))).thenReturn(raceParticipation.get(0).getId());
        when(raceParticipationFacade.findById(raceParticipation.get(0).getId())).thenReturn(raceParticipation.get(0));
        String json = convertToString(raceParticipation.get(0));

        //Then
        mockMvc.perform(post("/race-participation/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToString(raceParticipation.get(0))));
    }

    @Test
    public void addRaceParticipation_returnsException() throws Exception {
        //Given
        RaceParticipationDTO raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setId(-1);
        when(raceParticipationFacade.add(raceParticipationDTO)).thenThrow(FormulaOneTeamException.class);
        String json = convertToString(raceParticipationDTO);

        //Then
        mockMvc.perform(post("/race-participation/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateRaceParticipation_returnsException() throws Exception {
        //Given
        RaceParticipationDTO raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setId(0);
        String json = convertToString(raceParticipationDTO);
        doThrow(FormulaOneTeamException.class).when(raceParticipationFacade).update(raceParticipationDTO, 0);

        //Then
        mockMvc.perform(put("/race-participation/0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    private List<RaceParticipationDTO> createRaceParticipation() {
        RaceParticipationDTO raceParticipationOne = new RaceParticipationDTO();
        raceParticipationOne.setId(1l);
        raceParticipationOne.setResultPosition(4);

        RaceParticipationDTO raceParticipationTwo = new RaceParticipationDTO();
        raceParticipationTwo.setId(2l);
        raceParticipationTwo.setResultPosition(5);

        return Arrays.asList(raceParticipationOne, raceParticipationTwo);
    }

    private String convertToString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
