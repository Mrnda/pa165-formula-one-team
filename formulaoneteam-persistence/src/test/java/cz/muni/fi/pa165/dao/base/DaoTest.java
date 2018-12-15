package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for the general Dao functionality.
 *
 * @author Ivan Dendis
 */
public class DaoTest {

    private EntityManager em;
    private TestEntityDaoImpl dao;
    private final TestEntity testEntity = mock(TestEntity.class);

    @Before
    public void setUp() {
        em = mock(EntityManager.class);
        dao = new TestEntityDaoImpl(em);

        when(testEntity.getId()).thenReturn(1L);
        when(em.find(TestEntity.class, testEntity.getId())).thenReturn(testEntity);
    }

    @Test
    public void testAdd_persistCalled() {
        dao.add(testEntity);

        verify(em).persist(testEntity);
    }

    @Test
    public void testDelete_removeCalled() {
        dao.delete(testEntity.getId());

        verify(em).remove(testEntity);
    }

    @Test
    public void testUpdate_mergeCalled() {
        dao.update(testEntity);

        verify(em).merge(testEntity);
    }

    @Test
    public void testFindById_findCalled() {
        dao.findById(1L);

        verify(em).find(TestEntity.class, 1L);
    }

    private class TestEntity extends BaseEntity {

    }

    private class TestEntityDaoImpl extends DaoImpl<TestEntity> {

        public TestEntityDaoImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        protected Class<TestEntity> getClassType() {
            return TestEntity.class;
        }
    }
}
