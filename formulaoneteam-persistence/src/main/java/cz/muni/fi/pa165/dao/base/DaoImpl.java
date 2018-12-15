package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of general Dao operations.
 *
 * @param <T> Generic parameter for the Dao's entity type.
 * @author Ivan Dendis
 */
@Transactional
public abstract class DaoImpl<T extends BaseEntity> implements Dao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T findById(Long id) {
        return entityManager.find(getClassType(), id);
    }

    @Override
    public void add(@Nullable T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(T entity) {
        T managed = findById(entity.getId());

        entityManager.detach(managed);

        BeanUtils.copyProperties(entity, managed);

        entityManager.merge(managed);
    }

    @Override
    public List<T> findAll() {
        Class<T> classType = getClassType();

        return entityManager
                .createQuery("FROM " + classType.getName(), classType)
                .getResultList();
    }

    protected abstract Class<T> getClassType();
}
