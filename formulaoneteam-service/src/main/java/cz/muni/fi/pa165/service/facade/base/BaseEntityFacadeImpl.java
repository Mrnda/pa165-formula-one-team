package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseEntityFacadeImpl<DTO extends BaseDTO, E extends BaseEntity, S extends BaseEntityService<E>>
        extends BaseFacadeImpl<DTO, E, S>
        implements BaseEntityFacade<DTO, E> {

    @Override
    public long add(DTO dto) {
        if (dto == null) throw new IllegalArgumentException("null DTO, cannot add");
        E entity = service.add(beanMappingService.mapTo(dto, getEntityClass()));
        return entity.getId();
    }

    @Override
    public void remove(DTO dto) {
        if (dto == null) throw new IllegalArgumentException("null DTO, cannot delete");
        service.remove(beanMappingService.mapTo(dto, getEntityClass()));
    }

    @Override
    public void update(DTO dto) {
        if (dto == null) throw new IllegalArgumentException("null raceDTO, cannot update");
        service.update(beanMappingService.mapTo(dto, getEntityClass()));
    }
}
