package cz.muni.fi.pa165.dao.componentparameter;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.ComponentParameter;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ivan Dendis
 */
public class ComponentParameterDaoImpl implements Dao<ComponentParameter> {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ComponentParameter findById(Long id) {
        return entityManager.find(ComponentParameter.class, id);
    }

    @Override
    public void add(@Nullable ComponentParameter component) {
        validateComponent(component);

        entityManager.persist(component);
    }

    @Override
    public void delete(@Nullable ComponentParameter component) {
        validateComponent(component);

        entityManager.remove(component);
    }

    @Override
    public void update(@Nullable ComponentParameter component) {
        validateComponent(component);

        entityManager.merge(component);
    }

    @Override
    public List<ComponentParameter> findAll() {
        return entityManager
                .createQuery("select component from ComponentParameter component", ComponentParameter.class)
                .getResultList();
    }

    private void validateComponent(ComponentParameter component) {
        if (component == null || !component.isConfigured()) {
            throw new IllegalArgumentException("Engineer is null or not configured");
        }
    }
}
