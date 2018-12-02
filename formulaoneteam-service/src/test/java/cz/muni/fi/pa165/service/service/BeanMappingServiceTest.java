package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.BeanMappingServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Adel Chakouri
 */
public class BeanMappingServiceTest extends BaseServiceTest<Manager> {

    @Mock
    private DozerBeanMapper dozer;

    @InjectMocks
    private BeanMappingServiceImpl beanMappingService;

    @Test
    public void mapFromDTO_toEntity() {
        //given
        ManagerDTO expectedDTO = createDTO();

        //when
        when(dozer.map(entity, ManagerDTO.class)).thenReturn(expectedDTO);

        //then
        assertEquals(expectedDTO, beanMappingService.mapTo(entity, ManagerDTO.class));
    }

    @Test
    public void getMapper_returnsValidMapper() {
        //then
        assertEquals(dozer, beanMappingService.getMapper());
    }

    @Test
    public void mapFromCollectionDTO_toCollectionEntity() {
        //given
        ManagerDTO firstManagerDTO = createDTO();
        ManagerDTO secondManagerDTO = createDTO();

        Manager firstManager = createManager();
        Manager secondManager = createManager();

        List<ManagerDTO> managerDTOS = new ArrayList<>();
        managerDTOS.add(firstManagerDTO);
        managerDTOS.add(secondManagerDTO);

        List<Manager> managers = new ArrayList<>();
        managers.add(firstManager);
        managers.add(secondManager);

        //when
        when(dozer.map(firstManager, ManagerDTO.class)).thenReturn(firstManagerDTO);
        when(dozer.map(secondManager, ManagerDTO.class)).thenReturn(secondManagerDTO);

        //then
        assertEquals(managerDTOS, beanMappingService.mapTo(managers, ManagerDTO.class));
    }

    @Override
    protected Manager createTestEntity() {
        return createManager();
    }

    private ManagerDTO createDTO() {
        ManagerDTO dto = new ManagerDTO();
        dto.setId(33);
        dto.setName("name");
        dto.setSurname("surname");
        dto.setEmail("email@gmail.com");
        dto.setPassword("aspdaspdasjd");
        return dto;
    }
}
