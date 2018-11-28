package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.TestDrive.TestDriveDao;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.service.TestDriveServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */
public class TestDriveServiceTest extends BaseServiceTest<TestDrive> {
    
    @Mock
    private TestDriveDao testDriveDaoMock;

    @InjectMocks
    private TestDriveServiceImpl testDriveService;
    
    @Test
    public void addTestDrive_WithValidValues() {
        //when
        testDriveService.add(entity);

        //then
        verify(testDriveDaoMock, times(1)).add(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void addTestDrive_exceptionIsThrown() {
        //when
        testDriveService.add(null);
    }

    @Test
    public void findById_WithExistingValue() {
        //Given
        when(testDriveDaoMock.findById(entity.getId())).thenReturn(entity);

        //When
        TestDrive TestDrive = testDriveService.findById(entity.getId());

        //Then
        assertEquals(entity, TestDrive);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void findById_exceptionIsThrown() {
        //when
        testDriveService.findById(-1);
    }

    @Test
    public void updateTestDrive_WithValidValues() {
        //When
        testDriveService.update(entity);

        //Then
        verify(testDriveDaoMock, times(1)).update(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void updateTestDrive_exceptionIsThrown() {
        //when
        testDriveService.update(null);
    }

    @Test
    public void removeTestDrive_WithValidValues() {
        //When
        testDriveService.remove(entity);

        //Then
        verify(testDriveDaoMock, times(1)).delete(entity);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void removeTestDrive_exceptionIsThrown() {
        //when
        testDriveService.remove(null);
    }

    @Test
    public void getAllTestDrives() {
        //given
        List<TestDrive> raceList = new ArrayList<>();
        raceList.add(entity);

        //when
        when(testDriveDaoMock.findAll()).thenReturn(raceList);
        List<TestDrive> resultRaceList = testDriveService.getAll();

        //then
        Assert.assertNotNull(resultRaceList);
        Assert.assertEquals(resultRaceList.size(), 1);
        Assert.assertTrue(resultRaceList.contains(entity));
    }

    @Override
    protected TestDrive createTestEntity() {
        return createTestDrive();
    }
}
