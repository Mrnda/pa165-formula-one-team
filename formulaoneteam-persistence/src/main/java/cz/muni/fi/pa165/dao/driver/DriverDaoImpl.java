package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.entity.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static cz.muni.fi.pa165.entity.base.User.EMAIL_FIELD;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Repository
public class DriverDaoImpl implements DriverDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Driver findById(Long id) {
        return entityManager.find(Driver.class, id);
    }

    @Override
    public void add(Driver driver) {
        checkValidity(driver);
        entityManager.persist(driver);
    }

    @Override
    public void delete(Driver driver) {
        checkValidity(driver);
        entityManager.remove(findById(driver.getId()));
    }

    @Override
    public void update(Driver driver) {
        checkValidity(driver);
        entityManager.merge(driver);
    }

    @Override
    public List<Driver> findAll() {
        return entityManager.createQuery("select driver from Driver driver", Driver.class)
                .getResultList();
    }

    @Override
    public Driver findByEmail(String email) {
        try {
            return entityManager
                    .createQuery("select driver from Driver driver where email = :email", Driver.class)
                    .setParameter(EMAIL_FIELD, email)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    private void checkValidity(Driver entity) {
        if(entity == null || !entity.isConfigured()) {
            throw new IllegalArgumentException("Driver is null or not configured");
        }
    }
}
