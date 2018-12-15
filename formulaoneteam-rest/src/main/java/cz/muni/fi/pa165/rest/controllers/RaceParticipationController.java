package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adel Chakouri
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_RACE_PARTICIPATION)
public class RaceParticipationController
        extends BaseEntityController<RaceParticipationFacade, RaceParticipationDTO, RaceParticipation> {
}
