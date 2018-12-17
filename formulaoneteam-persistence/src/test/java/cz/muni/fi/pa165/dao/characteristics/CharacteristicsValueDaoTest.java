package cz.muni.fi.pa165.dao.characteristics;

import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Adel Chakouri
 */
public class CharacteristicsValueDaoTest extends BaseTest {

    @Inject
    private CharacteristicsValueDao characteristicsValueDao;

    @Test
    public void addCharacteristics_withValidData_isPersisted() {
        //given
        CharacteristicsType type = CharacteristicsType.AGGRESSIVITY;
        CharacteristicsValue characteristicsValue = createCharacteristics(type);

        //when
        characteristicsValueDao.add(characteristicsValue);

        //then
        assertNotNull(characteristicsValueDao.findById(characteristicsValue.getId()));
    }

    @Test
    public void deleteCharacteristics_withValidData_isPersisted() {
        //given
        CharacteristicsType type = CharacteristicsType.AGGRESSIVITY;
        CharacteristicsValue characteristicsValue = createCharacteristics(type);

        //when
        characteristicsValueDao.add(characteristicsValue);
        characteristicsValueDao.delete(characteristicsValue.getId());

        //then
        assertEquals(0, characteristicsValueDao.findCharacteristicValuesByType(type).size());
    }

    @Test
    public void updateCharacteristics() {
        //given
        CharacteristicsType newType = CharacteristicsType.PATIENCE;
        CharacteristicsValue characteristicsValue = createCharacteristics(CharacteristicsType.AGGRESSIVITY);

        //when
        characteristicsValueDao.add(characteristicsValue);
        characteristicsValue.setType(newType);
        characteristicsValueDao.update(characteristicsValue);

        //then
        assertEquals(newType, characteristicsValueDao.findById(characteristicsValue.getId()).getType());
    }

    @Test
    public void addTwoCharacteristics() {
        //given
        CharacteristicsValue characteristics1 = createCharacteristics(CharacteristicsType.AGGRESSIVITY);
        CharacteristicsValue characteristics2 = createCharacteristics(CharacteristicsType.PATIENCE);

        //when
        characteristicsValueDao.add(characteristics1);
        characteristicsValueDao.add(characteristics2);

        //then
        List<CharacteristicsValue> result = characteristicsValueDao.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(characteristics1));
        assertTrue(result.contains(characteristics2));
    }
}
