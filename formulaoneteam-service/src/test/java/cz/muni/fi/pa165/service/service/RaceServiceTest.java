package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.Race.RaceDao;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.service.RaceServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */
public class RaceServiceTest extends BaseServiceTest<Race> {

    @Mock
    private RaceDao raceDaoMock;

    @InjectMocks
    private RaceServiceImpl raceService;

    @Test
    public void addRace_WithValidValues() {
        //when
        raceService.add(entity);

        //then
        verify(raceDaoMock, times(1)).add(entity);
    }

    @Test
    public void findById_WithExistingValue() {
        //given
        when(raceDaoMock.findById(entity.getId())).thenReturn(entity);

        //when
        Race race = raceService.findById(entity.getId());

        //then
        assertEquals(entity, race);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void findById_exceptionIsThrown() {
        //when
        raceService.findById(-1);
    }

    @Test
    public void updateRace_WithValidValues() {
        //when
        raceService.update(entity);

        //then
        verify(raceDaoMock, times(1)).update(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateRace_exceptionIsThrown() {
        //when
        raceService.update(null);
    }

    @Test
    public void removeRace_WithValidValues() {
        //when
        raceService.remove(entity);

        //then
        verify(raceDaoMock, times(1)).delete(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void removeRace_exceptionIsThrown() {
        //when
        raceService.remove(null);
    }

    @Test
    public void getAllRacesTest() {
        //given
        List<Race> raceList = new ArrayList<>();
        raceList.add(entity);

        //when
        when(raceDaoMock.findAll()).thenReturn(raceList);
        List<Race> resultRaceList = raceService.getAll();

        //then
        Assert.assertNotNull(resultRaceList);
        Assert.assertEquals(resultRaceList.size(), 1);
        Assert.assertTrue(resultRaceList.contains(entity));
    }

    @Override
    protected Race createTestEntity() {
        return createRace();
    }
}
