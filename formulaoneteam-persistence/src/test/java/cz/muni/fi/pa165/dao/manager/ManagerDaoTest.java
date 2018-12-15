package cz.muni.fi.pa165.dao.manager;

import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.Manager;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class ManagerDaoTest extends BaseTest {

    @Inject
    private ManagerDao managerDao;

    @Test
    public void addNewManager_foundById() {
        Manager manager = createManager("test@email.com");

        managerDao.add(manager);

        assertNotNull(managerDao.findById(manager.getId()));
    }

    @Test
    public void addNewManager_foundByEmail() {
        String email = "asd@asd.asd";
        Manager manager = createManager(email);

        managerDao.add(manager);

        assertNotNull(managerDao.findByEmail(email));
    }

    @Test
    public void addAndUpdateManager_updatedIsFound() {
        String oldMail = "old@email.com";
        String newMail = "new@email.com";
        Manager m = createManager(oldMail);

        managerDao.add(m);

        m.setEmail(newMail);

        Manager oldManager = managerDao.findByEmail(oldMail);
        Manager newManager = managerDao.findByEmail(newMail);

        assertNull(oldManager);
        assertNotNull(newManager);
    }

    @Test
    public void deleteManager_managerNotFound() {
        String email = "asd@asd.asd";
        Manager manager = createManager(email);

        managerDao.add(manager);
        managerDao.delete(manager.getId());

        Manager result = managerDao.findByEmail(email);
        assertNull(result);
    }

    @Test
    public void addTwoManagers_bothAreFound() {
        Manager m1 = createManager("m1@mail.com");
        Manager m2 = createManager("m2@mail.com");

        managerDao.add(m1);
        managerDao.add(m2);

        List<Manager> result = managerDao.findAll();
        assertEquals(result.size(), 2);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void addNullManager_illegalArgumentExceptionThrown() {
        managerDao.add(null);
    }

    private Manager createManager(String email) {
        return new Manager("name", "surname", email);
    }
}
