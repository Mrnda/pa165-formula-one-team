package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.facade.ComponentParameterFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPONENT_PARAM)
public class ComponentParameterController
        extends BaseEntityController<ComponentParameterFacade, ComponentParameterDTO, ComponentParameter> {
}
