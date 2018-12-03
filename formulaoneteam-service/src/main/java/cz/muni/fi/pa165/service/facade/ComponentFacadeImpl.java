package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ComponentService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Théo Desjardins
 */
public class ComponentFacadeImpl implements ComponentFacade {

    @Inject
    private ComponentService componentService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public ComponentDTO findByID(long id) {
        Component component = componentService.findById(id);
        return (component == null) ? null : beanMappingService.mapTo(component, ComponentDTO.class);
    }

    @Override
    public List<ComponentDTO> getAllComponent() {
        List<Component> allComponentEntities = componentService.getAll();
        return beanMappingService.mapTo(allComponentEntities, ComponentDTO.class);
    }

    @Override
    public void deleteComponent(ComponentDTO componentDTO) {
        if (componentDTO == null) throw new IllegalArgumentException("null componentDTO, cannot delete");

        componentService.remove(beanMappingService.mapTo(componentDTO, Component.class));
    }

    @Override
    public void addComponent(ComponentDTO componentDTO) {
        if (componentDTO == null) throw new IllegalArgumentException("null componentDTO, cannot add");

        Component component = beanMappingService.mapTo(componentDTO, Component.class);
        componentService.add(component);
    }

    @Override
    public void updateComponent(ComponentDTO componentDTO) {
        if (componentDTO == null) throw new IllegalArgumentException("null componentDTO, cannot update");
        componentService.update(beanMappingService.mapTo(componentDTO, Component.class));
    }
}
