package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.config.MySpringMvcConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MySpringMvcConfig.class)
public class DriversControllerIntegrationTests extends BaseControllerTest<DriversController> {

    @Inject
    private DriversController controller;

    @Test
    public void getListOfDrivers() throws Exception {
        mockMvc.perform(get("/drivers/list"))
                .andExpect(status().isOk());
    }

    @Override
    protected DriversController getController() {
        return controller;
    }
}
