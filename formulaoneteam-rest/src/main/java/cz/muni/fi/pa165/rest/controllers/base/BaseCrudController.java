package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.base.BaseFacade;
import cz.muni.fi.pa165.rest.controllers.ApiError;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    private Logger logger = LoggerFactory.getLogger(BaseCrudController.class);

    @Inject
    protected Facade facade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.ok(facade.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DTO> findById(@PathVariable long id) {
        return ok(facade.findById(id));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        logger.error("Entity not found", exception);
        return notFound();
    }

    @ExceptionHandler({FormulaOneTeamException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(FormulaOneTeamException exception) {
        logger.error("Validation exception", exception);
        final ApiError error = new ApiError(exception.getMessage());
        return unprocessableEntity(error);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleNotHandledException(Exception exception) {
        logger.error("Unhandled exception", exception);
        final ApiError error = new ApiError(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
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

    protected ResponseEntity<Object> notFound() {
        return ResponseEntity.notFound().build();
    }

    protected ResponseEntity<Object> unprocessableEntity(ApiError error) {
        return ResponseEntity.unprocessableEntity().body(error);
    }

}
