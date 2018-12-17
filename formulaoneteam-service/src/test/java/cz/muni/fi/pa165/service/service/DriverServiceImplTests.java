package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.DriverServiceImpl;
import cz.muni.fi.pa165.service.base.BaseTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cz.muni.fi.pa165.enums.CharacteristicsType.*;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class DriverServiceImplTests extends BaseTest {

    @Mock
    private DriverDao driverDao;

    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver testingDriver;

    @Before
    public void setUp() {
        testingDriver = createDriver();
    }

    @Test
    public void registerDriver_withValidValues_driverRegistered() {
        //Given
        String password = "password";

        //When
        driverService.register(testingDriver, password);

        //Then
        verify(driverDao, times(1)).add(testingDriver);
        assertTrue(Validator.validatePassword(password, testingDriver.getPassword()));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void registerDriver_withMissingPassword_throwsException() {
        //Given
        String password = "";

        //When
        driverService.register(testingDriver, password);

        //Then
    }

    @Test(expected = FormulaOneTeamException.class)
    public void registerDriver_withMissingEmail_throwsException() {
        //Given
        String password = "password";

        //When
        testingDriver.setEmail("");
        driverService.register(testingDriver, password);

        //Then
    }

    @Test
    public void registeredDriver_withValidPassword_isAuthenticated() {
        //Given
        String password = "password";
        testingDriver.setPassword(Validator.createHash(password));
        when(driverDao.findByEmail(testingDriver.getEmail())).thenReturn(testingDriver);

        //When
        boolean result = driverService.authenticate(testingDriver.getEmail(), password);

        //Then
        assertTrue(result);
    }

    @Test
    public void registeredDriver_withInvalidPassword_isAuthenticated() {
        //Given
        String validPassword = "password";
        testingDriver.setPassword(Validator.createHash(validPassword));
        String invalidPassword = "invalid_password";
        when(driverDao.findByEmail(testingDriver.getEmail())).thenReturn(testingDriver);

        //When
        boolean result = driverService.authenticate(testingDriver.getEmail(), invalidPassword);

        //Then
        assertFalse(result);
    }

    @Test
    public void registeredDriver_canBeFoundById() {
        //Given
        when(driverDao.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        final Driver foundDriver = driverService.findById(testingDriver.getId());

        //Then
        assertEquals(testingDriver, foundDriver);
    }

    @Test
    public void registeredDriver_canBeFoundByEmail() {
        //Given
        when(driverDao.findByEmail(testingDriver.getEmail())).thenReturn(testingDriver);

        //When
        final Driver foundDriver = driverService.findByEmail(testingDriver.getEmail());

        //Then
        assertEquals(testingDriver, foundDriver);
    }

    @Test
    public void findAllDrivers_returnsAllDrivers() {
        //Given
        List<Driver> allDrivers = Stream.of(createTestingDriverWithStatus(DriverStatus.TEST),
                createTestingDriverWithStatus(DriverStatus.MAIN),
                createTestingDriverWithStatus(DriverStatus.TEST)).collect(Collectors.toList());
        when(driverDao.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allFoundDrivers = driverService.getAll();

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
        when(driverDao.findAll()).thenReturn(allDrivers);

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
        when(driverDao.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allTestDrivers = driverService.getAllDriversByStatus(DriverStatus.MAIN);

        //Then
        assertEquals(1, allTestDrivers.size());
    }

    @Test
    public void getDriverWithHighestCharacteristicsValue_withAgresivityAsBest_returnsTopDriverWithBestAgresivity() {
        //Given
        Driver topDriver = createTestingDriver();
        topDriver.addCharacteristics(Sets.newSet(
                new CharacteristicsValue(AGGRESSIVITY, 100.0),
                new CharacteristicsValue(PATIENCE, 10.0),
                new CharacteristicsValue(DRIVING_ON_WET, 40.0),
                new CharacteristicsValue(ENDURANCE, 50.0)
        ));

        Driver nextDriver1 = createTestingDriver();
        nextDriver1.addCharacteristics(Sets.newSet(
                new CharacteristicsValue(AGGRESSIVITY, 15),
                new CharacteristicsValue(PATIENCE, 14),
                new CharacteristicsValue(DRIVING_ON_WET, 25),
                new CharacteristicsValue(ENDURANCE, 13)
        ));

        Driver nextDriver2 = createTestingDriver();
        nextDriver2.addCharacteristics(Sets.newSet(
                new CharacteristicsValue(AGGRESSIVITY, 60),
                new CharacteristicsValue(PATIENCE, 20),
                new CharacteristicsValue(DRIVING_ON_WET, 26),
                new CharacteristicsValue(ENDURANCE, 50)
        ));

        List<Driver> allDrivers = Stream.of(
                topDriver,
                nextDriver1,
                nextDriver2)
                .collect(Collectors.toList());

        when(driverDao.findAll()).thenReturn(allDrivers);

        //When
        Driver foundDriver = driverService.findDriverWithHighestCharacteristicsValue(AGGRESSIVITY);

        //Then
        assertEquals(topDriver, foundDriver);
    }

    @Test
    public void deleteDriver_withExistingDriver_driverDeleteCalled() {
        //When
        driverService.remove(testingDriver.getId());

        //Then
        verify(driverDao, times(1)).delete(testingDriver.getId());
    }

    @Test
    public void updateDriver_withExistingDriver_driverUpdated() {
        //Given
        when(driverDao.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        testingDriver.setEmail("test@test.cz");
        testingDriver.setPassword("some random password hash");
        Driver updatedDriver = driverService.update(testingDriver);

        //Then
        verify(driverDao, times(1)).update(testingDriver);
        assertEquals(testingDriver, updatedDriver);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateDriver_withInvalidDriver_driverUpdated() {
        //Given
        when(driverDao.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        testingDriver.setEmail("");
        Driver updatedDriver = driverService.update(testingDriver);

        //Then
    }

    private Driver createTestingDriver() {
        return createCustomTestingDriver(
                createDate(2, 1, 1985),
                DriverStatus.MAIN
        );
    }

    private Driver createTestingDriverWithStatus(DriverStatus status) {
        return createCustomTestingDriver(
                createDate(2, 1, 1985),
                status
        );
    }

    private Driver createCustomTestingDriver(Date birthDate, DriverStatus status) {
        return new Driver("John", "Doe", "john@doe.com", "American", birthDate, status);
    }
}
