package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPONENT)
public class ComponentController extends BaseEntityController<ComponentFacade, ComponentDTO, Component> {

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentDTO> addParameter(@PathVariable long id, @RequestBody ComponentParameterDTO parameter) {
        facade.addParameter(id, parameter);
        return ok(facade.findById(id));
    }

    @RequestMapping(value = "/parameters/{parameterId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateParameter(@PathVariable long parameterId, @RequestBody ComponentParameterDTO parameter) {
        facade.updateParameter(parameterId, parameter);
        return ok();
    }

    @RequestMapping(value = "/parameters/{componentId}/{parameterId}", method = RequestMethod.DELETE)
    public ResponseEntity<ComponentDTO> deleteParameter(@PathVariable long componentId, @PathVariable long parameterId) {
        facade.removeParameter(componentId, parameterId);
        return ok(facade.findById(componentId));
    }
}
