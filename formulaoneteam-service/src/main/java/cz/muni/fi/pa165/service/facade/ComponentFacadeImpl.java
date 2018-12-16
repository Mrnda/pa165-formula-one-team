package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
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
    public void remove(long id) {
        final Component component = service.findById(id);
        if (carSetupService.getAll().stream().anyMatch(carSetup -> carSetup.getComponents().contains(component))) {
            throw new FormulaOneTeamException("Component can't be deleted when is part of existing car setup.");
        }
        super.remove(id);
    }

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
        if (component.getParameters().contains(parameterEntity))
            throw new FormulaOneTeamException("This component already contains parameter with name: " + parameterEntity.getName());
        component.addParameter(parameterEntity);
        service.update(component);
    }

    @Override
    public void updateParameter(long id, ComponentParameterDTO parameterDTO) {
        final ComponentParameter parameter = beanMappingService.mapTo(parameterDTO, ComponentParameter.class);
        parameter.setId(id);
        componentParameterService.update(parameter);
    }

    @Override
    public void removeParameter(long componentId, long parameterId) {
        ComponentParameter componentParameter = componentParameterService.findById(parameterId);
        Component component = service.findById(componentId);
        component.removeParameter(componentParameter);
        service.update(component);
        componentParameterService.remove(parameterId);
    }
}
