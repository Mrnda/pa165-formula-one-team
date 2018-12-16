package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.dto.AuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.facade.base.BaseUserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseUserController<
        Facade extends BaseUserFacade<DTO, Entity>,
        DTO extends UserDTO,
        Entity extends User> extends BaseCrudController<Facade, DTO, Entity> {

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody DTO dto) {
        facade.register(dto, dto.getPassword());
        return ok();
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity authenticate(@RequestBody AuthenticateDTO authenticateDTO) {
        if (facade.authenticate(authenticateDTO.getEmail(), authenticateDTO.getPassword())) {
            return ok();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody AuthenticateDTO authenticateDTO) {
        facade.delete(authenticateDTO.getEmail(), authenticateDTO.getPassword());
        return ok();
    }
}
