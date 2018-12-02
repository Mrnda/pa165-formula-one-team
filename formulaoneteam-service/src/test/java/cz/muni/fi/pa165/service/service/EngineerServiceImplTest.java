package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.engineer.EngineerDao;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.enums.EngineerSpecialization;
import cz.muni.fi.pa165.service.EngineerServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.utils.Validator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Ivan Dendis
 */
public class EngineerServiceImplTest extends BaseServiceTest<Engineer> {

    @Mock
    private EngineerDao engineerDaoMock;

    @InjectMocks
    private EngineerServiceImpl engineerService;

    @Test
    public void registerEngineer_withValidValues_driverRegistered() {
        String password = "password";

        engineerService.register(entity, password);

        verify(engineerDaoMock, times(1)).add(entity);
        assertTrue(Validator.validatePassword(password, entity.getPasswordHash()));
    }

    @Test
    public void findById_withExistingValue() {
        entity.setId(1);
        when(engineerDaoMock.findById(entity.getId())).thenReturn(entity);

        Engineer engineer = engineerService.findById(entity.getId());

        AssertJUnit.assertEquals(entity, engineer);
    }

    @Test
    public void updateEngineer_withValidValues() {
        engineerService.update(entity);

        verify(engineerDaoMock, times(1)).update(entity);
    }

    @Test
    public void removeEngineer_withValidValues() {
        engineerService.remove(entity);

        verify(engineerDaoMock, times(1)).delete(entity);
    }

    @Test
    public void getAllEngineersTest() {
        List<Engineer> componentList = new ArrayList<>();
        componentList.add(entity);
        componentList.add(entity);

        when(engineerDaoMock.findAll()).thenReturn(componentList);
        List<Engineer> resultComponentList = engineerService.getAll();

        assertNotNull(resultComponentList);
        assertEquals(resultComponentList.size(), 2);
        assertTrue(resultComponentList.contains(entity));
    }

    @Override
    protected Engineer createTestEntity() {
        return new Engineer("name", "surname", "email@email.email", "a", EngineerSpecialization.AERODYNAMICS);
    }
}
