package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.facade.base.BaseFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.CharacteristicsValueService;
import cz.muni.fi.pa165.service.ComponentParameterService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseFacadeImpl<DTO extends BaseDTO, E extends BaseEntity, S extends BaseService<E>>
        implements BaseFacade<DTO, E> {

    @Inject
    protected S service;

    @Inject
    protected BeanMappingService beanMappingService;

    @Inject
    protected CharacteristicsValueService characteristicsValueService;

    @Inject
    protected ComponentParameterService componentParameterService;

    @Inject
    protected CarSetupService carSetupService;

    @Override
    public DTO findById(long id) {
        return beanMappingService.mapTo(service.findById(id), getDtoClass());
    }

    @Override
    public List<DTO> getAll() {
        List<E> allEntities = service.getAll();
        return beanMappingService.mapTo(allEntities, getDtoClass());
    }

    protected abstract Class<DTO> getDtoClass();

    protected abstract Class<E> getEntityClass();
}
