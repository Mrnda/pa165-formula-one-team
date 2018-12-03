package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.service.ComponentParameterService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Ivan Dendis
 */

public class ComponentParameterFacadeImplTest extends BaseFacadeTest<ComponentParameter, ComponentParameterDTO> {

    @Mock
    private ComponentParameterService service;

    @InjectMocks
    private ComponentParameterFacadeImpl facade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, ComponentParameter.class)).thenReturn(entity);
    }

    @Test
    public void findComponentById() {
        when(service.findById(entity.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, ComponentParameterDTO.class)).thenReturn(dto);

        ComponentParameterDTO componentParameterdto = facade.findById(entity.getId());

        assertEquals(componentParameterdto, dto);
    }

    @Test
    public void deleteComponentTest() {
        facade.remove(dto);

        verify(service, times(1)).remove(entity);
    }

    @Test
    public void updateComponentTest() {
        facade.update(dto);

        verify(service, times(1)).update(entity);
    }

    @Test
    public void addComponentTest() {
        facade.add(dto);

        verify(service, times(1)).add(entity);
    }

    @Test
    public void getAllComponentTest() {
        List<ComponentParameter> list = new ArrayList<>();
        list.add(entity);
        when(service.getAll()).thenReturn(list);
        List<ComponentParameterDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        when(beanMappingServiceMock.mapTo(list, ComponentParameterDTO.class)).thenReturn(dtoList);

        List<ComponentParameterDTO> resultDtoList = new ArrayList<>(facade.getAll());

        verify(service).getAll();
        assertEquals(resultDtoList.size(), dtoList.size());
        Assert.assertTrue(resultDtoList.contains(dto));
    }

    @Override
    protected ComponentParameter createTestEntity() {
        return new ComponentParameter("name", "value");
    }

    @Override
    protected ComponentParameterDTO createTestDTO() {
        ComponentParameterDTO cDto = new ComponentParameterDTO();
        cDto.setName("name");
        cDto.setValue("value");
        return cDto;
    }
}
