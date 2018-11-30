package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.RaceParticipationService;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
}

