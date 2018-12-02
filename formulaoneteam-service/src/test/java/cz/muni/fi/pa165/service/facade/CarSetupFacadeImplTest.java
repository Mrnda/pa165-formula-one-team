package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CarSetupDTO;
import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Théo Desjardins
 */
public class CarSetupFacadeImplTest extends BaseFacadeTest<CarSetup, CarSetupDTO> {
    
    @Mock
    private CarSetupService carSetupService;

    @InjectMocks
    private CarSetupFacadeImpl carSetupFacade;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        when(beanMappingServiceMock.mapTo(dto, CarSetup.class)).thenReturn(entity);
    }

    @Test
    public void findCarSetupById() {
        //Given
        when(carSetupService.findById(entity.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, CarSetupDTO.class)).thenReturn(dto);
        //When
        CarSetupDTO resdto = carSetupFacade.findByID(entity.getId());
        //Then
        assertEquals(resdto, dto);
    }

    @Test
    public void deleteCarSetupTest() {

        //when
        carSetupFacade.deleteCarSetup(dto);

        //then
        verify(carSetupService, times(1)).remove(entity);
    }

    @Test
    public void updateCarSetupTest() {

        //when
        carSetupFacade.updateCarSetup(dto);

        //then
        verify(carSetupService, times(1)).update(entity);
    }

    @Test
    public void addCarSetupTest() {

        //when
        carSetupFacade.addCarSetup(dto);

        //then
        verify(carSetupService, times(1)).add(entity);
    }

    @Test
    public void getAllCarSetupTest() {

        //Given
        List<CarSetup> listCarSetup = new ArrayList<>();
        listCarSetup.add(entity);
        when(carSetupService.getAll()).thenReturn(listCarSetup);
        List<CarSetupDTO> listDTOCarSetup = new ArrayList<>();
        listDTOCarSetup.add(dto);
        when(beanMappingServiceMock.mapTo(listCarSetup, CarSetupDTO.class)).thenReturn(listDTOCarSetup);

        //When
        List<CarSetupDTO> resListdto = new ArrayList<>(carSetupFacade.getAllCarSetup());

        //Then
        verify(carSetupService).getAll();
        assertEquals(resListdto.size(), 1);
        Assert.assertTrue(resListdto.contains(dto));
    }

    @Override
    protected CarSetup createTestEntity() {
        return createCarSetup();
    }

    @Override
    protected CarSetupDTO createTestDTO() {
        CarSetupDTO dto = new CarSetupDTO();
        dto.setEngine(new ComponentDTO());
        dto.setSuspension(new ComponentDTO());
        dto.setBrakes(new ComponentDTO());
        dto.setCover(new ComponentDTO());
        dto.setTires(new ComponentDTO());
        dto.setTransmission(new ComponentDTO());
        return dto;
    }
}
