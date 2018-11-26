package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.dto.DriverListItemDTO;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.CharacteristicsValueService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

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

    @Test
    public void authenticate_withRegisteredDriver_returnsTrue() {
        //Given
        DriverDetailDTO driverDetail = createDetailDto();
        Driver driverEntity = createDriver();
        String password = "secret";
        when(beanMappingServiceMock.mapTo(driverDetail, Driver.class)).thenReturn(driverEntity);
        when(driverServiceMock.authenticate(driverEntity, password)).thenReturn(true);

        //When
        boolean result = driverFacade.authenticate(driverDetail, password);

        //Then
        assertTrue(result);
        verify(driverServiceMock, times(1)).authenticate(driverEntity, password);
    }

    @Test
    public void findDriverById_withExistingDriver_returnsItsDetail() {
        //Given
        DriverDetailDTO driverDetail = createDetailDto();
        Driver driverEntity = createDriver();
        when(beanMappingServiceMock.mapTo(driverEntity, DriverDetailDTO.class)).thenReturn(driverDetail);
        when(driverServiceMock.findDriverById(driverDetail.getId())).thenReturn(driverEntity);

        //When
        DriverDetailDTO foundDriver = driverFacade.findDriverById(driverDetail.getId());

        //Then
        assertEquals(foundDriver, driverDetail);
    }

    @Test
    public void findDriverByEmail_withExistingDriver_returnsItsDetail() {
        //Given
        DriverDetailDTO driverDetail = createDetailDto();
        Driver driverEntity = createDriver();
        when(beanMappingServiceMock.mapTo(driverEntity, DriverDetailDTO.class)).thenReturn(driverDetail);
        when(driverServiceMock.findDriverByEmail(driverDetail.getEmail())).thenReturn(driverEntity);

        //When
        DriverDetailDTO foundDriver = driverFacade.findDriverByEmail(driverDetail.getEmail());

        //Then
        assertEquals(foundDriver, driverDetail);
    }

    @Test
    public void getAllDrivers_withExistingDrivers_returnsAllListItemDtos() {
        //Given
        DriverListItemDTO johnDoe = createListItemDto("John", "Doe", "john@doe.com", DriverStatus.TEST);
        DriverListItemDTO janeDoe = createListItemDto("Jane", "Doe", "jane@doe.com", DriverStatus.MAIN);
        Driver johnDoeEntity = createDriver("John", "Doe", "john@doe.com", DriverStatus.TEST);
        Driver janeDoeEntity = createDriver("Jane", "Doe", "jane@doe.com", DriverStatus.MAIN);
        List<Driver> entitiesCollection = Arrays.asList(johnDoeEntity, janeDoeEntity);
        List<DriverListItemDTO> dtosCollection = Arrays.asList(johnDoe, janeDoe);
        when(beanMappingServiceMock.mapTo(entitiesCollection, DriverListItemDTO.class)).thenReturn(dtosCollection);
        when(driverServiceMock.getAllDrivers()).thenReturn(entitiesCollection);

        //When
        List<DriverListItemDTO> allDrivers = driverFacade.getAllDrivers();

        //Then
        assertEquals(allDrivers, dtosCollection);
    }

    @Test
    public void getAllDriversByStatus_withExistingDrivers_returnsAllListItemDtos() {
        //Given
        DriverListItemDTO johnDoe = createListItemDto("John", "Doe", "john@doe.com", DriverStatus.TEST);
        DriverListItemDTO janeDoe = createListItemDto("Jane", "Doe", "jane@doe.com", DriverStatus.TEST);
        Driver johnDoeEntity = createDriver("John", "Doe", "john@doe.com", DriverStatus.TEST);
        Driver janeDoeEntity = createDriver("Jane", "Doe", "jane@doe.com", DriverStatus.TEST);
        List<Driver> entitiesCollection = Arrays.asList(johnDoeEntity, janeDoeEntity);
        List<DriverListItemDTO> dtosCollection = Arrays.asList(johnDoe, janeDoe);
        when(beanMappingServiceMock.mapTo(entitiesCollection, DriverListItemDTO.class)).thenReturn(dtosCollection);
        when(driverServiceMock.getAllDriversByStatus(DriverStatus.TEST)).thenReturn(entitiesCollection);

        //When
        List<DriverListItemDTO> allDrivers = driverFacade.getAllDriversByStatus(DriverStatus.TEST);

        //Then
        assertEquals(allDrivers, dtosCollection);
    }

    @Test
    public void findDriverWithHighestCharacteristicsValue_withExistingDriver_returnsItsDetail() {
        //Given
        DriverDetailDTO driverDetail = createDetailDto();
        Driver driverEntity = createDriver();
        when(beanMappingServiceMock.mapTo(driverEntity, DriverDetailDTO.class)).thenReturn(driverDetail);
        when(driverServiceMock.findDriverWithHighestCharacteristicsValue(CharacteristicsType.AGGRESIVITY)).thenReturn(driverEntity);

        //When
        DriverDetailDTO foundDriver = driverFacade.findDriverWithHighestCharacteristicsValue(CharacteristicsType.AGGRESIVITY);

        //Then
        assertEquals(foundDriver, driverDetail);
    }

    @Test
    public void updateCharacteristicsValue_withValidDate_updatesCharacteristics() {
        //Given
        DriverDetailDTO driverDetail = createDetailDto();
        Driver driverEntity = createDriver();
        when(beanMappingServiceMock.mapTo(driverDetail, Driver.class)).thenReturn(driverEntity);
        when(driverServiceMock.findDriverById(driverDetail.getId())).thenReturn(driverEntity);
        when(beanMappingServiceMock.mapTo(driverEntity, DriverDetailDTO.class)).thenReturn(driverDetail);
        CharacteristicsValueDTO characteristicsValueDTO = createCharacteristicsValueDto(driverDetail);
        CharacteristicsValue characteristicsValue = createCharacteristicsValue(driverEntity);
        when(beanMappingServiceMock.mapTo(characteristicsValueDTO, CharacteristicsValue.class)).thenReturn(characteristicsValue);

        //When
        DriverDetailDTO updatedDriverDetailDTO = driverFacade.updateDriversCharacteristicsValue(characteristicsValueDTO);

        //Then
        assertEquals(driverDetail, updatedDriverDetailDTO);
        verify(characteristicsValueServiceMock, times(1)).update(characteristicsValue);
    }

    private CharacteristicsValue createCharacteristicsValue(Driver driverEntity) {
        return new CharacteristicsValue(CharacteristicsType.AGGRESIVITY, 50.0, driverEntity);
    }

    private CharacteristicsValueDTO createCharacteristicsValueDto(DriverDetailDTO driverDetail) {
        CharacteristicsValueDTO characteristicsValueDTO = new CharacteristicsValueDTO();
        characteristicsValueDTO.setDriver(driverDetail);
        characteristicsValueDTO.setType(CharacteristicsType.AGGRESIVITY);
        characteristicsValueDTO.setValue(50.0);
        return characteristicsValueDTO;
    }

    private DriverListItemDTO createListItemDto(String name, String surname, String email, DriverStatus status ) {
        DriverListItemDTO driverListItemDto = new DriverListItemDTO();
        driverListItemDto.setName(name);
        driverListItemDto.setSurname(surname);
        driverListItemDto.setEmail(email);
        driverListItemDto.setNationality("American");
        driverListItemDto.setBirthday(createDate(2, 9, 1989));
        driverListItemDto.setDriverStatus(status);
        return driverListItemDto;
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

    private Driver createDriver(String name, String surname, String email, DriverStatus status) {
        return new Driver(name,
                surname,
                email,
                "",
                "American",
                createDate(2,10,1960),
                status,
                new ArrayList<>());
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
