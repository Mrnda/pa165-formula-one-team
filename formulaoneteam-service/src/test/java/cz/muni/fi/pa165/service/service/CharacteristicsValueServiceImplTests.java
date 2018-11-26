package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.characteristics.CharacteristicsValueDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.service.CharacteristicsValueServiceImpl;
import cz.muni.fi.pa165.service.base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class CharacteristicsValueServiceImplTests extends BaseTest {

    private CharacteristicsValue testingValue;

    @Mock
    private CharacteristicsValueDao characteristicsValueDaoMock;

    @InjectMocks
    private CharacteristicsValueServiceImpl characteristicsValueService;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        testingValue = new CharacteristicsValue(CharacteristicsType.AGGRESIVITY, 100, null);
    }

    @Test
    public void addValue_withValidValues_valueAddCalled() {
        //When
        characteristicsValueService.add(testingValue);

        //Then
        verify(characteristicsValueDaoMock, times(1)).add(testingValue);
    }

    @Test
    public void findById_withExistingValue_valueReturned() {
        //Given
        when(characteristicsValueDaoMock.findById(testingValue.getId())).thenReturn(testingValue);

        //When
        CharacteristicsValue value = characteristicsValueService.findById(testingValue.getId());

        //Then
        assertEquals(testingValue, value);
    }

    @Test
    public void updateValue_withValidValues_valueUpdated() {
        //When
        characteristicsValueService.update(testingValue);

        //Then
        verify(characteristicsValueDaoMock, times(1)).update(testingValue);
    }

    @Test
    public void removeValue_withValidValues_valueDeleted() {
        //When
        characteristicsValueService.delete(testingValue);

        //Then
        verify(characteristicsValueDaoMock, times(1)).delete(testingValue);
    }
}
