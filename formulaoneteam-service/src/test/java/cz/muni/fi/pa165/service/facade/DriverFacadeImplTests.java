package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CharacteristicsValueService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;


@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriverFacadeImplTests extends AbstractTestNGSpringContextTests {

    @Mock
    private DriverService driverServiceMock;

    @Mock
    private CharacteristicsValueService characteristicsValueServiceMock;

    @Mock
    private BeanMappingService beanMappingServiceMock;

    @InjectMocks
    private DriverFacadeImpl driverFacade;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerDriver_withValidData_addsDefaultCharacteristics() {
        //Given
        DriverDetailDTO driverDetail = createDetailDto();
        Driver driverEntity = createDriver();
        when(beanMappingServiceMock.mapTo(driverDetail, Driver.class)).thenReturn(driverEntity);

        //When
        driverFacade.registerDriver(driverDetail, "password");

        //Then
        verify(driverServiceMock, times(1)).registerDriver(driverEntity, "password");
        assertEquals(5, driverEntity.getCharacteristics().size());
    }

    private DriverDetailDTO createDetailDto() {
        DriverDetailDTO driverDetailDTO = new DriverDetailDTO();
        driverDetailDTO.setName("John");
        driverDetailDTO.setSurname("Doe");
        driverDetailDTO.setEmail("John@doe.com");
        driverDetailDTO.setNationality("American");
        driverDetailDTO.setBirthday(createDate(2, 9, 1989));
        driverDetailDTO.setCharacteristics(new ArrayList<>());
        driverDetailDTO.setDriverStatus(DriverStatus.MAIN);
        return driverDetailDTO;
    }

    private Driver createDriver() {
        return new Driver("John",
                "Doe",
                "John@doe.com",
                "",
                "American",
                createDate(2, 9, 1989),
                DriverStatus.MAIN,
                new ArrayList<>());
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
