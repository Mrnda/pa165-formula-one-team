package cz.muni.fi.pa165.service.facade;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.TestDriveService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;


/**
 * @author Adel Chakouri
 */

public class TestDriveFacadeImplTest extends BaseFacadeTest {

    @Mock
    private TestDriveService testDriveService;

    @InjectMocks
    private TestDriveFacadeImpl testDriveFacade;

    private TestDriveDTO testDriveDTO;

    private TestDrive testDrive;

    @BeforeMethod
    public void setUp() {

        testDriveDTO = new TestDriveDTO();
        testDriveDTO.setCar(createCarSetup());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriver(createDriver());
        testDriveDTO.setNotes("notes");

        testDrive = createTestDrive();
        when(beanMappingServiceMock.mapTo(testDriveDTO,TestDrive.class)).thenReturn(testDrive);
    }

    @Test
    public void getAllTestDriveTest(){
        //Given
        List<TestDrive> listTestDrive = new ArrayList<>();
        listTestDrive.add(testDrive);
        when(testDriveService.getAll()).thenReturn(listTestDrive);
        List<TestDriveDTO> listDTOTestDrive = new ArrayList<>();
        listDTOTestDrive.add(testDriveDTO);
        when(beanMappingServiceMock.mapTo(listTestDrive, TestDriveDTO.class)).thenReturn(listDTOTestDrive);

        //When
        List<TestDriveDTO> resListTestDriveDTO = new ArrayList<>(testDriveFacade.getAllTestDrive());

        //Then
        verify(testDriveService).getAll();
        assertEquals(resListTestDriveDTO.size(),1);
        Assert.assertTrue(resListTestDriveDTO.contains(testDriveDTO));
    }

    @Test
    void findTestDriveByIdTest() {
        //Given
        when(beanMappingServiceMock.mapTo(testDrive, TestDriveDTO.class)).thenReturn(testDriveDTO);
        when(testDriveService.findById(22)).thenReturn(testDrive);

        //When
        TestDriveDTO resTestDriveDTO = testDriveFacade.findTestDriveByID(testDrive.getId());

        //Then
        assertEquals(resTestDriveDTO,testDriveDTO);
    }

    @Test
    public void deleteTestDriveTest() {
       //When
        testDriveFacade.deleteTestDrive(testDriveDTO);

        //Then
        verify(testDriveService, times(1)).remove(testDrive);
    }

    @Test
    public void updateTestDriveTest() {
        //When
        testDriveFacade.updateTestDrive(testDriveDTO);

        //Then
        verify(testDriveService, times(1)).update(testDrive);
    }

    @Test
    public void addTestDriveTest() {
       //When
        testDriveFacade.addTestDrive(testDriveDTO);

        //Then
        verify(testDriveService, times(1)).add(testDrive);
    }
}
