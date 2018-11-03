package cz.muni.fi.pa165.dao.carsetup;

import cz.muni.fi.pa165.entity.base.CarSetup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Théo Desjardins
 */

@Repository
public class CarSetupDaoImpl implements CarSetupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CarSetup findById(Long id) {
        return entityManager.find(CarSetup.class, id);
    }

    @Override
    public void add(CarSetup carsetup) {
        entityManager.persist(carsetup);
    }

    @Override
    public void delete(CarSetup carsetup) {
        entityManager.remove(carsetup);
    }

    @Override
    public void update(CarSetup carsetup) {
        entityManager.merge(carsetup);
    }

    @Override
    public List<CarSetup> findAll() {
        return entityManager
                .createQuery("select carsetup from CarSetup carsetup", CarSetup.class)
                .getResultList();
    }
}
