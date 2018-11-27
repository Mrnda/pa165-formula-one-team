package cz.muni.fi.pa165.service;
import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.entity.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Théo Desjardins
 */
public class ComponentServiceImpl implements ComponentService {

    @Inject
    private ComponentDao componentDao;

    @Override
    public Component findById(long id) {
        return componentDao.findById(id);
    }

    @Override
    public void add(Component component) {
        componentDao.add(component);
    }

    @Override
    public void update(Component component) {
        componentDao.update(component);
    }

    @Override
    public void delete(Component component) {
        componentDao.delete(component);
    }

    @Override
    public List<Component> getAllComponent() {
        return componentDao.findAll();
    }
}
