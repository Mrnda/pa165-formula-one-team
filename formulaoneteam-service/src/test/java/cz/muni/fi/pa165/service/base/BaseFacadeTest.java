package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.BeanMappingService;
import org.junit.Before;
import org.mockito.Mock;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseFacadeTest<E extends BaseEntity, DTO extends BaseDTO> extends BaseTest {

    @Mock
    protected BeanMappingService beanMappingServiceMock;

    protected E entity;
    protected DTO dto;

    @Before
    public void setUp() {
        entity = createTestEntity();
        dto = createTestDTO();
    }

    protected abstract E createTestEntity();

    protected abstract DTO createTestDTO();
}
