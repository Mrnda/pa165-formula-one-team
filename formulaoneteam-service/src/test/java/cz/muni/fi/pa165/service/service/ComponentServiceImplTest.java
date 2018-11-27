package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.service.ComponentServiceImpl;
import cz.muni.fi.pa165.service.base.BaseTest;
import org.junit.Before;
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

/**
 * @author Théo Desjardins
 */

public class ComponentServiceImplTest extends BaseTest {

    private Component testingComponent;

    @Mock
    private ComponentDao componentDaoMock;

    @InjectMocks
    private ComponentServiceImpl componentService;

    @Before
    public void setUp() {
        testingComponent = new Component("engine", ComponentType.ENGINE);
    }

    @Test
    public void addComponent_withValidValues() {
        //when
        componentService.add(testingComponent);

        //then
        verify(componentDaoMock, times(1)).add(testingComponent);
    }

    @Test
    public void findById_withExistingValue() {
        //given
        when(componentDaoMock.findById(testingComponent.getId())).thenReturn(testingComponent);

        //when
        Component component = componentService.findById(testingComponent.getId());

        //then
        AssertJUnit.assertEquals(testingComponent, component);
    }

    @Test
    public void updateComponent_withValidValues() {
        //when
        componentService.update(testingComponent);

        //then
        verify(componentDaoMock, times(1)).update(testingComponent);
    }

    @Test
    public void removeComponent_withValidValues() {
        //when
        componentService.delete(testingComponent);

        //then
        verify(componentDaoMock, times(1)).delete(testingComponent);
    }

    @Test
    public void getAllComponentTest() {
        //given
        List<Component> componentList = new ArrayList<>();
        componentList.add(testingComponent);
        componentList.add(testingComponent);

        //when
        when(componentDaoMock.findAll()).thenReturn(componentList);
        List<Component> resultComponentList = componentService.getAllComponent();

        //then
        assertNotNull(resultComponentList);
        assertEquals(resultComponentList.size(), 2);
        assertTrue(resultComponentList.contains(testingComponent));
    }
}
