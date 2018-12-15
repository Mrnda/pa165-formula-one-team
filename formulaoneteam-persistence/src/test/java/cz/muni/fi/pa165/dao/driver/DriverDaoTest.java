package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.Driver;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Adel Chakouri
 */
public class DriverDaoTest extends BaseTest {

    @Inject
    private DriverDao driverDao;

    @Test
    public void addDriver_withValidData_isPersisted() {
        //given
        Driver driver = createDriver("Driver@drive.cz");

        //when
        driverDao.add(driver);

        //then
        assertNotNull(driverDao.findById(driver.getId()));
    }

    @Test
    public void deleteDriver_withValidData_isPersisted() {
        String mail = "Driver@drive.cz";
        Driver driver = createDriver(mail);

        driverDao.add(driver);
        driverDao.delete(driver.getId());

        Driver result = driverDao.findByEmail(mail);
        assertNull(result);
    }

    @Test
    public void updateDriver_withEmail() {
        String oldMail = "driver1@drive.cz";
        String newMail = "driver22@drive.Cz";

        Driver driver = createDriver(oldMail);

        driverDao.add(driver);
        driver.setEmail(newMail);
        driverDao.update(driver);

        Driver newDriver = driverDao.findById(driver.getId());
        assertEquals(newMail, newDriver.getEmail());
    }

    @Test
    public void addTwoDrivers() {
        Driver d1 = createDriver("driver123@driver.fr");
        Driver d2 = createDriver("driver321@driver.cz");

        driverDao.add(d1);
        driverDao.add(d2);

        List<Driver> result = driverDao.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(d1));
        assertTrue(result.contains(d2));
    }
}
