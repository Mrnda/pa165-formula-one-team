package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */

public abstract class BaseCrudController<Facade extends BaseEntityFacade<DTO, Entity>, DTO extends BaseDTO, Entity extends BaseEntity> {

    @Inject
    protected Facade facade;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DTO> getAll() {
        return facade.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> findById(@PathVariable long id) {
        try {
            return ok(facade.findById(id));
        } catch (EntityNotFoundException e) {
            return notFound();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable long id) {
        try {
            DTO dto = facade.findById(id);
            facade.remove(dto);
            return ok();
        } catch (EntityNotFoundException e) {
            return notFound();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> add(@RequestBody DTO dto) {
        try {
            long id = facade.add(dto);
            return ok(facade.findById(id));
        } catch (FormulaOneTeamException e) {
            return unprocessableEntity();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody DTO updatedDto) {
        try {
            facade.update(updatedDto);
            return ok();
        } catch (FormulaOneTeamException e) {
            return unprocessableEntity();
        }
    }

    private ResponseEntity<Object> ok() {
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<DTO> ok(DTO dto) {
        return ResponseEntity.ok(dto);
    }

    private ResponseEntity<DTO> notFound() {
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<DTO> unprocessableEntity() {
        return ResponseEntity.unprocessableEntity().build();
    }
}
