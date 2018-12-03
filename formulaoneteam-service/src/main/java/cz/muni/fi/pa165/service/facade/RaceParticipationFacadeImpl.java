package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.service.RaceParticipationService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class RaceParticipationFacadeImpl
        extends BaseEntityFacadeImpl<RaceParticipationDTO, RaceParticipation, RaceParticipationService>
        implements RaceParticipationFacade {

    @Override
    protected Class<RaceParticipationDTO> getDtoClass() {
        return RaceParticipationDTO.class;
    }

    @Override
    protected Class<RaceParticipation> getEntityClass() {
        return RaceParticipation.class;
    }

    @Override
    public List<RaceParticipationDTO> participateInWorldChampionship(WorldChampionshipSetupDTO worldChampionshipSetupDTO) {
        if (worldChampionshipSetupDTO == null)
            throw new IllegalArgumentException("worldChampionshipSetupDTO can't be null");

        final Pair<CarSetup, Driver> firstDriverCarSetupPair =
                Pair.of(beanMappingService.mapTo(worldChampionshipSetupDTO.getFirstCarSetup(), CarSetup.class),
                        beanMappingService.mapTo(worldChampionshipSetupDTO.getFirstDriver(), Driver.class));

        final Pair<CarSetup, Driver> secondDriverCarSetupPair =
                Pair.of(beanMappingService.mapTo(worldChampionshipSetupDTO.getSecondCarSetup(), CarSetup.class),
                        beanMappingService.mapTo(worldChampionshipSetupDTO.getSecondDriver(), Driver.class));

        final List<RaceParticipation> raceParticipations =
                service.participateInWorldChampionship(
                        worldChampionshipSetupDTO.getDate(),
                        worldChampionshipSetupDTO.getLocation(),
                        Arrays.asList(firstDriverCarSetupPair, secondDriverCarSetupPair));

        return beanMappingService.mapTo(raceParticipations, RaceParticipationDTO.class);
    }
}

