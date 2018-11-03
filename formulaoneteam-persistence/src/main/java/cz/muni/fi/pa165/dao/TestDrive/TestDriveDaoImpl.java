package cz.muni.fi.pa165.dao.TestDrive;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.TestDrive;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Adel Chakouri
 */
@Repository
public class TestDriveDaoImpl implements TestDriveDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TestDrive findById(Long id) {
        return entityManager.find(TestDrive.class, id);
    }

    @Override
    public void add(TestDrive p) {
        entityManager.persist(p);
    }

    @Override
    public void update(TestDrive p) {
        entityManager.merge(p);
    }

    @Override
    public void delete(TestDrive p) {
        entityManager.remove(p);
    }

    @Override
    public List<TestDrive> findAll() {
        return entityManager
                .createQuery("select r from TestDrive r ", TestDrive.class)
                .getResultList();

    }
}
