package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
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

public class TestDriveFacadeImplTest extends BaseFacadeTest<TestDrive, TestDriveDTO> {

    @Mock
    private TestDriveService testDriveService;

    @InjectMocks
    private TestDriveFacadeImpl testDriveFacade;

    @BeforeMethod
    public void setUp() {
        when(beanMappingServiceMock.mapTo(dto, TestDrive.class)).thenReturn(entity);
    }

    @Test
    public void getAllTestDriveTest() {
        //Given
        List<TestDrive> listTestDrive = new ArrayList<>();
        listTestDrive.add(entity);
        when(testDriveService.getAll()).thenReturn(listTestDrive);
        List<TestDriveDTO> listDTOTestDrive = new ArrayList<>();
        listDTOTestDrive.add(dto);
        when(beanMappingServiceMock.mapTo(listTestDrive, TestDriveDTO.class)).thenReturn(listDTOTestDrive);

        //When
        List<TestDriveDTO> resListTestDriveDTO = new ArrayList<>(testDriveFacade.getAllTestDrive());

        //Then
        verify(testDriveService).getAll();
        assertEquals(resListTestDriveDTO.size(), 1);
        Assert.assertTrue(resListTestDriveDTO.contains(dto));
    }

    @Test
    void findTestDriveByIdTest() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, TestDriveDTO.class)).thenReturn(dto);
        when(testDriveService.findById(22)).thenReturn(entity);

        //When
        TestDriveDTO resTestDriveDTO = testDriveFacade.findTestDriveByID(entity.getId());

        //Then
        assertEquals(resTestDriveDTO, dto);
    }

    @Test
    public void deleteTestDriveTest() {
        //When
        testDriveFacade.deleteTestDrive(dto);

        //Then
        verify(testDriveService, times(1)).remove(entity);
    }

    @Test
    public void updateTestDriveTest() {
        //When
        testDriveFacade.updateTestDrive(dto);

        //Then
        verify(testDriveService, times(1)).update(entity);
    }

    @Test
    public void addTestDriveTest() {
        //When
        testDriveFacade.addTestDrive(dto);

        //Then
        verify(testDriveService, times(1)).add(entity);
    }

    @Override
    protected TestDrive createTestEntity() {
        return createTestDrive();
    }

    @Override
    protected TestDriveDTO createTestDTO() {
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setCar(createCarSetup());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriver(createDriver());
        testDriveDTO.setNotes("notes");
        return testDriveDTO;
    }
}
