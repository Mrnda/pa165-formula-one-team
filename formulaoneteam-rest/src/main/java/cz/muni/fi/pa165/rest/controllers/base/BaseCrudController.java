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

    @RequestMapping
    public @ResponseBody
    List<DTO> getAll() {
        return facade.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(facade.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable long id) {
        try {
            DTO dto = facade.findById(id);
            facade.remove(dto);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedEntityIdDTO> add(@RequestBody DTO dto) {
        try {
            long id = facade.add(dto);
            return ResponseEntity.ok(new CreatedEntityIdDTO(id));
        } catch (FormulaOneTeamException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> update(@RequestBody DTO updatedDto) {
        try {
            facade.update(updatedDto);
            return ResponseEntity.ok(facade.findById(updatedDto.getId()));
        } catch (FormulaOneTeamException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
