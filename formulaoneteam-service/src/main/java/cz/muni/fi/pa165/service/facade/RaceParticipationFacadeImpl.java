package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.RaceParticipationService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @author Adel Chakouri
 */

@Service
@Transactional
public class RaceParticipationFacadeImpl implements RaceParticipationFacade {

    @Inject
    private RaceParticipationService raceParticipationService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public RaceParticipationDTO findRaceParticipationById(long id) {
        RaceParticipation raceParticipation = raceParticipationService.findById(id);
        return (raceParticipation == null) ? null : beanMappingService.mapTo(raceParticipation, RaceParticipationDTO.class);
    }

    @Override
    public void deleteRaceParticipation(RaceParticipationDTO raceParticipationDTO) {
        if (raceParticipationDTO == null) throw new IllegalArgumentException("null raceParticipationDTO, cannot delete");
        raceParticipationService.remove(beanMappingService.mapTo(raceParticipationDTO, RaceParticipation.class));
    }

    @Override
    public void addRaceParticipation(RaceParticipationDTO raceParticipationDTO) {
        if (raceParticipationDTO == null) throw new IllegalArgumentException("null raceParticipationDTO, cannot add");
        RaceParticipation raceParticipation = beanMappingService.mapTo(raceParticipationDTO, RaceParticipation.class);
        raceParticipationService.add(raceParticipation);
    }

    @Override
    public void updateRaceParticipation(RaceParticipationDTO raceParticipationDTO) {
        if (raceParticipationDTO == null) throw new IllegalArgumentException("null raceParticipationDTO, cannot update");
        raceParticipationService.update(beanMappingService.mapTo(raceParticipationDTO, RaceParticipation.class));
    }

    @Override
    public List<RaceParticipationDTO> getAllRaceParticipation() {
        List<RaceParticipation> allRacesEntities = raceParticipationService.getAll();
        return beanMappingService.mapTo(allRacesEntities, RaceParticipationDTO.class);
    }

    @Override
    public List<RaceParticipationDTO> participateInWorldChampionship(WorldChampionshipSetupDTO worldChampionshipSetupDTO) {
        if (worldChampionshipSetupDTO == null)
            throw new IllegalArgumentException("worldChampionshipSetupDTO can't be null");
        final Pair<CarSetup, Driver> firstDriverCarSetupPair = Pair.of(beanMappingService.mapTo(worldChampionshipSetupDTO.getFirstCarSetup(), CarSetup.class),
                beanMappingService.mapTo(worldChampionshipSetupDTO.getFirstDriver(), Driver.class));
        final Pair<CarSetup, Driver> secondDriverCarSetupPair = Pair.of(beanMappingService.mapTo(worldChampionshipSetupDTO.getSecondCarSetup(), CarSetup.class),
                beanMappingService.mapTo(worldChampionshipSetupDTO.getSecondDriver(), Driver.class));
        final List<RaceParticipation> raceParticipations = raceParticipationService.participateInWorldChampionship(worldChampionshipSetupDTO.getDate(),
                worldChampionshipSetupDTO.getLocation(),
                Arrays.asList(firstDriverCarSetupPair, secondDriverCarSetupPair));
        return beanMappingService.mapTo(raceParticipations, RaceParticipationDTO.class);
    }
}

