package cz.muni.fi.pa165.dao.component;

import cz.muni.fi.pa165.entity.component.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Théo Desjardins
 */

@Repository
public class ComponentDaoImpl implements ComponentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Component findById(Long id) {
        return entityManager.find(Component.class, id);
    }

    @Override
    public void add(Component component) {
        entityManager.persist(component);
    }

    @Override
    public void delete(Component component) {
        entityManager.remove(component);
    }

    @Override
    public void update(Component component) {
        entityManager.merge(component);
    }

    @Override
    public List<Component> findAll() {
        return entityManager
                .createQuery("select component from Component component", Component.class)
                .getResultList();
    }
}
