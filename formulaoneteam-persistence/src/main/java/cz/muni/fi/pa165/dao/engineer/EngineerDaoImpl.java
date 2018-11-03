package cz.muni.fi.pa165.dao.engineer;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.entity.Manager;
import static cz.muni.fi.pa165.entity.base.User.EMAIL_FIELD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.lang.Nullable;

/**
 * @author Ivan Dendis
 */
public class EngineerDaoImpl implements Dao<Engineer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Engineer findById(Long id) {
        return entityManager.find(Engineer.class, id);
    }

    @Override
    public void add(@Nullable Engineer engineer) {
        validateEngineer(engineer);

        entityManager.persist(engineer);
    }

    @Override
    public void delete(@Nullable Engineer engineer) {
        validateEngineer(engineer);

        entityManager.remove(engineer);
    }

    @Override
    public void update(@Nullable Engineer engineer) {
        validateEngineer(engineer);

        entityManager.merge(engineer);
    }

    @Override
    public List<Engineer> findAll() {
        return entityManager
                .createQuery("select engineer from Engineer engineer", Engineer.class)
                .getResultList();
    }

    @Nullable
    public Manager findByEmail(String email) {
        try {
            return entityManager
                    .createQuery("select engineer from Engineer engineer where email = :email", Manager.class)
                    .setParameter(EMAIL_FIELD, email)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    private void validateEngineer(Engineer engineer) {
        if (engineer == null || !engineer.isConfigured()) {
            throw new IllegalArgumentException("Engineer is null or not configured");
        }
    }
}
