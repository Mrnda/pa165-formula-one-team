package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.service.ComponentParameterService;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author Théo Desjardins
 */
@Service
@Transactional
public class ComponentFacadeImpl
        extends BaseEntityFacadeImpl<ComponentDTO, Component, ComponentService>
        implements ComponentFacade {

    @Inject
    private ComponentParameterService componentParameterService;

    @Override
    protected Class<ComponentDTO> getDtoClass() {
        return ComponentDTO.class;
    }

    @Override
    protected Class<Component> getEntityClass() {
        return Component.class;
    }

    @Override
    public void addParameter(long componentId, ComponentParameterDTO parameter) {
        ComponentParameter parameterEntity
                = componentParameterService.add(beanMappingService.mapTo(parameter, ComponentParameter.class));
        Component component = service.findById(componentId);
        component.addParameter(parameterEntity);
        service.update(component);
    }

    @Override
    public void updateParameter(ComponentParameterDTO parameterDTO) {
        componentParameterService.update(beanMappingService.mapTo(parameterDTO, ComponentParameter.class));
    }

    @Override
    public void removeParameter(long componentId, ComponentParameterDTO parameter) {
        ComponentParameter componentParameter = componentParameterService.findById(parameter.getId());
        Component component = service.findById(componentId);
        component.removeParameter(componentParameter);
        service.update(component);
        componentParameterService.remove(parameter.getId());
    }
}
