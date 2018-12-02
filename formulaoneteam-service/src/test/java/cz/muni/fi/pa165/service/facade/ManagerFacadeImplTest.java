package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.ManagerService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class ManagerFacadeImplTest extends BaseFacadeTest {

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private ManagerFacadeImpl managerFacadeImpl;

    private Manager manager;
    private ManagerDTO managerDTO;

    @BeforeMethod
    public void setUp() {
        Long managerId = 1L;
        String managerEmail = "test@test.com";
        String managerName = "Test Test";
        String managerPassword = "123456";

        managerDTO = new ManagerDTO();
        managerDTO.setEmail(managerEmail);
        managerDTO.setId(managerId);
        managerDTO.setName(managerName);
        managerDTO.setPassword(managerPassword);
    }

    @Test
    public void findByIdTest() {
        //given
        when(managerService.findById(1L)).thenReturn(manager);
        when(beanMappingServiceMock.mapTo(manager, ManagerDTO.class)).thenReturn(managerDTO);
        Long id = 1L;

        //when
        ManagerDTO returnedDTO = managerFacadeImpl.findManagerById(id);

        //then
        assertEquals(returnedDTO, managerDTO);
        verify(managerService).findById(id);
    }
}