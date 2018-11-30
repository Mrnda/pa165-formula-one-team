package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.RaceDTO;

import java.util.List;

/**
 * @author Adel Chakouri
 */

public interface RaceFacade {

    RaceDTO findRaceByID(long id);

    List<RaceDTO> getAllRaces();

    void deleteRace(RaceDTO raceDTO);

    void addRace(RaceDTO raceDTO);

    void updateRace(RaceDTO raceDTO);
}
