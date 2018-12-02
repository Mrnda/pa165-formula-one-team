package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.ManagerService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class ManagerFacadeImplTest extends BaseFacadeTest<Manager, ManagerDTO> {

    private static final String TEST_MANAGER_PASSWORD = "aspkdjp1j2jda";

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private ManagerFacadeImpl managerFacade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, Manager.class)).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, ManagerDTO.class)).thenReturn(dto);
    }

    @Test
    public void registerManager_withValidData() {
        //when
        managerFacade.register(dto, TEST_MANAGER_PASSWORD);

        //then
        verify(managerService, times(1)).register(entity, TEST_MANAGER_PASSWORD);
    }

    @Test
    public void authenticate_returnsTrue() {
        //when
        when(managerService.authenticate(dto.getEmail(), dto.getPassword())).thenReturn(true);

        //then
        assertTrue(managerFacade.authenticate(dto.getEmail(), dto.getPassword()));
    }

    @Test
    public void findById_returnsValidDto() {
        //when
        when(managerService.findById(dto.getId())).thenReturn(entity);

        //then
        assertEquals(dto, managerFacade.findById(dto.getId()));
    }

    @Test
    public void findByEmail_returnsValidDto() {
        //when
        when(managerService.findByEmail(dto.getEmail())).thenReturn(entity);

        //then
        assertEquals(dto, managerFacade.findByEmail(dto.getEmail()));
    }

    @Test
    public void getAll_returnsAllValidDto() {
        //given
        List<Manager> managerList = new ArrayList<>();
        managerList.add(entity);
        managerList.add(entity);
        managerList.add(entity);

        List<ManagerDTO> managerDTOList = new ArrayList<>();
        managerDTOList.add(dto);
        managerDTOList.add(dto);
        managerDTOList.add(dto);

        //when
        when(beanMappingServiceMock.mapTo(managerList, ManagerDTO.class)).thenReturn(managerDTOList);
        when(managerService.getAll()).thenReturn(managerList);

        //then
        assertEquals(managerDTOList, managerFacade.getAll());
    }

    @Test
    public void delete_deletesEntity() {
        //when
        when(managerService.authenticate(dto.getEmail(), dto.getPassword())).thenReturn(true);

        managerFacade.delete(dto.getEmail(), dto.getPassword());

        //then
        verify(managerService).remove(managerService.findByEmail(dto.getEmail()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_throwsException() {
        //when
        managerFacade.delete(dto.getEmail(), dto.getPassword());

        //then
        fail("Exception is not thrown");
    }

    @Override
    protected Manager createTestEntity() {
        return createManager();
    }

    @Override
    protected ManagerDTO createTestDTO() {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setId(12);
        managerDTO.setEmail("manager@gmail.com");
        managerDTO.setName("Manager");
        managerDTO.setSurname("Test");
        managerDTO.setPassword("aspkdjp1j2jda");
        return managerDTO;
    }
}