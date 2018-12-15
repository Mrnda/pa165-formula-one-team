package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.EngineerDTO;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.facade.EngineerFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseUserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_ENGINEER)
public class EngineerController extends BaseUserController<EngineerFacade, EngineerDTO, Engineer> {
}
