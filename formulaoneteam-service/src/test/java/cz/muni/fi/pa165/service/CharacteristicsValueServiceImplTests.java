package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.characteristics.CharacteristicsValueDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;


@ContextConfiguration(classes = ServiceConfiguration.class)
public class CharacteristicsValueServiceImplTests extends AbstractTestNGSpringContextTests {
    @Mock
    CharacteristicsValueDao characteristicsValueDaoMock;

    @InjectMocks
    CharacteristicsValueServiceImpl characteristicsValueService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private CharacteristicsValue testingValue;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp(){
        testingValue = createCharacteristicsValue(CharacteristicsType.AGGRESIVITY, 100);
    }

    @Test
    public void addValue_withValidValues_valueAddCalled(){
        //Given

        //When
        characteristicsValueService.add(testingValue);

        //Then
        verify(characteristicsValueDaoMock, times(1)).add(testingValue);
    }

    @Test
    public void findById_withExistingValue_valueReturned(){
        //Given
        when(characteristicsValueDaoMock.findById(testingValue.getId())).thenReturn(testingValue);

        //When
        CharacteristicsValue value = characteristicsValueService.findById(testingValue.getId());

        //Then
        assertEquals(testingValue, value);
    }

    @Test
    public void updateValue_withValidValues_valueUpdated(){
        //Given

        //When
        characteristicsValueService.update(testingValue);

        //Then
        verify(characteristicsValueDaoMock, times(1)).update(testingValue);
    }

    @Test
    public void removeValue_withValidValues_valueDeleted(){
        //Given

        //When
        characteristicsValueService.delete(testingValue);

        //Then
        verify(characteristicsValueDaoMock, times(1)).delete(testingValue);
    }

    private CharacteristicsValue createCharacteristicsValue(CharacteristicsType type, double value) {
        return new CharacteristicsValue(type, value);
    }
}
