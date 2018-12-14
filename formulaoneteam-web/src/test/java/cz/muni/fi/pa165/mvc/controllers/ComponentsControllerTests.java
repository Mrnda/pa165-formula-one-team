package cz.muni.fi.pa165.mvc.controllers;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComponentsControllerTests extends BaseControllerTest<ComponentsController> {
    @InjectMocks
    private ComponentsController controller;

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/components/list"))
                .andExpect(status().isOk());
    }

    @Override
    protected ComponentsController getController() {
        return controller;
    }
}
