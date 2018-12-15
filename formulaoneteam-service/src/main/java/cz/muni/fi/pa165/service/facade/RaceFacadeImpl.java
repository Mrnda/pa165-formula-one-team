package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class RaceFacadeImpl extends BaseEntityFacadeImpl<RaceDTO, Race, RaceService> implements RaceFacade {

    @Override
    protected Class<RaceDTO> getDtoClass() {
        return RaceDTO.class;
    }

    @Override
    protected Class<Race> getEntityClass() {
        return Race.class;
    }
}
