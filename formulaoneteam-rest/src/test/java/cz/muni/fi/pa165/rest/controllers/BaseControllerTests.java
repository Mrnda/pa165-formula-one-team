package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public abstract class BaseControllerTests<Controller> {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(getController())
                .build();
    }

    protected abstract Controller getController();

    protected String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
