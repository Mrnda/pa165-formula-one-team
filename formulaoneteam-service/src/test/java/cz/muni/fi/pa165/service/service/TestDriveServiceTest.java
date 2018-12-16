package cz.muni.fi.pa165.service.service;

import cz.muni.fi.pa165.dao.TestDrive.TestDriveDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.service.TestDriveServiceImpl;
import cz.muni.fi.pa165.service.base.BaseServiceTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotNull;

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
        testDriveService.add((TestDrive) null);
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

    @Test(expected = EntityNotFoundException.class)
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
        testDriveService.remove(entity.getId());

        //Then
        verify(testDriveDaoMock, times(1)).delete(entity.getId());
    }

    @Test(expected = FormulaOneTeamException.class)
    public void removeTestDrive_exceptionIsThrown() {
        //when
        testDriveService.remove(-1);
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
        assertNotNull(resultRaceList);
        assertEquals(resultRaceList.size(), 1);
        assertTrue(resultRaceList.contains(entity));
    }

    @Test
    public void getAllNotesForDriver_noDate() {
        //given
        Driver driver1 = mockDriver("e1@e.c");
        Driver driver2 = mockDriver("e2@e.c");

        CarSetup setup1 = mockCarSetup("e1");
        CarSetup setup2 = mockCarSetup("e2");

        Calendar cal = Calendar.getInstance();
        cal.set(2000, 1, 1);

        String note1 = "Best drive";
        String note2 = "2nd best drive";
        String note3 = "other driver";
        String note4 = "note";

        TestDrive drive1 = new TestDrive(setup1, driver1, note1, cal.getTime());
        TestDrive drive2 = new TestDrive(setup2, driver1, note2, cal.getTime());
        TestDrive drive3 = new TestDrive(setup1, driver2, note3, cal.getTime());
        TestDrive drive4 = new TestDrive(setup1, driver1, note4, cal.getTime());

        List<TestDrive> drives = new ArrayList<TestDrive>();
        drives.add(drive1);
        drives.add(drive2);
        drives.add(drive3);
        drives.add(drive4);

        //when
        when(testDriveDaoMock.findAll()).thenReturn(drives);
        Map<CarSetup, List<String>> resultNotes = testDriveService.getNotesForDriver(driver1);

        //then
        assertTrue(resultNotes.containsKey(setup1));
        assertTrue(resultNotes.containsKey(setup2));
        assertEquals(2, resultNotes.keySet().size());

        assertTrue(resultNotes.get(setup1).contains(note1));
        assertTrue(resultNotes.get(setup1).contains(note4));
        assertEquals(2, resultNotes.get(setup1).size());

        assertTrue(resultNotes.get(setup2).contains(note2));
        assertEquals(1, resultNotes.get(setup2).size());
    }

    @Test
    public void getAllNotesForCar_noDate() {
        //given
        Driver driver1 = mockDriver("e1@e.c");
        Driver driver2 = mockDriver("e2@e.c");
        Driver driver3 = mockDriver("e3@e.c");

        CarSetup setup1 = mockCarSetup("e1");
        CarSetup setup2 = mockCarSetup("e2");

        Calendar cal = Calendar.getInstance();
        cal.set(2000, 1, 1);

        String note1 = "Best drive";
        String note2 = "2nd best drive";
        String note3 = "other driver";
        String note4 = "note";

        TestDrive drive1 = new TestDrive(setup1, driver1, note1, cal.getTime());
        TestDrive drive2 = new TestDrive(setup2, driver3, note2, cal.getTime());
        TestDrive drive3 = new TestDrive(setup1, driver2, note3, cal.getTime());
        TestDrive drive4 = new TestDrive(setup1, driver1, note4, cal.getTime());

        List<TestDrive> drives = new ArrayList<>();
        drives.add(drive1);
        drives.add(drive2);
        drives.add(drive3);
        drives.add(drive4);

        //when
        when(testDriveDaoMock.findAll()).thenReturn(drives);
        Map<Driver, List<String>> resultNotes = testDriveService.getNotesForCar(setup1);

        //then
        assertTrue(resultNotes.containsKey(driver1));
        assertTrue(resultNotes.containsKey(driver2));
        assertEquals(2, resultNotes.keySet().size());

        assertTrue(resultNotes.get(driver1).contains(note1));
        assertTrue(resultNotes.get(driver1).contains(note4));
        assertEquals(2, resultNotes.get(driver1).size());

        assertTrue(resultNotes.get(driver2).contains(note3));
        assertEquals(1, resultNotes.get(driver2).size());
    }

    @Override
    protected TestDrive createTestEntity() {
        return createTestDrive();
    }

    private Driver mockDriver(String email) {
        return new Driver("driver1", "sur", email, "meh", new Date(), DriverStatus.TEST);
    }

    private CarSetup mockCarSetup(String engine) {
        Component e = new Component(engine, ComponentType.ENGINE);
        Component s = new Component("SUSPENSION", ComponentType.SUSPENSION);
        Component b = new Component("BRAKES", ComponentType.BRAKES);
        Component tr = new Component("TRANSMISSION", ComponentType.TRANSMISSION);
        Component ti = new Component("TIRES", ComponentType.TIRES);
        Component c = new Component("COVER", ComponentType.COVER);

        return new CarSetup(e, s, b, tr, ti, c);
    }
}
