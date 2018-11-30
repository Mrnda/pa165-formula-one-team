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

public class RaceParticipationFacadeImplTest extends BaseFacadeTest {

    @Mock
    private RaceParticipationService raceParticipationService;

    @InjectMocks
    private RaceParticipationFacadeImpl raceParticipationFacade;

    private RaceParticipationDTO raceParticipationDTO;

    private RaceParticipation raceParticipation;


    @BeforeMethod
    public void setUp() {

        raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setCar(createCarSetup());
        raceParticipationDTO.setDriver(createDriver());
        raceParticipationDTO.setRace(createRace());
        raceParticipationDTO.setResultPosition(1);

        raceParticipation = createRaceParticipation();
        when(beanMappingServiceMock.mapTo(raceParticipationDTO,RaceParticipation.class)).thenReturn(raceParticipation);
    }

    @Test
    public void getAllRaceParticipationTest(){
        //Given
        List<RaceParticipation> listRaceParticipation = new ArrayList<>();
        listRaceParticipation.add(raceParticipation);
        when(raceParticipationService.getAll()).thenReturn(listRaceParticipation);
        List<RaceParticipationDTO> listDTORaceParticipation = new ArrayList<>();
        listDTORaceParticipation.add(raceParticipationDTO);
        when(beanMappingServiceMock.mapTo(listRaceParticipation, RaceParticipationDTO.class)).thenReturn(listDTORaceParticipation);

        //When
        List<RaceParticipationDTO> resListRaceParticipationDTO = new ArrayList<>(raceParticipationFacade.getAllRaceParticipation());

        //Then
        verify(raceParticipationService).getAll();
        assertEquals(resListRaceParticipationDTO.size(),1);
        Assert.assertTrue(resListRaceParticipationDTO.contains(raceParticipationDTO));
    }

    @Test
    public void findRaceParticipationById() {
        //Given
        when(beanMappingServiceMock.mapTo(raceParticipation, RaceParticipationDTO.class)).thenReturn(raceParticipationDTO);
        when(raceParticipationService.findById(1)).thenReturn(raceParticipation);

        //When
        RaceParticipationDTO resRaceParticipationDTO = raceParticipationFacade.findRaceParticipationById(raceParticipation.getId());

        //Then
        assertEquals(resRaceParticipationDTO,raceParticipationDTO);
    }

    @Test
    public void deleteRaceParticipationTest() {
       //When
        raceParticipationFacade.deleteRaceParticipation(raceParticipationDTO);

        //Then
        verify(raceParticipationService, times(1)).remove(raceParticipation);
    }

    @Test
    public void updateRaceParticipationTest() {
       //When
        raceParticipationFacade.updateRaceParticipation(raceParticipationDTO);

        //Then
        verify(raceParticipationService, times(1)).update(raceParticipation);
    }

    @Test
    public void addRaceParticipationTest() {
        //When
        raceParticipationFacade.addRaceParticipation(raceParticipationDTO);

        //Then
        verify(raceParticipationService, times(1)).add(raceParticipation);
    }
}
