package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.engineer.EngineerDao;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.service.EngineerServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.fail;

/**
 * @author Ivan Dendis
 */
public class EngineerServiceImplTest extends BaseServiceTest<Engineer> {

    @Mock
    private EngineerDao dao;

    @InjectMocks
    private EngineerServiceImpl engineerService;

    @Test
    public void registerEngineer_withValidValues_driverRegistered() {
        //given
        String password = "password";

        //when
        when(dao.findById(any())).thenReturn(entity);

        engineerService.register(entity, password);

        //then
        verify(dao, times(1)).add(entity);
        assertTrue(Validator.validatePassword(password, entity.getPassword()));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void registerEngineer_withMissingPassword_throwsException() {
        //given
        String password = "";

        //when
        engineerService.register(entity, password);

        //then
        fail("Exception is not thrown");
    }

    @Test
    public void findById_withExistingValue() {
        entity.setId(1);
        when(dao.findById(entity.getId())).thenReturn(entity);

        Engineer engineer = engineerService.findById(entity.getId());

        AssertJUnit.assertEquals(entity, engineer);
    }

    @Test
    public void updateEngineer_withValidValues() {
        engineerService.update(entity);

        verify(dao, times(1)).update(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateEngineer_withInvalidEngineer_exceptionIsThrown() {
        //given
        entity.setEmail("");

        //when
        when(dao.findById(entity.getId())).thenReturn(entity);
        engineerService.update(entity);

        //then
        fail("Exception is not thrown");
    }

    @Test
    public void removeEngineer_withValidValues() {
        engineerService.remove(entity.getId());

        verify(dao, times(1)).delete(entity.getId());
    }

    @Test(expected = FormulaOneTeamException.class)
    public void removeEngineer_withInvalidEngineer_exceptionIsThrown() {
        //when
        engineerService.remove(-1);

        //then
        fail("Exception is not thrown");
    }

    @Test
    public void getAllEngineersTest() {
        //given
        List<Engineer> componentList = new ArrayList<>();
        componentList.add(entity);
        componentList.add(entity);

        //when
        when(dao.findAll()).thenReturn(componentList);
        List<Engineer> resultComponentList = engineerService.getAll();

        //then
        assertNotNull(resultComponentList);
        assertEquals(resultComponentList.size(), 2);
        assertTrue(resultComponentList.contains(entity));
    }

    @Override
    protected Engineer createTestEntity() {
        return createEngineer();
    }
}
