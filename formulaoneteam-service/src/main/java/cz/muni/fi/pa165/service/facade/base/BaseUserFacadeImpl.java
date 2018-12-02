package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.facade.base.BaseUserFacade;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseUserFacadeImpl<DTO extends BaseDTO, E extends User, S extends BaseUserService<E>>
        extends BaseFacadeImpl<DTO, E, S>
        implements BaseUserFacade<DTO, E> {

    @Override
    public void register(DTO dto, String password) {
        service.register(beanMappingService.mapTo(dto, getEntityClass()), password);
    }

    @Override
    public boolean authenticate(String email, String password) {
        return service.authenticate(email, password);
    }

    @Override
    public void delete(String email, String password) {
        boolean isAuthenticated = service.authenticate(email, password);

        if (isAuthenticated) {
            service.remove(service.findByEmail(email));
        } else {
            throw new IllegalArgumentException("Cannot authenticate user");
        }
    }

    @Override
    public DTO findByEmail(String email) {
        return beanMappingService.mapTo(service.findByEmail(email), getDtoClass());
    }
}
