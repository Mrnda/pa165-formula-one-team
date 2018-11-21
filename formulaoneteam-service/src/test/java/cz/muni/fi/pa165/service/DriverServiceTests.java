package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.utils.Validator;
import org.junit.Before;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class DriverServiceTests extends AbstractTestNGSpringContextTests {

    @Mock
    DriverDao driverDaoMock;

    @InjectMocks
    DriverServiceImpl driverService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private Driver testingDriver;

    @BeforeClass
    public void setUpClass(){
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        testingDriver = createTestingDriver(DriverStatus.MAIN);
    }

    @Test
    public void registerDriver_withValidValues_driverRegistered(){
        //Given
        String password = "password";

        //When
        driverService.registerDriver(testingDriver, password);

        //Then
        verify(driverDaoMock, times(1)).add(testingDriver);
        assertTrue(Validator.validatePassword(password, testingDriver.getPasswordHash()));
    }

    @Test
    public void registeredDriver_withValidPassword_isAuthenticated(){
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
    public void registeredDriver_withInvalidPassword_isAuthenticated(){
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
    public void registeredDriver_canBeFoundById(){
        //Given
        when(driverDaoMock.findById(testingDriver.getId())).thenReturn(testingDriver);

        //When
        final Driver foundDriver = driverService.findDriverById(testingDriver.getId());

        //Then
        assertEquals(testingDriver, foundDriver);
    }

    @Test
    public void registeredDriver_canBeFoundByEmail(){
        //Given
        when(driverDaoMock.findByEmail(testingDriver.getEmail())).thenReturn(testingDriver);

        //When
        final Driver foundDriver = driverService.findDriverByEmail(testingDriver.getEmail());

        //Then
        assertEquals(testingDriver, foundDriver);
    }

    @Test
    public void findAllTestDrivers_returnsAllTestDrivers(){
        //Given
        List<Driver> allDrivers = Stream.of(createTestingDriver(DriverStatus.TEST),
                createTestingDriver(DriverStatus.MAIN),
                createTestingDriver(DriverStatus.TEST)).collect(Collectors.toList());
        when(driverDaoMock.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allTestDrivers = driverService.getAllDriversByStatus(DriverStatus.TEST);

        //Then
        assertEquals(2, allTestDrivers.size());
    }

    @Test
    public void findAllMainDrivers_returnsAllMainDrivers(){
        //Given
        List<Driver> allDrivers = Stream.of(createTestingDriver(DriverStatus.TEST),
                createTestingDriver(DriverStatus.MAIN),
                createTestingDriver(DriverStatus.TEST)).collect(Collectors.toList());
        when(driverDaoMock.findAll()).thenReturn(allDrivers);

        //When
        List<Driver> allTestDrivers = driverService.getAllDriversByStatus(DriverStatus.MAIN);

        //Then
        assertEquals(1, allTestDrivers.size());
    }

    private Driver createTestingDriver(DriverStatus status) {
        return new Driver("John",
                "Doe",
                "john@doe.com",
                "",
                "American",
                createDate(2,1,1985),
                status,
                new ArrayList<>());
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
