package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

