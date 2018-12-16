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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adel Chakouri
 */
@Service
public class RaceParticipationServiceImpl
        extends BaseEntityServiceImpl<RaceParticipation, RaceParticipationDao>
        implements RaceParticipationService {

    @Inject
    private RaceService raceService;

    @Inject
    private DateService dateService;

    @Override
    public List<RaceParticipation> participateInWorldChampionship(Date date, String location, List<Pair<CarSetup, Driver>> setups) {
        if (!date.after(dateService.getCurrentDate())) {
            throw new FormulaOneTeamException("World championship has to be created in future");
        }

        if (setups.size() != 2) {
            throw new FormulaOneTeamException("Only two setups can participate in world championship");
        }

        if (setups.stream().anyMatch(carSetupDriverPair -> carSetupDriverPair.getSecond().getDriverStatus() == DriverStatus.TEST)) {
            throw new FormulaOneTeamException("Test drivers can't participate in world championship.");
        }

        Calendar championshipCalendar = dateService.createCalendarForDate(date);
        Race race = new Race(date, championshipCalendar.get(Calendar.YEAR) + " world championship", location);
        raceService.add(race);

        List<RaceParticipation> participations = new ArrayList<>();
        for (Pair<CarSetup, Driver> setupDriverPair : setups) {
            RaceParticipation participation = new RaceParticipation(setupDriverPair.getFirst(), setupDriverPair.getSecond(), race, RaceParticipation.NO_RESULT_POSITION);
            add(participation);
            participations.add(participation);
        }

        return findByRaceId(race.getId());
    }

    @Override
    public List<RaceParticipation> findByRaceId(Long id) {
        return getAll().stream()
                .filter(raceParticipation -> raceParticipation.getRace().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public void validateEntity(@Nullable RaceParticipation entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("RaceParticipation entity is null or not configured");
        }
    }

    @Override
    protected Class<RaceParticipation> getEntityClass() {
        return RaceParticipation.class;
    }
}
