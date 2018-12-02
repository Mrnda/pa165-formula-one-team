package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.ManagerService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
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

    @BeforeMethod
    public void setUp() {
        manager = createManager();
    }

    @Test
    public void findByIdTest() {
        //given
        ManagerDTO managerDTO = mock(ManagerDTO.class);

        //when
        when(managerService.findById(manager.getId())).thenReturn(manager);
        when(beanMappingServiceMock.mapTo(manager, ManagerDTO.class)).thenReturn(managerDTO);

        ManagerDTO returnedDTO = managerFacadeImpl.findManagerById(manager.getId());

        //then
        assertEquals(returnedDTO, managerDTO);
        verify(managerService).findById(manager.getId());
    }
}