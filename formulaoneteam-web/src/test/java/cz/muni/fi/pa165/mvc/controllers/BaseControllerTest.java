package cz.muni.fi.pa165.mvc.controllers;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public abstract class BaseControllerTest<Controller> {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(getController())
                .build();
    }

    protected abstract Controller getController();
}
