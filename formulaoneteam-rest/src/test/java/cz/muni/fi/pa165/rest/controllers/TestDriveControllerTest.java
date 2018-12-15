package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.TestDriveFacade;
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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Adel Chakouri
 */

@WebAppConfiguration
@ContextConfiguration(classes = { RootWebContext.class})
public class TestDriveControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TestDriveFacade testDriveFacade;

    @InjectMocks
    private TestDriveController testDriveController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(testDriveController)
                .build();
    }

    @Test
    public void getValidTestDriveTest() throws Exception {
        //Given
        List<TestDriveDTO> raceParticipation = this.createTestDrive();
        doReturn(raceParticipation.get(0)).when(testDriveFacade).findById(1l);
        doReturn(raceParticipation.get(1)).when(testDriveFacade).findById(2l);

        //Then
        mockMvc.perform(get("/test-drive/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.notes").value("notes1"));

        mockMvc.perform(get("/test-drive/2")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.notes").value("notes2"));
    }

    @Test
    public void getInvalidTestDriveTest() throws Exception {
        //Given
        List<TestDriveDTO> testDrive = this.createTestDrive();
        when(testDriveFacade.findById(1)).thenThrow(EntityNotFoundException.class);

        //Then
        mockMvc.perform(get("/test-drive/1")).andExpect(status().isNotFound());
    }

    @Test
    public void getTestDrive() throws Exception {
        //Given
        doReturn(Collections.unmodifiableList(this.createTestDrive())).when(testDriveFacade).getAll();

        //Then
        mockMvc.perform(get("/test-drive/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].notes").value("notes1"))
                .andExpect(jsonPath("$.[?(@.id==2)].notes").value("notes2"));
    }

    @Test
    public void deleteTestDrive() throws Exception {

        //Then
        mockMvc.perform(delete("/test-drive/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteTestDriveNonExisting() throws Exception {
        //given
        doThrow(EntityNotFoundException.class).when(testDriveFacade).remove(1);

        //then
        mockMvc.perform(delete("/test-drive/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addTestDrive_returnsOk() throws Exception {
        //Given
        List<TestDriveDTO> testDrive = this.createTestDrive();
        when(testDriveFacade.add(testDrive.get(0))).thenReturn(testDrive.get(0).getId());
        when(testDriveFacade.findById(testDrive.get(0).getId())).thenReturn(testDrive.get(0));
        String json = convertToString(testDrive.get(0));

        //Then
        mockMvc.perform(post("/test-drive/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToString(testDrive.get(0))));
    }

    @Test
    public void addTestDrive_returnsException() throws Exception {
        //Given
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setId(-1);
        when(testDriveFacade.add(testDriveDTO)).thenThrow(FormulaOneTeamException.class);
        String json = convertToString(testDriveDTO);

        //Then
        mockMvc.perform(post("/test-drive/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateTestDrive_returnsException() throws Exception {
        //Given
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setId(0);
        String json = convertToString(testDriveDTO);
        doThrow(FormulaOneTeamException.class).when(testDriveFacade).update(testDriveDTO, 0);

        //Then
        mockMvc.perform(put("/test-drive/0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    private List<TestDriveDTO> createTestDrive() {
        TestDriveDTO testDriveOne = new TestDriveDTO();
        testDriveOne.setId(1l);
        testDriveOne.setNotes("notes1");

        TestDriveDTO testDriveTwo = new TestDriveDTO();
        testDriveTwo.setId(2l);
        testDriveTwo.setNotes("notes2");

        return Arrays.asList(testDriveOne, testDriveTwo);
    }

    private String convertToString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
