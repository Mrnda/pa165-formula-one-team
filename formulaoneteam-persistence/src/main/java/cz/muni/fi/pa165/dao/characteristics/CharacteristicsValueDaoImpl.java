package cz.muni.fi.pa165.dao.characteristics;

import cz.muni.fi.pa165.entity.CharacteristicsType;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Repository
public class CharacteristicsValueDaoImpl implements CharacteristicsValueDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CharacteristicsValue findById(Long id) {
        return entityManager.find(CharacteristicsValue.class, id);
    }

    @Override
    public void add(CharacteristicsValue entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(CharacteristicsValue entity) {
        entityManager.remove(findById(entity.getId()));
    }

    @Override
    public void update(CharacteristicsValue entity) {
        entityManager.merge(entity);
    }

    @Override
    public List<CharacteristicsValue> findAll() {
        return entityManager.createQuery("select characteristics from CharacteristicsValue characteristics", CharacteristicsValue.class)
                .getResultList();
    }

    @Override
    public List<CharacteristicsValue> findCharacteristicValuesByType(CharacteristicsType type) {
        return entityManager.createQuery("select characteristics from CharacteristicsValue characteristics where characteristics.type = :type", CharacteristicsValue.class)
                .setParameter("type", type)
                .getResultList();
    }
}
