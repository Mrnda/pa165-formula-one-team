package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.characteristics.CharacteristicsValueDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
public class CharacteristicsValueServiceImpl implements CharacteristicsValueService {

    @Inject
    private CharacteristicsValueDao characteristicsValueDao;

    @Override
    public CharacteristicsValue findById(long id) {
        return characteristicsValueDao.findById(id);
    }

    @Override
    public void update(CharacteristicsValue value) {
        characteristicsValueDao.update(value);
    }

    @Override
    public void add(CharacteristicsValue value) {
        characteristicsValueDao.add(value);
    }

    @Override
    public void delete(CharacteristicsValue value) {
        characteristicsValueDao.delete(value);
    }
}
