package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.componentparameter.ComponentParameterDao;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.service.ComponentParameterServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Ivan Dendis
 */
public class ComponentParameterServiceImplTest extends BaseServiceTest<ComponentParameter> {

    @Mock
    private ComponentParameterDao dao;

    @InjectMocks
    private ComponentParameterServiceImpl service;

    @Test
    public void addComponentParameter_WithValidValues() {
        service.add(entity);

        verify(dao, times(1)).add(entity);
    }

    @Test
    public void findById_WithExistingValue() {
        entity.setId(1);
        when(dao.findById(entity.getId())).thenReturn(entity);

        ComponentParameter componentParameter = service.findById(entity.getId());

        assertEquals(entity, componentParameter);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void findById_exceptionIsThrown() {
        service.findById(-1);
    }

    @Test
    public void updateComponentParameter_WithValidValues() {
        service.update(entity);

        verify(dao, times(1)).update(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateComponentParameter_exceptionIsThrown() {
        service.update(null);
    }

    @Test
    public void removeComponentParameters_WithValidValues() {
        service.remove(entity.getId());

        verify(dao, times(1)).delete(entity.getId());
    }

    @Test(expected = FormulaOneTeamException.class)
    public void removeComponentParameter_exceptionIsThrown() {
        service.remove(-1);
    }

    @Test
    public void getAllComponentParameterListTest() {
        List<ComponentParameter> componentParameterList = new ArrayList<>();
        componentParameterList.add(entity);

        when(dao.findAll()).thenReturn(componentParameterList);
        List<ComponentParameter> resultComponentParameterList = service.getAll();

        Assert.assertNotNull(resultComponentParameterList);
        Assert.assertEquals(resultComponentParameterList.size(), 1);
        Assert.assertTrue(resultComponentParameterList.contains(entity));
    }

    @Override
    protected ComponentParameter createTestEntity() {
        return createComponentParameter();
    }
}
