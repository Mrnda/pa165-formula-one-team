package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.dto.DriverListItemDTO;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.CharacteristicsValueService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static cz.muni.fi.pa165.enums.CharacteristicsType.AGGRESIVITY;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class DriverFacadeImplTests extends BaseFacadeTest<Driver, DriverDetailDTO> {

    @Mock
    private DriverService driverServiceMock;

    @Mock
    private CharacteristicsValueService characteristicsValueServiceMock;

    @InjectMocks
    private DriverFacadeImpl driverFacade;

    @Test
    public void registerDriver_withValidData_addsDefaultCharacteristics() {
        //Given
        Driver driverEntity = createDriver();
        when(beanMappingServiceMock.mapTo(dto, Driver.class)).thenReturn(driverEntity);

        //When
        driverFacade.register(dto, "password");

        //Then
        verify(driverServiceMock, times(1)).register(driverEntity, "password");
        assertEquals(5, driverEntity.getCharacteristics().size());
    }

    @Test
    public void authenticate_withRegisteredDriver_returnsTrue() {
        //Given
        String password = "secret";
        when(beanMappingServiceMock.mapTo(dto, Driver.class)).thenReturn(entity);
        when(driverServiceMock.authenticate(dto.getEmail(), password)).thenReturn(true);

        //When
        boolean result = driverFacade.authenticate(dto.getEmail(), password);

        //Then
        assertTrue(result);
        verify(driverServiceMock, times(1)).authenticate(dto.getEmail(), password);
    }

    @Test
    public void findDriverById_withExistingDriver_returnsItsDetail() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, DriverDetailDTO.class)).thenReturn(dto);
        when(driverServiceMock.findById(dto.getId())).thenReturn(entity);

        //When
        DriverDetailDTO foundDriver = driverFacade.findById(dto.getId());

        //Then
        assertEquals(foundDriver, dto);
    }

    @Test
    public void findDriverByEmail_withExistingDriver_returnsItsDetail() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, DriverDetailDTO.class)).thenReturn(dto);
        when(driverServiceMock.findByEmail(dto.getEmail())).thenReturn(entity);

        //When
        DriverDetailDTO foundDriver = driverFacade.findByEmail(dto.getEmail());

        //Then
        assertEquals(foundDriver, dto);
    }

    @Test
    public void getAllDrivers_withExistingDrivers_returnsAllListItemDtos() {
        //Given
        DriverListItemDTO johnDoe = createListItemDto("John", "john@doe.com", DriverStatus.TEST);
        DriverListItemDTO janeDoe = createListItemDto("Jane", "jane@doe.com", DriverStatus.MAIN);
        Driver johnDoeEntity = createDriver("John", "john@doe.com", DriverStatus.TEST);
        Driver janeDoeEntity = createDriver("Jane", "jane@doe.com", DriverStatus.MAIN);
        List<Driver> entitiesCollection = Arrays.asList(johnDoeEntity, janeDoeEntity);
        List<DriverListItemDTO> dtosCollection = Arrays.asList(johnDoe, janeDoe);
        when(beanMappingServiceMock.mapTo(entitiesCollection, DriverListItemDTO.class)).thenReturn(dtosCollection);
        when(driverServiceMock.getAll()).thenReturn(entitiesCollection);

        //When
        List<DriverListItemDTO> allDrivers = driverFacade.getAllDrivers();

        //Then
        assertEquals(allDrivers, dtosCollection);
    }

    @Test
    public void getAllDriversByStatus_withExistingDrivers_returnsAllListItemDtos() {
        //Given
        DriverListItemDTO johnDoe = createListItemDto("John", "john@doe.com", DriverStatus.TEST);
        DriverListItemDTO janeDoe = createListItemDto("Jane", "jane@doe.com", DriverStatus.TEST);
        Driver johnDoeEntity = createDriver("John", "john@doe.com", DriverStatus.TEST);
        Driver janeDoeEntity = createDriver("Jane", "jane@doe.com", DriverStatus.TEST);
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
        when(beanMappingServiceMock.mapTo(entity, DriverDetailDTO.class)).thenReturn(dto);
        when(driverServiceMock.findDriverWithHighestCharacteristicsValue(AGGRESIVITY)).thenReturn(entity);

        //When
        DriverDetailDTO foundDriver = driverFacade.findDriverWithHighestCharacteristicsValue(AGGRESIVITY);

        //Then
        assertEquals(foundDriver, dto);
    }

    @Test
    public void updateCharacteristicsValue_withValidDate_updatesCharacteristics() {
        //Given
        when(beanMappingServiceMock.mapTo(dto, Driver.class)).thenReturn(entity);
        when(driverServiceMock.findById(dto.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, DriverDetailDTO.class)).thenReturn(dto);
        CharacteristicsValueDTO characteristicsValueDTO = createCharacteristicsValueDto(dto);
        CharacteristicsValue characteristicsValue = createCharacteristicsValue(entity);
        when(beanMappingServiceMock.mapTo(characteristicsValueDTO, CharacteristicsValue.class)).thenReturn(characteristicsValue);

        //When
        DriverDetailDTO updatedDriverDetailDTO = driverFacade.updateDriversCharacteristicsValue(characteristicsValueDTO);

        //Then
        assertEquals(dto, updatedDriverDetailDTO);
        verify(characteristicsValueServiceMock, times(1)).update(characteristicsValue);
    }

    @Override
    protected Driver createTestEntity() {
        return createDriver();
    }

    @Override
    protected DriverDetailDTO createTestDTO() {
        return createDriverDetailDTO();
    }

    private CharacteristicsValue createCharacteristicsValue(Driver driverEntity) {
        return new CharacteristicsValue(AGGRESIVITY, 50.0, driverEntity);
    }

    private CharacteristicsValueDTO createCharacteristicsValueDto(DriverDetailDTO driverDetail) {
        CharacteristicsValueDTO characteristicsValueDTO = new CharacteristicsValueDTO();
        characteristicsValueDTO.setDriver(driverDetail);
        characteristicsValueDTO.setType(AGGRESIVITY);
        characteristicsValueDTO.setValue(50.0);
        return characteristicsValueDTO;
    }

    private DriverListItemDTO createListItemDto(String name, String email, DriverStatus status) {
        DriverListItemDTO driverListItemDto = new DriverListItemDTO();
        driverListItemDto.setName(name);
        driverListItemDto.setSurname("Doe");
        driverListItemDto.setEmail(email);
        driverListItemDto.setNationality("American");
        driverListItemDto.setBirthday(createDate(2, 9, 1989));
        driverListItemDto.setDriverStatus(status);
        return driverListItemDto;
    }

    private Driver createDriver(String name, String email, DriverStatus status) {
        return new Driver(name,
                "Doe",
                email,
                "",
                "American",
                createDate(2,10,1960),
                status
        );
    }
}
