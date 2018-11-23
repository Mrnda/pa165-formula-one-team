package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.DriverServiceImpl;
import cz.muni.fi.pa165.service.base.BaseTest;
import cz.muni.fi.pa165.service.utils.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class DriverServiceImplTests extends BaseTest {

    @Mock
    private DriverDao driverDaoMock;

    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver testingDriver;

    @Before
    public void setUp() {
        testingDriver = createTestingDriver();
    }

    @Test
    public void registerDriver_withValidValues_driverRegistered() {
        //Given
        String password = "password";

        //When
        driverService.registerDriver(testingDriver, password);

        //Then
        verify(driverDaoMock, times(1)).add(testingDriver);
        assertTrue(Validator.validatePassword(password, testingDriver.getPasswordHash()));
    }

    @Test
    public void registeredDriver_withValidPassword_isAuthenticated() {
        //Given
        String password = "password";
        testingDriver.setPasswordHash(Validator.createHash(password));
        when(driverDaoMock.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        boolean result = driverService.authenticate(testingDriver, password);

        //Then
        assertTrue(result);
    }

    @Test
    public void registeredDriver_withInvalidPassword_isAuthenticated() {
        //Given
        String validPassword = "password";
        testingDriver.setPasswordHash(Validator.createHash(validPassword));
        String invalidPassword = "invalid_password";
        when(driverDaoMock.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        boolean result = driverService.authenticate(testingDriver, invalidPassword);

        //Then
        assertFalse(result);
    }

    @Test
    public void registeredDriver_canBeFoundById() {
        //Given
        when(driverDaoMock.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        final Driver foundDriver = driverService.findDriverById(testingDriver.getId());

        //Then
        assertEquals(testingDriver, foundDriver);
    }

    @Test
    public void registeredDriver_canBeFoundByEmail() {
        //Given
        when(driverDaoMock.findByEmail(testingDriver.getEmail())).thenReturn(testingDriver);

        //When
        final Driver foundDriver = driverService.findDriverByEmail(testingDriver.getEmail());

        //Then
        assertEquals(testingDriver, foundDriver);
    }

    @Test
    public void findAllDrivers_returnsAllDrivers() {
        //Given
        List<Driver> allDrivers = Stream.of(createTestingDriverWithStatus(DriverStatus.TEST),
                createTestingDriverWithStatus(DriverStatus.MAIN),
                createTestingDriverWithStatus(DriverStatus.TEST)).collect(Collectors.toList());
        when(driverDaoMock.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allFoundDrivers = driverService.getAllDrivers();

        //Then
        assertEquals(3, allFoundDrivers.size());
        assertEquals(allDrivers, allFoundDrivers);
    }

    @Test
    public void findAllTestDrivers_returnsAllTestDrivers() {
        //Given
        List<Driver> allDrivers = Stream.of(createTestingDriverWithStatus(DriverStatus.TEST),
                createTestingDriverWithStatus(DriverStatus.MAIN),
                createTestingDriverWithStatus(DriverStatus.TEST)).collect(Collectors.toList());
        when(driverDaoMock.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allTestDrivers = driverService.getAllDriversByStatus(DriverStatus.TEST);

        //Then
        assertEquals(2, allTestDrivers.size());
    }

    @Test
    public void findAllMainDrivers_returnsAllMainDrivers() {
        //Given
        List<Driver> allDrivers = Stream.of(createTestingDriverWithStatus(DriverStatus.TEST),
                createTestingDriverWithStatus(DriverStatus.MAIN),
                createTestingDriverWithStatus(DriverStatus.TEST)).collect(Collectors.toList());
        when(driverDaoMock.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allTestDrivers = driverService.getAllDriversByStatus(DriverStatus.MAIN);

        //Then
        assertEquals(1, allTestDrivers.size());
    }

    @Test
    public void getDriverWithHighestCharacteristicsValue_withAgresivityAsBest_returnsTopDriverWithBestAgresivity() {
        //Given
        Driver topDriver = createTestingDriverWithCharacteristicsValues(
                Stream.of(
                        new CharacteristicsValue(CharacteristicsType.AGGRESIVITY, 100.0),
                        new CharacteristicsValue(CharacteristicsType.PATIENCE, 10.0),
                        new CharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 40.0),
                        new CharacteristicsValue(CharacteristicsType.ENDURANCE, 50.0)
                ).collect(Collectors.toList())
        );
        List<Driver> allDrivers = Stream.of(topDriver,
                createTestingDriverWithCharacteristicsValues(
                        Stream.of(
                                new CharacteristicsValue(CharacteristicsType.AGGRESIVITY, 15),
                                new CharacteristicsValue(CharacteristicsType.PATIENCE, 14),
                                new CharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 25),
                                new CharacteristicsValue(CharacteristicsType.ENDURANCE, 13)
                        ).collect(Collectors.toList())),
                createTestingDriverWithCharacteristicsValues(
                        Stream.of(
                                new CharacteristicsValue(CharacteristicsType.AGGRESIVITY, 78),
                                new CharacteristicsValue(CharacteristicsType.PATIENCE, 100),
                                new CharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 50),
                                new CharacteristicsValue(CharacteristicsType.ENDURANCE, 15)
                        ).collect(Collectors.toList()))).collect(Collectors.toList());
        when(driverDaoMock.findAll()).thenReturn(allDrivers);

        //When
        Driver foundDriver = driverService.findDriverWithHighestCharacteristicsValue(CharacteristicsType.AGGRESIVITY);

        //Then
        assertEquals(topDriver, foundDriver);
    }

    @Test
    public void deleteDriver_withExistingDriver_driverDeleteCalled() {
        //When
        driverService.deleteDriver(testingDriver);

        //Then
        verify(driverDaoMock, times(1)).delete(testingDriver);
    }

    @Test
    public void updateDriver_withExistingDriver_driverUpdated() {
        //Given
        when(driverDaoMock.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        testingDriver.setEmail("test@test.cz");
        Driver updatedDriver = driverService.updateDriver(testingDriver);

        //Then
        verify(driverDaoMock, times(1)).update(testingDriver);
        assertEquals(testingDriver, updatedDriver);
    }

    private Driver createTestingDriver() {
        return createCustomTestingDriver(
                createDate(2, 1, 1985),
                DriverStatus.MAIN,
                new ArrayList<>());
    }

    private Driver createTestingDriverWithCharacteristicsValues(List<CharacteristicsValue> values) {
        return createCustomTestingDriver(
                createDate(2, 1, 1985),
                DriverStatus.MAIN,
                values);
    }

    private Driver createTestingDriverWithStatus(DriverStatus status) {
        return createCustomTestingDriver(
                createDate(2, 1, 1985),
                status,
                new ArrayList<>());
    }

    private Driver createCustomTestingDriver(Date birthDate,
                                             DriverStatus status,
                                             List<CharacteristicsValue> characteristicsValues) {
        return new Driver("John", "Doe", "john@doe.com", "", "American",
                birthDate, status, characteristicsValues);
    }
}
