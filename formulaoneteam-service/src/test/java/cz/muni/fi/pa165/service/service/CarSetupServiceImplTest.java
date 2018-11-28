package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.carsetup.CarSetupDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.service.CarSetupServiceImpl;
import cz.muni.fi.pa165.service.base.BaseTest;
import org.junit.Before;
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

/**
 * @author Théo Desjardins
 */
public class CarSetupServiceImplTest extends BaseTest {

    private CarSetup testingCarSetup;

    @Mock
    private CarSetupDao carSetupDaoMock;

    @InjectMocks
    private CarSetupServiceImpl carSetupService;

    @Before
    public void setUp() {
        testingCarSetup = createCarSetup();
    }

    @Test
    public void addCarSetup_withValidValues() {
        //when
        carSetupService.add(testingCarSetup);

        //then
        verify(carSetupDaoMock, times(1)).add(testingCarSetup);
    }

    @Test
    public void findById_withExistingValue() {
        //given
        when(carSetupDaoMock.findById(testingCarSetup.getId())).thenReturn(testingCarSetup);

        //when
        CarSetup carSetup = carSetupService.findById(testingCarSetup.getId());

        //then
        AssertJUnit.assertEquals(testingCarSetup, carSetup);
    }

    @Test
    public void updateCarSetup_withValidValues() {
        //when
        carSetupService.update(testingCarSetup);

        //then
        verify(carSetupDaoMock, times(1)).update(testingCarSetup);
    }

    @Test
    public void removeCarSetup_withValidValues() {
        //when
        carSetupService.delete(testingCarSetup);

        //then
        verify(carSetupDaoMock, times(1)).delete(testingCarSetup);
    }

    @Test
    public void getAllCarSetupTest() {
        //given
        List<CarSetup> carSetupList = new ArrayList<>();
        carSetupList.add(testingCarSetup);
        carSetupList.add(testingCarSetup);

        //when
        when(carSetupDaoMock.findAll()).thenReturn(carSetupList);
        List<CarSetup> resultCarSetupList = carSetupService.getAllCarSetup();

        //then
        assertNotNull(resultCarSetupList);
        assertEquals(resultCarSetupList.size(), 2);
        assertTrue(resultCarSetupList.contains(testingCarSetup));
    }
}
