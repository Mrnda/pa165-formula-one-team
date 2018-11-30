package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;

import java.util.List;

/**
 * @author Adel Chakouri
 */

public interface RaceParticipationFacade {

    RaceParticipationDTO findRaceParticipationById(long id);

    void deleteRaceParticipation(RaceParticipationDTO raceParticipationDTO);

    void addRaceParticipation(RaceParticipationDTO raceParticipationDTO);

    void updateRaceParticipation(RaceParticipationDTO raceParticipationDTO);

    List<RaceParticipationDTO> getAllRaceParticipation();
}
