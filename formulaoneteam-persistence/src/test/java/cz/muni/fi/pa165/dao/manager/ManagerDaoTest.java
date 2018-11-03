package cz.muni.fi.pa165.dao.manager;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.entity.Manager;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import org.testng.Assert;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;


/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes= AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ManagerDaoTest extends AbstractTestNGSpringContextTests {

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
        Manager m = createManager(email);
        
        managerDao.add(m);
        managerDao.delete(m);
        
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
    
    private Manager createManager(String email) {
        Manager manager = new Manager();
        
        manager.setName("name");
        manager.setPassword("pwd");
        manager.setSurname("surname");
        manager.setEmail(email);
        
        return manager;
    }
}
