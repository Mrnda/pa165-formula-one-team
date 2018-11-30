package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Théo Desjardins
 */

public class ComponentFacadeImplTest extends BaseFacadeTest {

    private Component testingComponent;
    private ComponentDTO componentDTO;

    @Mock
    private ComponentService componentService;

    @InjectMocks
    private ComponentFacadeImpl componentFacade;

    @BeforeMethod
    public void setUp() {

        componentDTO = new ComponentDTO();

        componentDTO.setType(ComponentType.ENGINE);
        componentDTO.setName("testWithEngine");

        testingComponent = createComponent();

        when(beanMappingServiceMock.mapTo(componentDTO,Component.class)).thenReturn(testingComponent);
    }

    @Test
    public void findComponentById() {
        //Given
        when(componentService.findById(testingComponent.getId())).thenReturn(testingComponent);
        when(beanMappingServiceMock.mapTo(testingComponent, ComponentDTO.class)).thenReturn(componentDTO);

        //When
        ComponentDTO resComponentDTO = componentFacade.findByID(testingComponent.getId());

        //Then
        assertEquals(resComponentDTO,componentDTO);
    }

    @Test
    public void deleteComponentTest() {

        //when
        componentFacade.deleteComponent(componentDTO);

        //then
        verify(componentService, times(1)).delete(testingComponent);
    }

    @Test
    public void updateComponentTest() {

        //when
        componentFacade.updateComponent(componentDTO);

        //then
        verify(componentService, times(1)).update(testingComponent);
    }

    @Test
    public void addComponentTest() {

        //when
        componentFacade.addComponent(componentDTO);

        //then
        verify(componentService, times(1)).add(testingComponent);
    }

    @Test
    public void getAllComponentTest() {

        //Given
        List<Component> listComponent = new ArrayList<>();
        listComponent.add(testingComponent);
        when(componentService.getAllComponent()).thenReturn(listComponent);
        List<ComponentDTO> listDTOComponent = new ArrayList<>();
        listDTOComponent.add(componentDTO);
        when(beanMappingServiceMock.mapTo(listComponent, ComponentDTO.class)).thenReturn(listDTOComponent);

        //When
        List<ComponentDTO> resListComponentDTO = new ArrayList<>(componentFacade.getAllComponent());

        //Then
        verify(componentService).getAllComponent();
        assertEquals(resListComponentDTO.size(),1);
        Assert.assertTrue(resListComponentDTO.contains(componentDTO));
    }
}
