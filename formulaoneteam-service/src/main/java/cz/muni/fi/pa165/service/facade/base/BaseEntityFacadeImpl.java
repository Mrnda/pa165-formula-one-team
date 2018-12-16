package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;

import static cz.muni.fi.pa165.entity.base.BaseEntity.NO_ID;

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
    public void remove(long id) {
        if (id == NO_ID) throw new IllegalArgumentException("Invalid id");

        service.remove(id);
    }

    @Override
    public void update(DTO dto, long id) {
        if (dto == null) throw new IllegalArgumentException("null raceDTO, cannot update");

        E entity = beanMappingService.mapTo(dto, getEntityClass());
        entity.setId(id);

        service.update(entity);
    }
}
