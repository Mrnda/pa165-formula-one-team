package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.DriverListItemDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class DriversControllerTests {

    @Mock
    private DriverFacade driverFacadeMock;

    @InjectMocks
    private DriversController controller;

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
        mockMvc.perform(get("/drivers/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void list_whenDriversArePresent_attributeEquals() throws Exception {
        //When
        List<DriverListItemDTO> drivers = Arrays.asList(createListItemDto(), createListItemDto());
        when(driverFacadeMock.getAllDrivers()).thenReturn(drivers);

        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(model().attributeExists("drivers"))
                .andExpect(model().attribute("drivers", drivers));
    }

    private DriverListItemDTO createListItemDto() {
        DriverListItemDTO driverListItemDto = new DriverListItemDTO();
        driverListItemDto.setName("John");
        driverListItemDto.setSurname("Doe");
        driverListItemDto.setEmail("john@doe.com");
        driverListItemDto.setNationality("American");
        driverListItemDto.setBirthday(createDate(2, 9, 1989));
        driverListItemDto.setDriverStatus(DriverStatus.MAIN);
        return driverListItemDto;
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
