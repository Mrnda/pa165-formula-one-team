package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertTrue;

/**
 * Tests for the general Dao functionality.
 *
 * @author Ivan Dendis
 */
public class DaoTest {

    private EntityManager em;
    private TestEntityDaoImpl dao;
    private final TestEntity testEntity = mock(TestEntity.class);

    @BeforeMethod
    public void SetUp() {
        em = mock(EntityManager.class);
        dao = new TestEntityDaoImpl(em);
    }

    @Test
    public void testAdd_persistCalled() {
        dao.add(testEntity);

        verify(em).persist(testEntity);
    }

    @Test
    public void testAdd_validateCalled() {
        dao.add(testEntity);

        assertTrue(dao.getValidateCalled());
    }

    @Test
    public void testDelete_removeCalled() {
        dao.delete(testEntity);

        verify(em).remove(testEntity);
    }

    @Test
    public void testDelete_validateCalled() {
        dao.delete(testEntity);

        assertTrue(dao.getValidateCalled());
    }

    @Test
    public void testUpdate_mergeCalled() {
        dao.update(testEntity);

        verify(em).merge(testEntity);
    }

    @Test
    public void testUpdate_validateCalled() {
        dao.update(testEntity);

        assertTrue(dao.getValidateCalled());
    }

    @Test
    public void testFindById_findCalled() {
        dao.findById(1L);

        verify(em).find(TestEntity.class, 1L);
    }

    private class TestEntity extends BaseEntity {

    }

    private class TestEntityDaoImpl extends DaoImpl<TestEntity> {

        private Boolean validateCalled;

        public TestEntityDaoImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public Boolean getValidateCalled() {
            return validateCalled;
        }

        protected void validateEntity(TestEntity entity) {
            validateCalled = true;
        }

        @Override
        protected Class<TestEntity> getClassType() {
            return TestEntity.class;
        }
    }
}
