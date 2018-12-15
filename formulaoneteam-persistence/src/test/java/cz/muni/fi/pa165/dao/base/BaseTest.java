package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.dao.Race.RaceDao;
import cz.muni.fi.pa165.dao.carsetup.CarSetupDao;
import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes = AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Inject
    private DriverDao driverDao;
    @Inject
    private CarSetupDao carSetupDao;
    @Inject
    private ComponentDao componentDao;
    @Inject
    private RaceDao raceDao;

    protected CarSetup createCarSetup() {
        Component engine = new Component("engine", ComponentType.ENGINE);
        Component suspension = new Component("suspension", ComponentType.SUSPENSION);
        Component brakes = new Component("brakes", ComponentType.BRAKES);
        Component transmission = new Component("transmission", ComponentType.TRANSMISSION);
        Component tires = new Component("tires", ComponentType.TIRES);
        Component cover = new Component("cover", ComponentType.COVER);

        componentDao.add(engine);
        componentDao.add(suspension);
        componentDao.add(brakes);
        componentDao.add(transmission);
        componentDao.add(tires);
        componentDao.add(cover);

        CarSetup carSetup = new CarSetup(engine, suspension, brakes, transmission, tires, cover);

        carSetupDao.add(carSetup);

        return carSetup;
    }

    protected Driver createDriver(String email) {

        Driver driver = new Driver(
                "name",
                "surname",
                email,
                "driverNationality",
                createDate(2, 11, 1995),
                DriverStatus.MAIN);
        driverDao.add(driver);

        return driver;
    }

    protected String getDefaultDriverEmail() {
        return "driver@car.com";
    }

    protected TestDrive createTestDrive() {
        return new TestDrive(createCarSetup(), createDriver(getDefaultDriverEmail()), "notes", createDate(20,12,2018));
    }

    protected RaceParticipation createRaceParticipation(String driverEmail, String raceTitle) {
        return new RaceParticipation(createCarSetup(), createDriver(driverEmail), createRace(raceTitle), 1);
    }

    protected Date createDate(int date, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    protected Race createRace(String raceTitle) {
        Race race = new Race(createDate(2, 11, 1995), raceTitle, "location");
        raceDao.add(race);
        return race;
    }

    protected String getDefaultRaceTitle() {
        return "raceTitle";
    }

    protected CharacteristicsValue createCharacteristics(CharacteristicsType type){
        return new CharacteristicsValue(type, 100.0);
    }
}
