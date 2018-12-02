package cz.muni.fi.pa165.entity.base;

import cz.muni.fi.pa165.dao.base.BaseTest;
import org.junit.Before;
import org.junit.Test;

import static org.testng.Assert.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class BaseEntityTest extends BaseTest {

    private BaseEntity baseEntity;

    @Before
    public void setUp() {
        baseEntity = new BaseEntity() {};
    }

    @Test
    public void whenIdIsNotSet() {
        //then
        assertEquals(baseEntity.getId(), BaseEntity.NO_ID);
        assertFalse(baseEntity.hasId());
    }

    @Test
    public void whenIdIsSet_returnId() {
        //given
        long testId = 100;

        //when
        baseEntity.setId(testId);

        //then
        assertEquals(baseEntity.getId(), testId);
        assertTrue(baseEntity.hasId());
    }
}
