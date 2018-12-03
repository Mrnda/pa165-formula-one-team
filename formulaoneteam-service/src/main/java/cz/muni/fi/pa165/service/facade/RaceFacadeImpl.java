package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceDTO;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.RaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class RaceFacadeImpl implements RaceFacade {

    @Inject
    private RaceService raceService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public RaceDTO findRaceByID(long id) {
        Race race = raceService.findById(id);
        return (race == null) ? null : beanMappingService.mapTo(race, RaceDTO.class);
    }

    @Override
    public List<RaceDTO> getAllRaces() {
        List<Race> allRacesEntities = raceService.getAll();
        return beanMappingService.mapTo(allRacesEntities, RaceDTO.class);
    }

    @Override
    public void deleteRace(RaceDTO raceDTO) {
        if (raceDTO == null) throw new IllegalArgumentException("null raceDTO, cannot delete");

        raceService.remove(beanMappingService.mapTo(raceDTO, Race.class));
    }

    @Override
    public void addRace(RaceDTO raceDTO) {
        if (raceDTO == null) throw new IllegalArgumentException("null raceDTO, cannot add");

        Race race = beanMappingService.mapTo(raceDTO, Race.class);
        raceService.add(race);
    }

    @Override
    public void updateRace(RaceDTO raceDTO) {
        if (raceDTO == null) throw new IllegalArgumentException("null raceDTO, cannot update");
        raceService.update(beanMappingService.mapTo(raceDTO, Race.class));
    }
}
