package cz.muni.fi.pa165.dao.characteristics;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.entity.CharacteristicsType;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.AssertJUnit;

import javax.inject.Inject;

@ContextConfiguration(classes= AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class CharacteristicsValueDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private CharacteristicsValueDao characteristicsValueDao;

    @Test
    public void addCharacteristics_withValidData_isPersisted() {
        //Given
        CharacteristicsValue characteristicsValue = new CharacteristicsValue();
        characteristicsValue.setValue(100.0);
        characteristicsValue.setType(CharacteristicsType.AGGRESIVITY);

        //Then
        characteristicsValueDao.add(characteristicsValue);

        //That
        AssertJUnit.assertNotNull(characteristicsValueDao.findById(characteristicsValue.getId()));
    }
}
