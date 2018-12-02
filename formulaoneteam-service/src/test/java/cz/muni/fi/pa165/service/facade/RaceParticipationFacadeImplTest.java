package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */

public class RaceParticipationFacadeImplTest extends BaseFacadeTest<RaceParticipation, RaceParticipationDTO> {

    @Mock
    private RaceParticipationService raceParticipationService;

    @InjectMocks
    private RaceParticipationFacadeImpl raceParticipationFacade;

    @BeforeMethod
    public void setUp() {
        when(beanMappingServiceMock.mapTo(dto, RaceParticipation.class)).thenReturn(entity);
    }

    @Test
    public void getAllRaceParticipationTest() {
        //Given
        List<RaceParticipation> listRaceParticipation = new ArrayList<>();
        listRaceParticipation.add(entity);
        when(raceParticipationService.getAll()).thenReturn(listRaceParticipation);
        List<RaceParticipationDTO> listDTORaceParticipation = new ArrayList<>();
        listDTORaceParticipation.add(dto);
        when(beanMappingServiceMock.mapTo(listRaceParticipation, RaceParticipationDTO.class)).thenReturn(listDTORaceParticipation);

        //When
        List<RaceParticipationDTO> resListRaceParticipationDTO = new ArrayList<>(raceParticipationFacade.getAllRaceParticipation());

        //Then
        verify(raceParticipationService).getAll();
        assertEquals(resListRaceParticipationDTO.size(), 1);
        Assert.assertTrue(resListRaceParticipationDTO.contains(dto));
    }

    @Test
    public void findRaceParticipationById() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, RaceParticipationDTO.class)).thenReturn(dto);
        when(raceParticipationService.findById(1)).thenReturn(entity);

        //When
        RaceParticipationDTO resRaceParticipationDTO = raceParticipationFacade.findRaceParticipationById(entity.getId());

        //Then
        assertEquals(resRaceParticipationDTO, dto);
    }

    @Test
    public void deleteRaceParticipationTest() {
        //When
        raceParticipationFacade.deleteRaceParticipation(dto);

        //Then
        verify(raceParticipationService, times(1)).remove(entity);
    }

    @Test
    public void updateRaceParticipationTest() {
        //When
        raceParticipationFacade.updateRaceParticipation(dto);

        //Then
        verify(raceParticipationService, times(1)).update(entity);
    }

    @Test
    public void addRaceParticipationTest() {
        //When
        raceParticipationFacade.addRaceParticipation(dto);

        //Then
        verify(raceParticipationService, times(1)).add(entity);
    }

    @Override
    protected RaceParticipation createTestEntity() {
        return createRaceParticipation();
    }

    @Override
    protected RaceParticipationDTO createTestDTO() {
        RaceParticipationDTO raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setCar(createCarSetup());
        raceParticipationDTO.setDriver(createDriver());
        raceParticipationDTO.setRace(createRace());
        raceParticipationDTO.setResultPosition(1);
        return raceParticipationDTO;
    }
}
