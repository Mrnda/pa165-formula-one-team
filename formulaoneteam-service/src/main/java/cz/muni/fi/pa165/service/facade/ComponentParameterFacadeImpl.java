package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.facade.ComponentParameterFacade;
import cz.muni.fi.pa165.service.ComponentParameterService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Dendis
 */
@Service
@Transactional
public class ComponentParameterFacadeImpl
        extends BaseEntityFacadeImpl<ComponentParameterDTO, ComponentParameter, ComponentParameterService>
        implements ComponentParameterFacade {

    @Override
    protected Class<ComponentParameterDTO> getDtoClass() {
        return ComponentParameterDTO.class;
    }

    @Override
    protected Class<ComponentParameter> getEntityClass() {
        return ComponentParameter.class;
    }
}
