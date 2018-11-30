package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CarSetupDTO;
import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Théo Desjardins
 */

public class CarSetupFacadeImplTest extends BaseFacadeTest {

    private CarSetup testingCarSetup;
    private CarSetupDTO carSetupDTO;

    @Mock
    private CarSetupService carSetupService;

    @InjectMocks
    private CarSetupFacadeImpl carSetupFacade;

    @BeforeMethod
    public void setUp() {

        carSetupDTO = new CarSetupDTO();

        carSetupDTO.setEngine(new ComponentDTO());
        carSetupDTO.setSuspension(new ComponentDTO());
        carSetupDTO.setBrakes(new ComponentDTO());
        carSetupDTO.setCover(new ComponentDTO());
        carSetupDTO.setTires(new ComponentDTO());
        carSetupDTO.setTransmission(new ComponentDTO());

        testingCarSetup = createCarSetup();

        when(beanMappingServiceMock.mapTo(carSetupDTO,CarSetup.class)).thenReturn(testingCarSetup);
    }

    @Test
    public void findCarSetupById() {
        //Given
        when(carSetupService.findById(testingCarSetup.getId())).thenReturn(testingCarSetup);
        when(beanMappingServiceMock.mapTo(testingCarSetup, CarSetupDTO.class)).thenReturn(carSetupDTO);
        //When
        CarSetupDTO resCarSetupDTO = carSetupFacade.findByID(testingCarSetup.getId());
        //Then
        assertEquals(resCarSetupDTO,carSetupDTO);
    }

    @Test
    public void deleteCarSetupTest() {

        //when
        carSetupFacade.deleteCarSetup(carSetupDTO);

        //then
        verify(carSetupService, times(1)).delete(testingCarSetup);
    }

    @Test
    public void updateCarSetupTest() {

        //when
        carSetupFacade.updateCarSetup(carSetupDTO);

        //then
        verify(carSetupService, times(1)).update(testingCarSetup);
    }

    @Test
    public void addCarSetupTest() {

        //when
        carSetupFacade.addCarSetup(carSetupDTO);

        //then
        verify(carSetupService, times(1)).add(testingCarSetup);
    }

    @Test
    public void getAllCarSetupTest() {

        //Given
        List<CarSetup> listCarSetup = new ArrayList<>();
        listCarSetup.add(testingCarSetup);
        when(carSetupService.getAllCarSetup()).thenReturn(listCarSetup);
        List<CarSetupDTO> listDTOCarSetup = new ArrayList<>();
        listDTOCarSetup.add(carSetupDTO);
        when(beanMappingServiceMock.mapTo(listCarSetup, CarSetupDTO.class)).thenReturn(listDTOCarSetup);

        //When
        List<CarSetupDTO> resListCarSetupDTO = new ArrayList<>(carSetupFacade.getAllCarSetup());

        //Then
        verify(carSetupService).getAllCarSetup();
        assertEquals(resListCarSetupDTO.size(),1);
        Assert.assertTrue(resListCarSetupDTO.contains(carSetupDTO));
    }
}
