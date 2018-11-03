package cz.muni.fi.pa165.dao.Race;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.entity.Race;
import org.springframework.stereotype.Repository;

/**
 * @author Adel Chakouri
 */
@Repository
public class RaceDaoImpl implements RaceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Race findById(Long id) {
        return entityManager.find(Race.class, id);
    }

    @Override
    public void add(Race race) {
        entityManager.persist(race);
    }

    @Override
    public void update(Race race) {
        entityManager.merge(race);
    }

    @Override
    public void delete(Race race) {
        entityManager.remove(race);
    }

    @Override
    public List<Race> findAll() {
        return entityManager
                .createQuery("select r from Race r", Race.class)
                .getResultList();
    }
}
