package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.DriverStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.AssertJUnit;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

@ContextConfiguration(classes= AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class DriverDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private DriverDao driverDao;

    @Test
    public void addDriver_withValidData_isPersisted() {
        //given
        Driver driver = new Driver();
        driver.setBirthdate(createDate(2, 11, 1995));
        driver.setName("name");
        driver.setSurname("surname");
        driver.setNationality("nationality");
        driver.setDriverStatus(DriverStatus.MAIN);
        driver.setPassword("password");
        driver.setEmail("driver@car.com");

        //when
        driverDao.add(driver);

        //then
        AssertJUnit.assertNotNull(driverDao.findById(driver.getId()));
    }

    private Date createDate(int date, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar.getTime();
    }
}
