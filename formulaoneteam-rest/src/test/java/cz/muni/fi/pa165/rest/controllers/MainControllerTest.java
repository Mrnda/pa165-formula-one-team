package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */

public class MainControllerTest extends BaseControllerTests<MainController> {

    @InjectMocks
    MainController controller;

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

    @Override
    protected MainController getController() {
        return controller;
    }
}
