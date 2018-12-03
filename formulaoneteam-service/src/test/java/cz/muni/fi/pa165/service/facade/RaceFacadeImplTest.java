package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceDTO;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */
public class RaceFacadeImplTest extends BaseFacadeTest<Race, RaceDTO> {

    @Mock
    private RaceService raceService;

    @InjectMocks
    private RaceFacadeImpl raceFacade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, Race.class)).thenReturn(entity);
    }

    @Test
    public void getAllRacesTest() {
        //Given
        List<Race> listRaces = new ArrayList<>();
        listRaces.add(entity);
        when(raceService.getAll()).thenReturn(listRaces);
        List<RaceDTO> listDTORaces = new ArrayList<>();
        listDTORaces.add(dto);
        when(beanMappingServiceMock.mapTo(listRaces, RaceDTO.class)).thenReturn(listDTORaces);

        //When
        List<RaceDTO> resListRaceDTO = new ArrayList<>(raceFacade.getAllRaces());

        //Then
        verify(raceService).getAll();
        assertEquals(resListRaceDTO.size(), 1);
        Assert.assertTrue(resListRaceDTO.contains(dto));
    }

    @Test
    public void findRaceById() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, RaceDTO.class)).thenReturn(dto);
        when(raceService.findById(3L)).thenReturn(entity);

        //When
        RaceDTO resRaceDTO = raceFacade.findRaceByID(entity.getId());

        //Then
        assertEquals(resRaceDTO, dto);
    }

    @Test
    public void deleteRaceTest() {
        //When
        raceFacade.deleteRace(dto);

        //Then
        verify(raceService, times(1)).remove(entity);
    }

    @Test
    public void updateRaceTest() {
        //When
        raceFacade.updateRace(dto);

        //Then
        verify(raceService, times(1)).update(entity);
    }

    @Test
    public void addRaceTest() {
        //When
        raceFacade.addRace(dto);

        //Then
        verify(raceService, times(1)).add(entity);
    }

    @Override
    protected Race createTestEntity() {
        return createRace();
    }

    @Override
    protected RaceDTO createTestDTO() {
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setDate(new Date());
        raceDTO.setLocation("location");
        raceDTO.setTitle("GP Monaco");
        return raceDTO;
    }
}
