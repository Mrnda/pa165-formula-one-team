package cz.muni.fi.pa165.dao.testDrive;

import cz.muni.fi.pa165.dao.TestDrive.TestDriveDao;
import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.TestDrive;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Théo Desjardins
 */
public class TestDriveDaoTest extends BaseTest {

    @Inject
    private TestDriveDao testDriveDao;

    private TestDrive testDrive;

    @Before
    public void setUp() {
        testDrive = createTestDrive();
    }

    @Test
    public void addTestDrive() {
        //when
        testDriveDao.add(testDrive);

        //then
        assertEquals(testDrive, testDriveDao.findById(testDrive.getId()) );
        assertNotNull(testDriveDao.findById(testDrive.getId()));
    }

    @Test
    public void deleteTestDrive() {
        //when
        testDriveDao.add(testDrive);
        testDriveDao.delete(testDrive.getId());

        //then
        assertNull(testDriveDao.findById(testDrive.getId()));
    }

    @Test
    public void updateTestDrive() {
        //when
        testDriveDao.add(testDrive);
        testDrive.setNotes("new notes");
        testDriveDao.update(testDrive);

        //then
        assertEquals("new notes", testDrive.getNotes());
    }

    @Test
    public void findAllRace() {
        //given
        TestDrive otherTestDrive = new TestDrive(createCarSetup(), createDriver("otherEmail@email.com"), "someNotes",  createDate(20,12,2018));

        //when
        testDriveDao.add(testDrive);
        testDriveDao.add(otherTestDrive);

        //then
        List<TestDrive> testDrives = testDriveDao.findAll();
        assertTrue(testDrives.contains(testDrive));
        assertTrue(testDrives.contains(otherTestDrive));
        assertEquals(2, testDrives.size());
    }
}