package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.junit.Rule;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static cz.muni.fi.pa165.enums.EngineerSpecialization.AERODYNAMICS;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    protected Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    protected CarSetup createCarSetup() {
        CarSetup carSetup = new CarSetup(createComponent(), createComponent(), createComponent(), createComponent(),
                createComponent(), createComponent());
        carSetup.setId(1);
        return carSetup;
    }

    protected Component createComponent() {
        Component component = new Component("default", ComponentType.ENGINE);
        component.setId(1);
        return component;
    }

    protected RaceParticipation createRaceParticipation() {
        RaceParticipation raceParticipation = new RaceParticipation(createCarSetup(), createDriver(), createRace(), 4);
        raceParticipation.setId(1);
        return raceParticipation;
    }

    protected Driver createDriver() {
        Driver driver = new Driver("name", "surname", "123@muni.cz",
                "driverNationality", new Date(), DriverStatus.MAIN, Sets.newSet(createCharacteristicsValue())
        );
        driver.setId(22);
        driver.setPassword("password");
        return driver;
    }

    protected CharacteristicsValue createCharacteristicsValue() {
        CharacteristicsValue characteristicsValue = new CharacteristicsValue(CharacteristicsType.AGGRESSIVITY, 10.0);
        characteristicsValue.setId(5);
        return characteristicsValue;
    }

    protected Race createRace() {
        Race race = new Race(new Date(), "GP Monaco", "location");
        race.setId(3);
        return race;
    }

    protected TestDrive createTestDrive() {
        TestDrive testDrive = new TestDrive(createCarSetup(), createDriver(), "notes", new Date());
        testDrive.setId(22);
        return testDrive;
    }

    protected Manager createManager() {
        Manager manager = new Manager("name", "surname", "email@gmail.com");
        manager.setId(33);
        manager.setPassword("aspdaspdasjd");
        return manager;
    }

    protected DriverDTO createDriverDTO() {
        DriverDTO driverDetailDTO = new DriverDTO();
        driverDetailDTO.setId(22);
        driverDetailDTO.setName("John");
        driverDetailDTO.setSurname("Doe");
        driverDetailDTO.setEmail("John@doe.com");
        driverDetailDTO.setNationality("American");
        driverDetailDTO.setBirthday(createDate(2, 9, 1989));
        driverDetailDTO.setCharacteristics(new ArrayList<>());
        driverDetailDTO.setDriverStatus(DriverStatus.MAIN);
        return driverDetailDTO;
    }

    protected ComponentDTO createComponentDTO() {
        return createComponentDTO(ComponentType.ENGINE);
    }

    private ComponentDTO createComponentDTO(ComponentType type) {
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setId(1);
        componentDTO.setType(type);
        componentDTO.setName("test " + type.toString());
        return componentDTO;
    }

    protected CarSetupDTO createCarSetupDTO() {
        CarSetupDTO dto = new CarSetupDTO();
        dto.setId(1);
        dto.setEngine(createComponentDTO(ComponentType.ENGINE));
        dto.setSuspension(createComponentDTO(ComponentType.SUSPENSION));
        dto.setBrakes(createComponentDTO(ComponentType.BRAKES));
        dto.setCover(createComponentDTO(ComponentType.COVER));
        dto.setTires(createComponentDTO(ComponentType.TIRES));
        dto.setTransmission(createComponentDTO(ComponentType.TRANSMISSION));
        return dto;
    }

    protected RaceDTO createRaceDTO() {
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId(3);
        raceDTO.setDate(new Date());
        raceDTO.setLocation("location");
        raceDTO.setTitle("GP Monaco");
        return raceDTO;
    }

    protected TestDriveDTO createTestDriveDTO() {
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setId(22);
        testDriveDTO.setCarSetup(createCarSetupDTO());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriver(createDriverDTO());
        testDriveDTO.setNotes("notes");
        return testDriveDTO;
    }

    protected RaceParticipationDTO createRaceParticipationDTO() {
        RaceParticipationDTO raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setId(1);
        raceParticipationDTO.setCarSetup(createCarSetupDTO());
        raceParticipationDTO.setDriver(createDriverDTO());
        raceParticipationDTO.setRace(createRaceDTO());
        raceParticipationDTO.setResultPosition(1);
        return raceParticipationDTO;
    }

    protected Engineer createEngineer() {
        Engineer engineer = new Engineer("name", "surname", "email@email.email", AERODYNAMICS);
        engineer.setId(22);
        engineer.setPassword("hash123");
        return engineer;
    }

    protected EngineerDTO createEngineerDTO() {
        EngineerDTO engineer = new EngineerDTO();
        engineer.setId(22);
        engineer.setName("name");
        engineer.setSurname("surname");
        engineer.setEmail("email@email.email");
        engineer.setSpecialization(AERODYNAMICS);
        engineer.setPassword("hash123");
        return engineer;
    }

    protected ComponentParameter createComponentParameter() {
        ComponentParameter componentParameter = new ComponentParameter("Name", "Value");
        componentParameter.setId(55);
        return componentParameter;
    }

    protected ComponentParameterDTO createComponentParameterDTO() {
        ComponentParameterDTO dto = new ComponentParameterDTO();
        dto.setId(55);
        dto.setName("name");
        dto.setValue("value");
        return dto;
    }
}
