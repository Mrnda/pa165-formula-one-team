package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.RaceParticipation.RaceParticipationDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.RaceParticipationServiceImpl;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.date.DateService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.util.Pair;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author Adel Chakouri
 */
public class RaceParticipationServiceTest extends BaseServiceTest<RaceParticipation> {

    @Mock
    private RaceParticipationDao dao;

    @Mock
    private RaceService raceServiceMock;

    @Mock
    private DateService dateServiceMock;

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

    @Test
    public void findByRaceId_returnsCorrectParticipations() {
        //given
        List<RaceParticipation> raceList = new ArrayList<>();
        RaceParticipation raceParticipation = createTestEntity();
        raceList.add(raceParticipation);
        raceParticipation = createTestEntity();
        raceList.add(raceParticipation);
        raceParticipation = createTestEntity();
        raceParticipation.getRace().setId(124);
        raceList.add(raceParticipation);
        when(dao.findAll()).thenReturn(raceList);

        //when
        List<RaceParticipation> resultRaceList = raceParticipationService.findByRaceId(124L);

        //then
        Assert.assertNotNull(resultRaceList);
        Assert.assertEquals(resultRaceList.size(), 1);
        Assert.assertTrue(resultRaceList.contains(raceParticipation));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void participateInWorldChampionship_championshipInPast_throws() {
        //Given
        final Date date = createDate(2, 11, 1987);
        final String location = "Barcelona";
        final Pair<CarSetup, Driver> firstDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        final Pair<CarSetup, Driver> secondDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        when(dateServiceMock.getCurrentDate()).thenReturn(createDate(2, 11, 2018));

        //When
        final List<RaceParticipation> raceParticipations = raceParticipationService.participateInWorldChampionship(date, location, Arrays.asList(firstDriverCarSetup, secondDriverCarSetup));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void participateInWorldChampionship_withTestDriver_throws() {
        //Given
        final Date date = createDate(2, 12, 2018);
        final String location = "Barcelona";
        final Driver testDriver = createDriver();
        testDriver.setDriverStatus(DriverStatus.TEST);
        final Pair<CarSetup, Driver> firstDriverCarSetup = Pair.of(createCarSetup(), testDriver);
        final Pair<CarSetup, Driver> secondDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        when(dateServiceMock.getCurrentDate()).thenReturn(createDate(2, 11, 2018));

        //When
        final List<RaceParticipation> raceParticipations = raceParticipationService.participateInWorldChampionship(date, location, Arrays.asList(firstDriverCarSetup, secondDriverCarSetup));
    }

    @Test(expected = FormulaOneTeamException.class)
    public void participateInWorldChampionship_withThreeSetups_throws() {
        //Given
        final Date date = createDate(2, 12, 2018);
        final String location = "Barcelona";
        final Pair<CarSetup, Driver> firstDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        final Pair<CarSetup, Driver> secondDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        final Pair<CarSetup, Driver> thirdDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        when(dateServiceMock.getCurrentDate()).thenReturn(createDate(2, 11, 2018));

        //When
        final List<RaceParticipation> raceParticipations = raceParticipationService.participateInWorldChampionship(date, location, Arrays.asList(firstDriverCarSetup, secondDriverCarSetup, thirdDriverCarSetup));
    }

    @Test
    public void participateInWorldChampionship_withValidData_createsRace() {
        //Given
        final Date date = createDate(2, 12, 2018);
        final String location = "Barcelona";
        final Pair<CarSetup, Driver> firstDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        final Pair<CarSetup, Driver> secondDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        when(dateServiceMock.getCurrentDate()).thenReturn(createDate(2, 11, 2018));
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.DECEMBER, 2);
        when(dateServiceMock.createCalendarForDate(date)).thenReturn(calendar);

        //When
        final List<RaceParticipation> raceParticipations = raceParticipationService.participateInWorldChampionship(date, location, Arrays.asList(firstDriverCarSetup, secondDriverCarSetup));

        //Then
        final Race race = new Race(date, "2018 world championship", location);
        verify(raceServiceMock, times(1)).add(race);
    }

    @Test
    public void participateInWorldChampionship_withValidData_createsParticipations() {
        //Given
        final Date date = createDate(2, 12, 2018);
        final String location = "Barcelona";
        final Pair<CarSetup, Driver> firstDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        final Pair<CarSetup, Driver> secondDriverCarSetup = Pair.of(createCarSetup(), createDriver());
        when(dateServiceMock.getCurrentDate()).thenReturn(createDate(2, 11, 2018));
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.DECEMBER, 2);
        when(dateServiceMock.createCalendarForDate(date)).thenReturn(calendar);

        //When
        final List<RaceParticipation> raceParticipations = raceParticipationService.participateInWorldChampionship(date, location, Arrays.asList(firstDriverCarSetup, secondDriverCarSetup));

        //Then
        final Race race = new Race(date, "2018 world championship", location);
        final RaceParticipation firstParticipation = new RaceParticipation(firstDriverCarSetup.getFirst(),
                firstDriverCarSetup.getSecond(),
                race,
                RaceParticipation.NO_RESULT_POSITION);
        verify(dao, times(2)).add(firstParticipation);
    }

    @Override
    protected RaceParticipation createTestEntity() {
        return createRaceParticipation();
    }
}
