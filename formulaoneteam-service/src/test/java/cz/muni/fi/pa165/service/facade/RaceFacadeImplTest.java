package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceDTO;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */

public class RaceFacadeImplTest extends BaseFacadeTest {

    @Mock
    private RaceService raceService;

    @InjectMocks
    private RaceFacadeImpl raceFacade;

    private RaceDTO raceDTO;

    private Race race;

    @BeforeMethod
    public void setUp() {

        raceDTO = new RaceDTO();
        raceDTO.setDate(new Date());
        raceDTO.setLocation("location");
        raceDTO.setTitle("GP Monaco");

        race = createRace();
        when(beanMappingServiceMock.mapTo(raceDTO,Race.class)).thenReturn(race);
    }

    @Test
    public void getAllRacesTest(){
        //Given
        List<Race> listRaces = new ArrayList<>();
        listRaces.add(race);
        when(raceService.getAll()).thenReturn(listRaces);
        List<RaceDTO> listDTORaces = new ArrayList<>();
        listDTORaces.add(raceDTO);
        when(beanMappingServiceMock.mapTo(listRaces, RaceDTO.class)).thenReturn(listDTORaces);

        //When
        List<RaceDTO> resListRaceDTO = new ArrayList<>(raceFacade.getAllRaces());

        //Then
        verify(raceService).getAll();
        assertEquals(resListRaceDTO.size(),1);
        Assert.assertTrue(resListRaceDTO.contains(raceDTO));
    }

    @Test
    public void findRaceById() {
        //Given
        when(beanMappingServiceMock.mapTo(race, RaceDTO.class)).thenReturn(raceDTO);
        when(raceService.findById(3L)).thenReturn(race);

        //When
        RaceDTO resRaceDTO = raceFacade.findRaceByID(race.getId());

        //Then
        assertEquals(resRaceDTO,raceDTO);
    }

    @Test
    public void deleteRaceTest() {
        //When
        raceFacade.deleteRace(raceDTO);

        //Then
        verify(raceService, times(1)).remove(race);
    }

    @Test
    public void updateRaceTest() {
        //When
        raceFacade.updateRace(raceDTO);

        //Then
        verify(raceService, times(1)).update(race);
    }

    @Test
    public void addRaceTest() {
        //When
        raceFacade.addRace(raceDTO);

        //Then
        verify(raceService, times(1)).add(race);
    }
}
