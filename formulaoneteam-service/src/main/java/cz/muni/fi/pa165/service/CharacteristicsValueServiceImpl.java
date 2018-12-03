package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.characteristics.CharacteristicsValueDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
public class CharacteristicsValueServiceImpl
        extends BaseEntityServiceImpl<CharacteristicsValue, CharacteristicsValueDao>
        implements CharacteristicsValueService {

    @Override
    public void validateEntity(CharacteristicsValue entity) {
        if (entity == null) {
            throw new FormulaOneTeamException("CharacteristicsValue is null.");
        }
    }
}
