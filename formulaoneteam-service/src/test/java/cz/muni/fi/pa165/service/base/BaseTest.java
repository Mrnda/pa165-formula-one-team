package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.junit.Rule;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

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
                "driverNationality", new Date(), DriverStatus.MAIN,
                characteristicsValues);
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
}
