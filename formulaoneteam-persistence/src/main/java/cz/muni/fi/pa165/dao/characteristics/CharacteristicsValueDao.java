package cz.muni.fi.pa165.dao.characteristics;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.entity.CharacteristicsValue;

import java.util.List;

/**
 * Data Access Object interface for CharacteristicsValue entity.
 *
 * @author mrnda (Michal Mrnuštík)
 */
public interface CharacteristicsValueDao extends Dao<CharacteristicsValue> {

    /**
     * Finds all CharacteristicsValue entities given by {@code type}.
     * @param type Characteristics type to be found.
     * @return list of found entities.
     */
    List<CharacteristicsValue> findCharacteristicValuesByType(CharacteristicsType type);
}
