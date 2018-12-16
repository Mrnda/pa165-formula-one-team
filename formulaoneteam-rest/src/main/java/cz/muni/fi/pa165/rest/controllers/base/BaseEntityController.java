package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public abstract class BaseEntityController<
        Facade extends BaseEntityFacade<DTO, Entity>,
        DTO extends BaseDTO,
        Entity extends BaseEntity> extends BaseCrudController<Facade, DTO, Entity> {

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity remove(@PathVariable long id) {
        facade.remove(id);
        return accepted();
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> add(@RequestBody DTO dto) {
        long id = facade.add(dto);
        return ok(facade.findById(id));
    }

    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody DTO updatedDto, @PathVariable long id) {
        facade.update(updatedDto, id);
        return ok();
    }
}
