package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        component.setId(0);
        return component;
    }

    protected RaceParticipation createRaceParticipation() {
        RaceParticipation raceParticipation = new RaceParticipation(createCarSetup(), createDriver(), createRace(), 4);
        raceParticipation.setId(1);
        return raceParticipation;
    }

    protected Driver createDriver() {
        List<CharacteristicsValue> characteristicsValues = new ArrayList<>();
        characteristicsValues.add(createCharacteristicsValue());

        Driver driver = new Driver("name", "surname", "123@muni.cz", "password",
                "driverNationality", new Date(), DriverStatus.MAIN
        );
        driver.setId(2);
        return driver;
    }

    protected CharacteristicsValue createCharacteristicsValue() {
        CharacteristicsValue characteristicsValue = new CharacteristicsValue();
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
        Manager manager = new Manager("name", "surname", "email@gmail.com", "aspdaspdasjd");
        manager.setId(33);
        return manager;
    }

    protected DriverDetailDTO createDriverDetailDTO() {
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

    protected ComponentDTO createComponentDTO() {
        return createComponentDTO(ComponentType.ENGINE);
    }

    private ComponentDTO createComponentDTO(ComponentType type) {
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setType(type);
        componentDTO.setName("test " + type.toString());
        return componentDTO;
    }

    protected CarSetupDTO createCarSetupDTO() {
        CarSetupDTO dto = new CarSetupDTO();
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
        raceDTO.setDate(new Date());
        raceDTO.setLocation("location");
        raceDTO.setTitle("GP Monaco");
        return raceDTO;
    }

    protected TestDriveDTO createTestDriveDTO() {
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setCar(createCarSetupDTO());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriver(createDriverDetailDTO());
        testDriveDTO.setNotes("notes");
        return testDriveDTO;
    }

    protected RaceParticipationDTO creaateRaceParticipationDTO() {
        RaceParticipationDTO raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setCar(createCarSetupDTO());
        raceParticipationDTO.setDriver(createDriverDetailDTO());
        raceParticipationDTO.setRace(createRaceDTO());
        raceParticipationDTO.setResultPosition(1);
        return raceParticipationDTO;
    }
}
