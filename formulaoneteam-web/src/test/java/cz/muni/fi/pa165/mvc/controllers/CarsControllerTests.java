package cz.muni.fi.pa165.mvc.controllers;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarsControllerTests extends BaseControllerTest<CarsController> {

    @InjectMocks
    private CarsController controller;

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/cars/list"))
                .andExpect(status().isOk());
    }

    @Override
    protected CarsController getController() {
        return controller;
    }
}
