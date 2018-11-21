package cz.muni.fi.pa165.dao.carsetup;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.entity.Component;

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
@ContextConfiguration(classes = AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class CarSetupDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private CarSetupDao carSetupDao;

    @Inject
    private ComponentDao componentDao;

    @Test
    public void addNewCarSetup_foundById() {
        //given
        CarSetup carSetup = createDefaultCarSetup();

        //when
        carSetupDao.add(carSetup);

        //then
        CarSetup foundSetup = carSetupDao.findById(carSetup.getId());
        assertNotNull(foundSetup);
        assertEquals(carSetup, foundSetup);
    }

    @Test
    public void addNewCarSetup_foundInAll() {
        //given
        CarSetup carSetup = createDefaultCarSetup();

        //when
        carSetupDao.add(carSetup);

        //then
        List<CarSetup> allCarSetups = carSetupDao.findAll();
        assertEquals(1, allCarSetups.size());
        assertTrue(allCarSetups.contains(carSetup));
    }

    @Test
    public void createMultipleCarSetups_foundAll() {
        //given
        CarSetup carSetup1 = createDefaultCarSetup();
        CarSetup carSetup2 = createCar(createComponent("Brakes", ComponentType.BRAKES),
                carSetup1.getCover(),
                carSetup1.getEngine(),
                carSetup1.getSuspension(),
                carSetup1.getTransmission(),
                carSetup1.getTires());

        //when
        carSetupDao.add(carSetup1);
        carSetupDao.add(carSetup2);

        //then
        List<CarSetup> allCarSetups = carSetupDao.findAll();
        assertEquals(2, allCarSetups.size());
        assertTrue(allCarSetups.contains(carSetup1));
        assertTrue(allCarSetups.contains(carSetup2));
    }

    @Test
    public void updateCarSetupComponent_componentUpdated() {
        //given
        CarSetup carSetup = createDefaultCarSetup();
        carSetupDao.add(carSetup);
        Component newBrakes = createComponent("Testing brakes v2", ComponentType.BRAKES);

        //when
        carSetup.setBrakes(newBrakes);
        carSetupDao.update(carSetup);

        //then
        CarSetup foundSetup = carSetupDao.findById(carSetup.getId());
        assertNotNull(foundSetup);
        assertEquals(newBrakes, foundSetup.getBrakes());
    }

    @Test
    public void removeCarSetup_carSetupNotFoundById() {
        //given
        CarSetup carSetup = createDefaultCarSetup();
        carSetupDao.add(carSetup);

        //when
        carSetupDao.delete(carSetup);

        //then
        CarSetup foundSetup = carSetupDao.findById(carSetup.getId());
        assertNull(foundSetup);
    }

    @Test
    public void removeCarSetup_carSetupNotFoundAtAll() {
        //given
        CarSetup carSetup = createDefaultCarSetup();
        carSetupDao.add(carSetup);

        //when
        carSetupDao.delete(carSetup);

        //then
        List<CarSetup> allCarSetups = carSetupDao.findAll();
        assertNotNull(allCarSetups);
        assertEquals(0, allCarSetups.size());
    }

    private CarSetup createDefaultCarSetup() {
        Component brakes = createComponent("Testing brakes", ComponentType.BRAKES);
        Component cover = createComponent("Testing cover", ComponentType.COVER);
        Component engine = createComponent("Testing engine", ComponentType.ENGINE);
        Component suspension = createComponent("Testing suspension", ComponentType.SUSPENSION);
        Component transmission = createComponent("Testing transmission", ComponentType.TRANSMISSION);
        Component tires = createComponent("Testing tires", ComponentType.TIRES);
        return createCar(brakes, cover, engine, suspension, transmission, tires);
    }

    private Component createComponent(String name, ComponentType type)
    {
        Component component = new Component(name, type);
        componentDao.add(component);
        return component;
    }

    private CarSetup createCar(Component brakes,
                               Component cover,
                               Component engine,
                               Component suspension,
                               Component transmission,
                               Component tires)
    {
        return new CarSetup(engine, suspension, brakes, transmission, tires, cover);
    }
}
