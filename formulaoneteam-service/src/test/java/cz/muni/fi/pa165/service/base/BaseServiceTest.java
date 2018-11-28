package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.junit.Before;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseServiceTest<T extends BaseEntity> extends BaseTest {

    protected T entity;

    @Before
    public void setUp() {
        entity = createTestEntity();
    }

    protected abstract T createTestEntity();
}
