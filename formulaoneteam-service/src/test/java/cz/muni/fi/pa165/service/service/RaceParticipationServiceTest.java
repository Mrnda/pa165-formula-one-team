package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.RaceParticipation.RaceParticipationDao;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.service.RaceParticipationServiceImpl;
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
public class RaceParticipationServiceTest extends BaseServiceTest<RaceParticipation> {

    @Mock
    private RaceParticipationDao dao;

    @InjectMocks
    private RaceParticipationServiceImpl raceParticipationService;

    @Test
    public void addRaceParticipation_WithValidValues() {
        //when
        raceParticipationService.add(entity);

        //then
        verify(dao, times(1)).add(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void addRaceParticipation_exceptionIsThrown() {
        //when
        raceParticipationService.add(null);
    }

    @Test
    public void findById_WithExistingValue() {
        //Given
        when(dao.findById(entity.getId())).thenReturn(entity);

        //When
        RaceParticipation raceParticipation = raceParticipationService.findById(entity.getId());

        //Then
        assertEquals(entity, raceParticipation);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void findById_exceptionIsThrown() {
        //when
        raceParticipationService.findById(-1);
    }

    @Test
    public void updateRaceParticipation_WithValidValues() {
        //When
        raceParticipationService.update(entity);

        //Then
        verify(dao, times(1)).update(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateRaceParticipation_exceptionIsThrown() {
        //when
        raceParticipationService.update(null);
    }

    @Test
    public void removeRaceParticipation_WithValidValues() {
        //When
        raceParticipationService.remove(entity);

        //Then
        verify(dao, times(1)).delete(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void removeRaceParticipation_exceptionIsThrown() {
        //when
        raceParticipationService.remove(null);
    }

    @Test
    public void getAllRaceParticipations_receiveCorrectList() {
        //given
        List<RaceParticipation> raceList = new ArrayList<>();
        raceList.add(entity);
        raceList.add(entity);

        //when
        when(dao.findAll()).thenReturn(raceList);
        List<RaceParticipation> resultRaceList = raceParticipationService.getAll();

        //then
        Assert.assertNotNull(resultRaceList);
        Assert.assertEquals(resultRaceList.size(), 2);
        Assert.assertTrue(resultRaceList.contains(entity));
    }

    @Override
    protected RaceParticipation createTestEntity() {
        return createRaceParticipation();
    }
}
