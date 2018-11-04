package cz.muni.fi.pa165.dao.component;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.dao.componentparameter.ComponentParameterDao;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.entity.ComponentType;
import cz.muni.fi.pa165.entity.component.Component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.testng.AssertJUnit.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@ContextConfiguration(classes= AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    ComponentDao componentDao;

    @Inject
    ComponentParameterDao componentParameterDao;

    @Test
    public void createNewComponent_foundById(){
        //given
        Component component = createDefaultComponent();

        //when
        componentDao.add(component);

        //then
        assertNotNull(componentDao.findById(component.getId()));
    }

    @Test
    public void createNewComponent_foundInAllComponents(){
        //given
        Component component = createDefaultComponent();

        //when
        componentDao.add(component);

        //then
        final List<Component> allComponents = componentDao.findAll();
        assertEquals(1, allComponents.size());
        assertTrue(allComponents.contains(component));
    }

    @Test
    public void createMultipleNewComponents_foundAll(){
        //given
        Component component1 = createDefaultComponent();
        Component component2 = createComponent("General motros engine 2.0", ComponentType.ENGINE);

        //when
        componentDao.add(component1);
        componentDao.add(component2);

        //then
        final List<Component> allComponents = componentDao.findAll();
        assertEquals(2, allComponents.size());
        assertTrue(allComponents.contains(component1));
        assertTrue(allComponents.contains(component2));
    }

    @Test
    public void updateComponent_componentUpdated(){
        //given
        Component component = createDefaultComponent();
        componentDao.add(component);
        String expectedComponentName = "Testing component name";

        //when
        component.setName(expectedComponentName);
        componentDao.update(component);

        //then
        Component componentFromDao = componentDao.findById(component.getId());
        assertNotNull(componentFromDao);
        assertEquals(expectedComponentName, componentFromDao.getName());
    }

    @Test
    public void removeComponent_componentNotFoundById(){
        //given
        Component component = createDefaultComponent();
        componentDao.add(component);

        //when
        componentDao.delete(component);

        //then
        Component componentFromDao = componentDao.findById(component.getId());
        assertNull(componentFromDao);
    }

    @Test
    public void removeComponent_componentNotFoundAtAll(){
        //given
        Component component = createDefaultComponent();
        componentDao.add(component);

        //when
        componentDao.delete(component);

        //then
        List<Component> allComponents = componentDao.findAll();
        assertEquals(0, allComponents.size());
    }

    @Test
    public void addComponentParameter_parameterAdded(){
        //given
        Component component = createDefaultComponent();
        componentDao.add(component);
        ComponentParameter componentParameter = createComponentParameter("Test", 10.0);

        //when
        component.addParameter(componentParameter);
        componentDao.update(component);

        //then
        Component foundComponent = componentDao.findById(component.getId());
        assertNotNull(foundComponent);
        assertEquals(1, foundComponent.getParameters().size());
        assertTrue(foundComponent.getParameters().contains(componentParameter));
    }

    @Test
    public void removeComponentParameter_parameterRemoved(){
        //given
        Component component = createDefaultComponent();
        ComponentParameter componentParameter = createComponentParameter("Test", 10.0);
        component.addParameter(componentParameter);
        componentDao.add(component);

        //when
        component.removeParameter(componentParameter);
        componentDao.update(component);

        //then
        Component foundComponent = componentDao.findById(component.getId());
        assertNotNull(foundComponent);
        assertEquals(0, foundComponent.getParameters().size());
    }

    private ComponentParameter createComponentParameter(String name, double value){
        ComponentParameter componentParameter = new ComponentParameter();
        componentParameter.setName(name);
        componentParameter.setValue(value);
        componentParameterDao.add(componentParameter);
        return componentParameter;
    }

    private Component createDefaultComponent(){
        return createComponent("General motors engine 4.0", ComponentType.ENGINE);
    }

    private Component createComponent(String name, ComponentType type){
        return new Component(name, type);
    }

}
