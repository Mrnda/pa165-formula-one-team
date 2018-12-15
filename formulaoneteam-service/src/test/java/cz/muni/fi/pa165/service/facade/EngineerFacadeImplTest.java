package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.EngineerDTO;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.service.EngineerService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * @author Ivan Dendis
 */
public class EngineerFacadeImplTest extends BaseFacadeTest<Engineer, EngineerDTO> {

    private static final String TEST_ENGINEER_PASSWORD = "adsadassa";

    @Mock
    private EngineerService service;

    @InjectMocks
    private EngineerFacadeImpl facade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, Engineer.class)).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, EngineerDTO.class)).thenReturn(dto);
    }

    @Test
    public void registerEngineer_withValidData() {
        facade.register(dto, "pwd");

        verify(service, times(1)).register(entity, "pwd");
    }

    @Test
    public void authenticate_returnsTrue() {
        when(service.authenticate(dto.getEmail(), TEST_ENGINEER_PASSWORD)).thenReturn(true);

        assertTrue(facade.authenticate(dto.getEmail(), TEST_ENGINEER_PASSWORD));
    }

    @Test
    public void findById_returnsValidDto() {
        when(service.findById(dto.getId())).thenReturn(entity);

        assertEquals(dto, facade.findById(dto.getId()));
    }
    @Test
    public void findByEmail_returnsValidDto() {
        when(service.findByEmail(dto.getEmail())).thenReturn(entity);

        assertEquals(dto, facade.findByEmail(dto.getEmail()));
    }

    @Test
    public void getAll_returnsAllValidDto() {
        List<Engineer> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);

        List<EngineerDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        dtoList.add(dto);

        when(beanMappingServiceMock.mapTo(list, EngineerDTO.class)).thenReturn(dtoList);
        when(service.getAll()).thenReturn(list);

        assertEquals(dtoList, facade.getAll());
    }

    @Test
    public void delete_deletesEntity() {
        //when
        when(service.authenticate(dto.getEmail(), TEST_ENGINEER_PASSWORD)).thenReturn(true);
        when(service.findByEmail(dto.getEmail())).thenReturn(entity);

        facade.delete(dto.getEmail(), TEST_ENGINEER_PASSWORD);

        //then
        verify(service).remove(dto.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_throwsException() {
        facade.delete(dto.getEmail(), TEST_ENGINEER_PASSWORD);

        fail("Exception is not thrown");
    }

    @Override
    protected Engineer createTestEntity() {
        return createEngineer();
    }

    @Override
    protected EngineerDTO createTestDTO() {
        return createEngineerDTO();
    }
}
