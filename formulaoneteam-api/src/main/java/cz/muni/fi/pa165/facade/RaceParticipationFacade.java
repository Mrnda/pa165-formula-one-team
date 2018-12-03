package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;

import java.util.Collection;
import java.util.List;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;

/**
 * @author Adel Chakouri
 */

public interface RaceParticipationFacade extends BaseEntityFacade<RaceParticipationDTO, RaceParticipation> {
    List<RaceParticipationDTO> participateInWorldChampionship(WorldChampionshipSetupDTO worldChampionshipSetupDTO);
}
