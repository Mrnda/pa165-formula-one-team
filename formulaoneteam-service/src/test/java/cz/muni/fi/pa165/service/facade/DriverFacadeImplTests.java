package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static cz.muni.fi.pa165.enums.CharacteristicsType.AGGRESSIVITY;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class DriverFacadeImplTests extends BaseFacadeTest<Driver, DriverDTO> {

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
        assertEquals(1, driverEntity.getCharacteristics().size());
    }

    @Test
    public void registerDriver_withAlreadyFilledCharacteristics_savesTheCharacteristicsValue() {
        //Given
        Driver driverEntity = createDriver();
        final CharacteristicsValueDTO characteristicsValueDto = createCharacteristicsValueDto(dto);
        final CharacteristicsValue characteristicsValueEntity = createCharacteristicsValue();
        dto.setCharacteristics(Collections.singletonList(characteristicsValueDto));
        when(beanMappingServiceMock.mapTo(dto, Driver.class)).thenReturn(driverEntity);
        when(beanMappingServiceMock.mapTo(characteristicsValueDto, CharacteristicsValue.class)).thenReturn(characteristicsValueEntity);

        //When
        driverFacade.register(dto, "password");

        //Then
        verify(driverServiceMock, times(1)).register(driverEntity, "password");
        verify(characteristicsValueServiceMock, times(1)).add(characteristicsValueEntity);
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
        when(beanMappingServiceMock.mapTo(entity, DriverDTO.class)).thenReturn(dto);
        when(driverServiceMock.findById(dto.getId())).thenReturn(entity);

        //When
        DriverDTO foundDriver = driverFacade.findById(dto.getId());

        //Then
        assertEquals(foundDriver, dto);
    }

    @Test
    public void findDriverByEmail_withExistingDriver_returnsItsDetail() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, DriverDTO.class)).thenReturn(dto);
        when(driverServiceMock.findByEmail(dto.getEmail())).thenReturn(entity);

        //When
        DriverDTO foundDriver = driverFacade.findByEmail(dto.getEmail());

        //Then
        assertEquals(foundDriver, dto);
    }

    @Test
    public void getAllDrivers_withExistingDrivers_returnsAllListItemDtos() {
        //Given
        DriverDTO johnDoe = createListItemDto("John", "john@doe.com", DriverStatus.TEST);
        DriverDTO janeDoe = createListItemDto("Jane", "jane@doe.com", DriverStatus.MAIN);
        Driver johnDoeEntity = createDriver("John", "john@doe.com", DriverStatus.TEST);
        Driver janeDoeEntity = createDriver("Jane", "jane@doe.com", DriverStatus.MAIN);
        List<Driver> entitiesCollection = Arrays.asList(johnDoeEntity, janeDoeEntity);
        List<DriverDTO> dtosCollection = Arrays.asList(johnDoe, janeDoe);
        when(beanMappingServiceMock.mapTo(entitiesCollection, DriverDTO.class)).thenReturn(dtosCollection);
        when(driverServiceMock.getAll()).thenReturn(entitiesCollection);

        //When
        List<DriverDTO> allDrivers = driverFacade.getAllDrivers();

        //Then
        assertEquals(allDrivers, dtosCollection);
    }

    @Test
    public void getAllDriversByStatus_withExistingDrivers_returnsAllListItemDtos() {
        //Given
        DriverDTO johnDoe = createListItemDto("John", "john@doe.com", DriverStatus.TEST);
        DriverDTO janeDoe = createListItemDto("Jane", "jane@doe.com", DriverStatus.TEST);
        Driver johnDoeEntity = createDriver("John", "john@doe.com", DriverStatus.TEST);
        Driver janeDoeEntity = createDriver("Jane", "jane@doe.com", DriverStatus.TEST);
        List<Driver> entitiesCollection = Arrays.asList(johnDoeEntity, janeDoeEntity);
        List<DriverDTO> dtosCollection = Arrays.asList(johnDoe, janeDoe);
        when(beanMappingServiceMock.mapTo(entitiesCollection, DriverDTO.class)).thenReturn(dtosCollection);
        when(driverServiceMock.getAllDriversByStatus(DriverStatus.TEST)).thenReturn(entitiesCollection);

        //When
        List<DriverDTO> allDrivers = driverFacade.getAllDriversByStatus(DriverStatus.TEST);

        //Then
        assertEquals(allDrivers, dtosCollection);
    }

    @Test
    public void findDriverWithHighestCharacteristicsValue_withExistingDriver_returnsItsDetail() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, DriverDTO.class)).thenReturn(dto);
        when(driverServiceMock.findDriverWithHighestCharacteristicsValue(AGGRESSIVITY)).thenReturn(entity);

        //When
        DriverDTO foundDriver = driverFacade.findDriverWithHighestCharacteristicsValue(AGGRESSIVITY);

        //Then
        assertEquals(foundDriver, dto);
    }

    @Test
    public void updateCharacteristicsValue_withValidDate_updatesCharacteristics() {
        //Given
        when(beanMappingServiceMock.mapTo(dto, Driver.class)).thenReturn(entity);
        when(driverServiceMock.findById(dto.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, DriverDTO.class)).thenReturn(dto);
        CharacteristicsValueDTO characteristicsValueDTO = createCharacteristicsValueDto(dto);
        CharacteristicsValue characteristicsValue = createCharacteristicsValue();
        entity.addCharacteristic(new CharacteristicsValue(AGGRESSIVITY, 50.0));
        when(beanMappingServiceMock.mapTo(characteristicsValueDTO, CharacteristicsValue.class)).thenReturn(characteristicsValue);

        //When
        DriverDTO updatedDriverDTO = driverFacade.updateDriversCharacteristicsValue(dto, characteristicsValueDTO);

        //Then
        assertEquals(dto, updatedDriverDTO);
        verify(characteristicsValueServiceMock, times(1)).update(characteristicsValue);
    }

    @Test
    public void createDefaultDriver_hasAllCharacteristicsValues() {
        //When
        final DriverDTO defaultDriver = driverFacade.createDefaultDriver();

        //Then
        for (CharacteristicsType type : CharacteristicsType.values()) {
            final long count = defaultDriver.getCharacteristics()
                    .stream()
                    .filter(characteristicsValueDTO -> characteristicsValueDTO.getType() == type)
                    .count();
            assertEquals(1, count);
        }
    }

    @Test
    public void updateDriver_withCharacteristicValues_updatesThem() {
        //Given
        final CharacteristicsValueDTO valueDto = createCharacteristicsValueDto(dto);
        final CharacteristicsValue valueEntity = createCharacteristicsValue();
        dto.setCharacteristics(Collections.singletonList(valueDto));
        dto.setId(1);
        when(beanMappingServiceMock.mapTo(dto, Driver.class)).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(valueDto, CharacteristicsValue.class))
                .thenReturn(valueEntity);

        //When
        driverFacade.updateDriver(dto);

        //Then
        entity.setId(1);
        verify(driverServiceMock).update(entity);
        verify(characteristicsValueServiceMock).update(valueEntity);
    }

    @Override
    protected Driver createTestEntity() {
        return createDriver();
    }

    @Override
    protected DriverDTO createTestDTO() {
        return createDriverDTO();
    }

    private CharacteristicsValueDTO createCharacteristicsValueDto(DriverDTO driverDetail) {
        CharacteristicsValueDTO characteristicsValueDTO = new CharacteristicsValueDTO();
        characteristicsValueDTO.setType(AGGRESSIVITY);
        characteristicsValueDTO.setValue(50.0);
        return characteristicsValueDTO;
    }

    private DriverDTO createListItemDto(String name, String email, DriverStatus status) {
        DriverDTO driverDto = new DriverDTO();
        driverDto.setName(name);
        driverDto.setSurname("Doe");
        driverDto.setEmail(email);
        driverDto.setNationality("American");
        driverDto.setBirthday(createDate(2, 9, 1989));
        driverDto.setDriverStatus(status);
        return driverDto;
    }

    private Driver createDriver(String name, String email, DriverStatus status) {
        return new Driver(name,
                "Doe",
                email,
                "American",
                createDate(2,10,1960),
                status
        );
    }
}
