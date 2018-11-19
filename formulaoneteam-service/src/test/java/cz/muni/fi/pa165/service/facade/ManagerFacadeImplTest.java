package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ManagerService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ManagerFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private ManagerFacadeImpl managerFacadeImpl;

    private Manager manager;
    private ManagerDTO managerDTO;
    private Long managerId;
    private String managerEmail;
    private String managerPassword;
    private String managerName;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {

        //Manager
        managerId = 1L;
        managerEmail = "test@test.com";
        managerName = "Test Test";
        managerPassword = "123456";

        manager = new Manager();
        manager.setPasswordHash(managerPassword);
        manager.setEmail(managerEmail);
        manager.setId(managerId);
        manager.setName(managerName);

        managerDTO = new ManagerDTO();
        managerDTO.setEmail(managerEmail);
        managerDTO.setId(managerId);
        managerDTO.setName(managerName);
        managerDTO.setPassword(managerPassword);
    }

    @Test
    public void findByIdTest() {
        //given
        when(managerService.findManagerById(1L)).thenReturn(manager);
        when(beanMappingService.mapTo(manager, ManagerDTO.class)).thenReturn(managerDTO);
        Long id = 1L;

        //when
        ManagerDTO returnedDTO = managerFacadeImpl.findManagerById(id);

        //then
        assertEquals(returnedDTO, managerDTO);
        verify(managerService).findManagerById(id);
    }
}