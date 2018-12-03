package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.service.facade.base.BaseEntityService;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

/**
 * @author Adel Chakouri
 */
public interface RaceParticipationService extends BaseEntityService<RaceParticipation> {
    List<RaceParticipation> participateInWorldChampionship(Date date, String location, List<Pair<CarSetup, Driver>> setups);

    List<RaceParticipation> findByRaceId(Long id);
}