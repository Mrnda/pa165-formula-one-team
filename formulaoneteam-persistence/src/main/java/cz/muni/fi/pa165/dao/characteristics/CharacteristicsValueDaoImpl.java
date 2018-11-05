package cz.muni.fi.pa165.dao.characteristics;

import cz.muni.fi.pa165.dao.base.DaoImpl;
import cz.muni.fi.pa165.entity.CharacteristicsType;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cz.muni.fi.pa165.entity.CharacteristicsValue.TYPE_FIELD;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Repository
public class CharacteristicsValueDaoImpl extends DaoImpl<CharacteristicsValue> implements CharacteristicsValueDao {

    @Override
    protected Class<CharacteristicsValue> getClassType() {
        return CharacteristicsValue.class;
    }

    @Override
    protected void validateEntity(CharacteristicsValue value) {
        if (value == null) {
            throw new IllegalArgumentException("CaracteristicsValue is null.");
        }
    }

    @Override
    public List<CharacteristicsValue> findCharacteristicValuesByType(CharacteristicsType type) {
        return entityManager.createQuery("select characteristics from CharacteristicsValue characteristics where characteristics.type = :type", CharacteristicsValue.class)
                .setParameter(TYPE_FIELD, type)
                .getResultList();
    }
}
