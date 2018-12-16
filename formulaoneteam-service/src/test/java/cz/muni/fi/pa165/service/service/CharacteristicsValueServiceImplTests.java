package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.characteristics.CharacteristicsValueDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.service.CharacteristicsValueServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class CharacteristicsValueServiceImplTests extends BaseServiceTest<CharacteristicsValue> {

    @Mock
    private CharacteristicsValueDao characteristicsValueDaoMock;

    @InjectMocks
    private CharacteristicsValueServiceImpl characteristicsValueService;

    @Test
    public void addValue_withValidValues_valueAddCalled() {
        //When
        characteristicsValueService.add(entity);

        //Then
        verify(characteristicsValueDaoMock, times(1)).add(entity);
    }

    @Test
    public void findById_withExistingValue_valueReturned() {
        //Given
        when(characteristicsValueDaoMock.findById(entity.getId())).thenReturn(entity);

        //When
        CharacteristicsValue value = characteristicsValueService.findById(entity.getId());

        //Then
        assertEquals(entity, value);
    }

    @Test
    public void updateValue_withValidValues_valueUpdated() {
        //When
        characteristicsValueService.update(entity);

        //Then
        verify(characteristicsValueDaoMock, times(1)).update(entity);
    }

    @Test
    public void removeValue_withValidValues_valueDeleted() {
        //When
        characteristicsValueService.remove(entity.getId());

        //Then
        verify(characteristicsValueDaoMock, times(1)).delete(entity.getId());
    }

    @Test(expected = FormulaOneTeamException.class)
    public void add_throwsException() {
        //when
        characteristicsValueService.add((CharacteristicsValue) null);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_exceptionIsThrown() {
        //given
        entity.setId(-1);

        //when
        when(characteristicsValueService.findById(entity.getId())).thenReturn(entity);
        characteristicsValueService.update(entity);

        //then
        fail("Exception is not thrown");
    }

    @Test(expected = FormulaOneTeamException.class)
    public void remove_exceptionIsThrown() {
        //when
        characteristicsValueService.remove(-1);

        //then
        fail("Exception is not thrown");
    }

    @Override
    protected CharacteristicsValue createTestEntity() {
        return createCharacteristicsValue();
    }
}
