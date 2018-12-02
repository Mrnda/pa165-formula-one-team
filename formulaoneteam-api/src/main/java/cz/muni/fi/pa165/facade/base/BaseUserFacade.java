package cz.muni.fi.pa165.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.User;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseUserFacade<DTO extends BaseDTO, E extends User> extends BaseFacade<DTO, E> {

    void register(DTO dto, String password);

    boolean authenticate(String email, String password);

    DTO findByEmail(String email);

    void delete(String email, String password);
}
