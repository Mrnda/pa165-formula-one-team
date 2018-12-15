package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.base.BaseFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public abstract class BaseCrudController<
        Facade extends BaseFacade<DTO, Entity>,
        DTO extends BaseDTO,
        Entity extends BaseEntity> {

    @Inject
    protected Facade facade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.ok(facade.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> findById(@PathVariable long id) {
        try {
            return ok(facade.findById(id));
        } catch (EntityNotFoundException e) {
            return notFound();
        }
    }

    protected ResponseEntity<Object> ok() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity<Object> accepted() {
        return ResponseEntity.accepted().build();
    }

    protected ResponseEntity<DTO> ok(DTO dto) {
        return ResponseEntity.ok(dto);
    }

    protected ResponseEntity<DTO> notFound() {
        return ResponseEntity.notFound().build();
    }

    protected ResponseEntity<DTO> unprocessableEntity() {
        return ResponseEntity.unprocessableEntity().build();
    }

    protected ResponseEntity<DTO> noContent() {
        return ResponseEntity.noContent().build();
    }
}
