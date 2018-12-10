package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.RootWebContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest extends AbstractTestNGSpringContextTests {

    private final MockMvc mockMvc = standaloneSetup(new MainController())
            .build();

    @Test
    public void onGetRequest_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void onGetRequest_controllerElementsArePresent() throws Exception {
        //Then
        mockMvc.perform(get("/"))
                .andExpect(jsonPath("race_uri").value(ApiUris.ROOT_URI_RACE))
                .andExpect(jsonPath("test_drive_uri").value(ApiUris.ROOT_URI_TEST_DRIVE))
                .andExpect(jsonPath("race_participation_uri").value(ApiUris.ROOT_URI_RACE_PARTICIPATION));
    }

    @Test
    public void onGetRequest_responseTypeIsJson() throws Exception {
        //Then
        mockMvc.perform(get("/"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }
}
