package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Théo Desjardins
 */
@Service
@Transactional
public class ComponentFacadeImpl
        extends BaseEntityFacadeImpl<ComponentDTO, Component, ComponentService>
        implements ComponentFacade {

    @Override
    protected Class<ComponentDTO> getDtoClass() {
        return ComponentDTO.class;
    }

    @Override
    protected Class<Component> getEntityClass() {
        return Component.class;
    }
}
