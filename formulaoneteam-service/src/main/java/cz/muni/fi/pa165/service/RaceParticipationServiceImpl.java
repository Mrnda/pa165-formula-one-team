package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.RaceParticipation.RaceParticipationDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.date.DateService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.data.util.Pair;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Adel Chakouri
 */
@Service
public class RaceParticipationServiceImpl
        extends BaseEntityServiceImpl<RaceParticipation, RaceParticipationDao>
        implements RaceParticipationService {

    @Inject
    RaceService raceService;

    @Inject
    DateService dateService;

    @Override
    public List<RaceParticipation> participateInWorldChampionship(Date date, String location, Pair<CarSetup, Driver> firstSetup, Pair<CarSetup, Driver> secondSetup) {
        if (!dateService.isInFuture(date)) {
            throw new FormulaOneTeamException("World championship has to be created in future");
        }

        if (firstSetup.getSecond().getDriverStatus() == DriverStatus.TEST
                || secondSetup.getSecond().getDriverStatus() == DriverStatus.TEST) {
            throw new FormulaOneTeamException("Test drivers can't participate in world championship.");
        }

        Calendar championshipCalendar = dateService.createCalendarForDate(date);
        Race race = new Race(date, championshipCalendar.get(Calendar.YEAR) + " world championship", location);
        raceService.add(race);

        RaceParticipation firstParticipation = new RaceParticipation(firstSetup.getFirst(), firstSetup.getSecond(), race, RaceParticipation.NO_RESULT_POSITION);
        add(firstParticipation);

        RaceParticipation secondParticipation = new RaceParticipation(secondSetup.getFirst(), secondSetup.getSecond(), race, RaceParticipation.NO_RESULT_POSITION);
        add(secondParticipation);

        return Arrays.asList(firstParticipation, secondParticipation);
    }

    @Override
    public void validateEntity(@Nullable RaceParticipation entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("RaceParticipation entity is null or not configured");
        }
    }

}
