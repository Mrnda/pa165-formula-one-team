package cz.muni.fi.pa165.mvc.controllers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class WorldChampionshipsControllerTests {
    @InjectMocks
    private WorldChampionshipsController controller;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(controller)
                .build();
    }

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/world-championships/list"))
                .andExpect(status().isOk());
    }
}


