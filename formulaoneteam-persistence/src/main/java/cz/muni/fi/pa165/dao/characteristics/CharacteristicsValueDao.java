package cz.muni.fi.pa165.dao.characteristics;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.CharacteristicsType;
import cz.muni.fi.pa165.entity.CharacteristicsValue;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public interface CharacteristicsValueDao extends Dao<CharacteristicsValue> {
    List<CharacteristicsValue> findCharacteristicValuesByType(CharacteristicsType type);
}
