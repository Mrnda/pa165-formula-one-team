package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.carsetup.CarSetupDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.service.CarSetupServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.fail;

/**
 * @author Théo Desjardins
 */
public class CarSetupServiceImplTest extends BaseServiceTest<CarSetup> {

    @Mock
    private CarSetupDao carSetupDaoMock;

    @InjectMocks
    private CarSetupServiceImpl carSetupService;

    @Test
    public void addCarSetup_withValidValues() {
        //when
        carSetupService.add(entity);

        //then
        verify(carSetupDaoMock, times(1)).add(entity);
    }

    @Test
    public void findById_withExistingValue() {
        //given
        when(carSetupDaoMock.findById(entity.getId())).thenReturn(entity);

        //when
        CarSetup carSetup = carSetupService.findById(entity.getId());

        //then
        AssertJUnit.assertEquals(entity, carSetup);
    }

    @Test
    public void updateCarSetup_withValidValues() {
        //when
        carSetupService.update(entity);

        //then
        verify(carSetupDaoMock, times(1)).update(entity);
    }

    @Test
    public void removeCarSetup_withValidValues() {
        //when
        carSetupService.remove(entity.getId());

        //then
        verify(carSetupDaoMock, times(1)).delete(entity.getId());
    }

    @Test
    public void getAllCarSetupTest() {
        //given
        List<CarSetup> carSetupList = new ArrayList<>();
        carSetupList.add(entity);
        carSetupList.add(entity);

        //when
        when(carSetupDaoMock.findAll()).thenReturn(carSetupList);
        List<CarSetup> resultCarSetupList = carSetupService.getAll();

        //then
        assertNotNull(resultCarSetupList);
        assertEquals(resultCarSetupList.size(), 2);
        assertTrue(resultCarSetupList.contains(entity));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void add_throwsException() {
        //when
        carSetupService.add((CarSetup) null);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = FormulaOneTeamException.class)
    public void update_exceptionIsThrown() {
        //given
        entity.setId(-1);

        //when
        when(carSetupService.findById(entity.getId())).thenReturn(entity);
        carSetupService.update(entity);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = FormulaOneTeamException.class)
    public void remove_exceptionIsThrown() {
        //when
        carSetupService.remove(-1);

        //then
        fail("Exception is not thrown");
    }

    @Override
    protected CarSetup createTestEntity() {
        return createCarSetup();
    }
}
