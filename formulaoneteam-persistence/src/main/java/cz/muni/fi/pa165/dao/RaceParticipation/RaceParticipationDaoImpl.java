package cz.muni.fi.pa165.dao.RaceParticipation;

import cz.muni.fi.pa165.entity.RaceParticipation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Adel Chakouri
 */
@Repository
public class RaceParticipationDaoImpl implements RaceParticipationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RaceParticipation findById(Long id) {
        return entityManager.find(RaceParticipation.class, id);
    }

    @Override
    public void add(RaceParticipation RacePart) {
        entityManager.persist(RacePart);
    }

    @Override
    public void update(RaceParticipation RacePart) {
        entityManager.merge(RacePart);
    }

    @Override
    public void delete(RaceParticipation RacePart) {
        entityManager.remove(RacePart);
    }

    @Override
    public List<RaceParticipation> findAll() {
        return entityManager
                .createQuery("select r from RaceParticipation r", RaceParticipation.class)
                .getResultList();
    }
}
