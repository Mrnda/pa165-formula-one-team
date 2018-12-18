package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DriversControllerTests extends BaseControllerTest<DriversController> {

    @Mock
    private DriverFacade driverFacadeMock;

    @InjectMocks
    private DriversController controller;

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void list_whenDriversArePresent_attributeEquals() throws Exception {
        //When
        List<DriverDTO> drivers = Arrays.asList(createDriverDTO(), createDriverDTO());
        when(driverFacadeMock.getAllDrivers()).thenReturn(drivers);

        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(model().attributeExists("drivers"))
                .andExpect(model().attribute("drivers", drivers));
    }

    @Test
    public void detail_whenDriverExists_statusIsOk() throws Exception {
        //When
        when(driverFacadeMock.findById(1)).thenReturn(createDriverDTO());

        //Then
        mockMvc.perform(get("/drivers/detail/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void detail_whenDriverExists_addsModelAttribute() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);

        //Then
        mockMvc.perform(get("/drivers/detail/1"))
                .andExpect(model().attributeExists("driver"))
                .andExpect(model().attribute("driver", driverDTO));
    }

    @Test
    public void create_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/drivers/create"))
                .andExpect(status().isOk());
    }

    @Test
    public void create_addsDefaultDriverToTheModel() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.createDefaultDriver()).thenReturn(driverDTO);

        //Then
        mockMvc.perform(get("/drivers/create"))
                .andExpect(model().attributeExists("driver"))
                .andExpect(model().attribute("driver", driverDTO));
    }

    @Test
    public void edit_withExistingDriver_addsDriverAttribute() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);

        //Then
        mockMvc.perform(get("/drivers/edit/1"))
                .andExpect(model().attributeExists("driver"))
                .andExpect(model().attribute("driver", driverDTO));
    }

    @Test
    public void edit_withExistingDriver_statusIsOk() throws Exception {
        //When
        when(driverFacadeMock.findById(1)).thenReturn(createDriverDTO());

        //Then
        mockMvc.perform(get("/drivers/edit/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void submit_withValidExistingDriver_statusRedirect() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setId(1);
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submit_withValidExistingDriver_driverUpdated() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setId(1);
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO));
        verify(driverFacadeMock).updateDriver(driverDTO);
    }

    @Test
    public void submit_withValidDriver_statusRedirect() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submit_withValidDriver_driverRegistered() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO));
        verify(driverFacadeMock).register(driverDTO, driverDTO.getPassword());
    }

    @Test
    public void submit_withMissingNameDriver_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setName("");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "name"));
    }

    @Test
    public void submit_withNotSamePasswordDriver_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setConfirmPassword("");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "password", "confirmPassword"));
    }

    @Test
    public void submit_withInvalidBirthdayStringDriver_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setBirthdayString("1254");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "birthdayString"));
    }

    @Test
    public void submit_withNewDriverAndMissingPassword_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setPassword("");
        driverDTO.setConfirmPassword("");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "password"));
    }

    private DriverDTO createDriverDTO() {
        DriverDTO driverDto = new DriverDTO();
        driverDto.setName("John");
        driverDto.setSurname("Doe");
        driverDto.setEmail("john@doe.com");
        driverDto.setNationality("American");
        driverDto.setBirthdayString("02/09/1989");
        driverDto.setBirthday(createDate(2, 9, 1989));
        driverDto.setPassword("password");
        driverDto.setConfirmPassword("password");
        driverDto.setDriverStatus(DriverStatus.MAIN);
        return driverDto;
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    protected DriversController getController() {
        return controller;
    }
}
