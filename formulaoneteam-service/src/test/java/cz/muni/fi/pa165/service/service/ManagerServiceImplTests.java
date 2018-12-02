package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.manager.ManagerDao;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.ManagerServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class ManagerServiceImplTests extends BaseServiceTest<Manager> {

    @Mock
    private ManagerDao dao;

    @InjectMocks
    private ManagerServiceImpl service;

    @Test
    public void registerManager_managerRegistered() {
        //given
        String password = "password";

        //when
        service.register(entity, password);

        //then
        verify(dao).add(entity);
        assertTrue(Validator.validatePassword(password, entity.getPasswordHash()));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void registerManager_withMissingPassword_throwsException() {
        //given
        String password = "";

        //when
        service.register(entity, password);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = FormulaOneTeamException.class)
    public void registerManager_withMissingEmail_throwsException() {
        //given
        String password = "password";

        //when
        entity.setEmail("");
        service.register(entity, password);

        //then
        fail("Exception is not thrown");
    }

    @Test
    public void registeredManager_withValidPassword_isAuthenticated() {
        //given
        String password = "password";

        //when
        entity.setPasswordHash(Validator.createHash(password));
        when(dao.findByEmail(entity.getEmail())).thenReturn(entity);

        //then
        assertTrue(service.authenticate(entity.getEmail(), password));
    }

    @Test
    public void registeredManager_withInvalidPassword_isNotAuthenticated() {
        //given
        String validPassword = "password";
        String invalidPassword = "invalid_password";

        //when
        entity.setPasswordHash(Validator.createHash(validPassword));
        when(dao.findByEmail(entity.getEmail())).thenReturn(entity);

        //then
        assertFalse(service.authenticate(entity.getEmail(), invalidPassword));
    }

    @Test
    public void registeredManager_canBeFoundById() {
        //when
        when(dao.findById(entity.getId())).thenReturn(entity);

        //then
        assertEquals(entity, service.findById(entity.getId()));
    }

    @Test
    public void registeredManager_canBeFoundByEmail() {
        //when
        when(dao.findByEmail(entity.getEmail())).thenReturn(entity);

        //then
        assertEquals(entity, service.findByEmail(entity.getEmail()));
    }

    @Test
    public void findAllManager_returnsAllManager() {
        //given
        List<Manager> allManager = Arrays.asList(createManager(), createManager(), createManager());

        //when
        when(dao.findAll()).thenReturn(allManager);

        //then
        assertEquals(3, service.getAll().size());
        assertEquals(allManager, service.getAll());
    }

    @Test
    public void deleteManager_withExistingManager_managerDeleteCalled() {
        //when
        service.remove(entity);

        //then
        verify(dao).delete(entity);
    }

    @Test
    public void updateManager_withExistingManager_managerUpdated() {
        //given
        when(dao.findById(entity.getId())).thenReturn(entity);

        //when
        entity.setEmail("test@test.cz");
        entity.setPasswordHash("asdapso2321");

        //then
        assertEquals(entity, service.update(entity));
        verify(dao).update(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateManager_withInvalidManager_managerUpdated() {
        //given
        entity.setEmail("");

        //when
        when(dao.findById(entity.getId())).thenReturn(entity);
        service.update(entity);

        //then
        fail("Exception is not thrown");
    }

    @Override
    protected Manager createTestEntity() {
        return createManager();
    }
}
