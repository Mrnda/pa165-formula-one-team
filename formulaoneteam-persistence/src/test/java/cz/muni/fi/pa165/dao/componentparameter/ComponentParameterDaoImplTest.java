package cz.muni.fi.pa165.dao.componentparameter;

import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.ComponentParameter;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class ComponentParameterDaoImplTest extends BaseTest {

    private static String TEST_NAME = "testName";

    @Inject
    private ComponentParameterDao componentParameterDao;

    private ComponentParameter testComponentParameter;

    @Before
    public void setUp() {
        testComponentParameter = createTestComponentParameter();
    }

    @Test
    public void createNewComponentParameter_foundById() {
        //when
        componentParameterDao.add(testComponentParameter);

        //then
        assertEquals(TEST_NAME, componentParameterDao.findById(testComponentParameter.getId()).getName());
    }

    @Test
    public void createNewComponentParameter_foundInAllComponentParameters() {
        //when
        componentParameterDao.add(testComponentParameter);

        //then
        List<ComponentParameter> componentParameters = componentParameterDao.findAll();
        assertEquals(1, componentParameters.size());
        assertTrue(componentParameters.contains(testComponentParameter));
    }

    @Test
    public void createMultipleNewComponentParameters_foundAll() {
        //given
        ComponentParameter otherComponentParameter = new ComponentParameter("otherName", "50.0");

        //when
        componentParameterDao.add(testComponentParameter);
        componentParameterDao.add(otherComponentParameter);

        //then
        List<ComponentParameter> componentParameters = componentParameterDao.findAll();
        assertEquals(2, componentParameters.size());
        assertTrue(componentParameters.contains(testComponentParameter));
        assertTrue(componentParameters.contains(otherComponentParameter));
    }

    @Test
    public void updateComponentParameter_componentParameterUpdated() {
        //given
        componentParameterDao.add(testComponentParameter);
        String newName = "newName";

        //when
        testComponentParameter.setName(newName);
        componentParameterDao.update(testComponentParameter);

        //then
        assertEquals(newName, componentParameterDao.findById(testComponentParameter.getId()).getName());
    }

    @Test
    public void removeComponentParameter_componentParameterNotFoundById() {
        //given
        componentParameterDao.add(testComponentParameter);

        //when
        componentParameterDao.delete(testComponentParameter);

        //then
        assertNull(componentParameterDao.findById(testComponentParameter.getId()));
    }

    @Test
    public void removeComponentParameter_componentParameterNotFoundAtAll() {
        //given
        componentParameterDao.add(testComponentParameter);

        //when
        componentParameterDao.delete(testComponentParameter);

        //then
        assertEquals(0, componentParameterDao.findAll().size());
    }

    private ComponentParameter createTestComponentParameter() {
        return new ComponentParameter(TEST_NAME, "100.0");
    }
}
