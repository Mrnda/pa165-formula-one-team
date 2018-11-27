package cz.muni.fi.pa165.service;
import cz.muni.fi.pa165.entity.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Théo Desjardins
 */
@Service
public interface ComponentService {

    Component findById(long id);

    void add(Component component );

    void update(Component component);

    void delete(Component component );

    List<Component> getAllComponent();
}
