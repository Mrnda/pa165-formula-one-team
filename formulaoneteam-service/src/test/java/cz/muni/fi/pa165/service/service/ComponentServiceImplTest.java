package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.service.ComponentServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

/**
 * @author Théo Desjardins
 */
public class ComponentServiceImplTest extends BaseServiceTest<Component> {

    @Mock
    private ComponentDao componentDaoMock;

    @InjectMocks
    private ComponentServiceImpl componentService;

    @Test
    public void addComponent_withValidValues() {
        //when
        componentService.add(entity);

        //then
        verify(componentDaoMock, times(1)).add(entity);
    }

    @Test
    public void findById_withExistingValue() {
        //given
        when(componentDaoMock.findById(entity.getId())).thenReturn(entity);

        //when
        Component component = componentService.findById(entity.getId());

        //then
        AssertJUnit.assertEquals(entity, component);
    }

    @Test
    public void updateComponent_withValidValues() {
        //when
        componentService.update(entity);

        //then
        verify(componentDaoMock, times(1)).update(entity);
    }

    @Test
    public void removeComponent_withValidValues() {
        //when
        componentService.remove(entity);

        //then
        verify(componentDaoMock, times(1)).delete(entity);
    }

    @Test
    public void getAllComponentTest() {
        //given
        List<Component> componentList = new ArrayList<>();
        componentList.add(entity);
        componentList.add(entity);

        //when
        when(componentDaoMock.findAll()).thenReturn(componentList);
        List<Component> resultComponentList = componentService.getAll();

        //then
        assertNotNull(resultComponentList);
        assertEquals(resultComponentList.size(), 2);
        assertTrue(resultComponentList.contains(entity));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void add_throwsException() {
        //when
        componentService.add(null);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = FormulaOneTeamException.class)
    public void update_exceptionIsThrown() {
        //given
        entity.setName("");

        //when
        when(componentService.findById(entity.getId())).thenReturn(entity);
        componentService.update(entity);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = FormulaOneTeamException.class)
    public void remove_exceptionIsThrown() {
        //when
        componentService.remove(null);

        //then
        fail("Exception is not thrown");
    }

    @Override
    protected Component createTestEntity() {
        return createComponent();
    }
}
