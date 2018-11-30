package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;

import java.util.List;

/**
 * @author Théo Desjardins
 */
public interface ComponentFacade {

    ComponentDTO findByID(long id);

    List<ComponentDTO> getAllComponent();

    void deleteComponent(ComponentDTO componentDTO);

    void addComponent(ComponentDTO componentDTO);

    void updateComponent(ComponentDTO componentDTO);
}
