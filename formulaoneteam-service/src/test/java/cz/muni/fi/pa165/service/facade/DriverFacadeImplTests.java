package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.CharacteristicsValueService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class DriverFacadeImplTests extends BaseFacadeTest {

    @Mock
    private DriverService driverServiceMock;

    @Mock
    private CharacteristicsValueService characteristicsValueServiceMock;

    @InjectMocks
    private DriverFacadeImpl driverFacade;

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
}
