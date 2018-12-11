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

public abstract class BaseCrudController<TFacade extends BaseEntityFacade<TDTO, TEntity>, TDTO extends BaseDTO, TEntity extends BaseEntity> {

    @Inject
    TFacade facade;

    @RequestMapping
    public @ResponseBody List<TDTO> getAll() {
        return facade.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TDTO> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(facade.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable long id) {
        try {
            TDTO dto = facade.findById(id);
            facade.remove(dto);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TDTO> add(@RequestBody TDTO dto) {
        try {
            TDTO createdDTO = facade.add(dto);
            return ResponseEntity.ok(createdDTO);
        } catch (FormulaOneTeamException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TDTO> update(@RequestBody TDTO updatedDto) {
        try {
            facade.update(updatedDto);
            return ResponseEntity.ok(facade.findById(updatedDto.getId()));
        } catch (FormulaOneTeamException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
