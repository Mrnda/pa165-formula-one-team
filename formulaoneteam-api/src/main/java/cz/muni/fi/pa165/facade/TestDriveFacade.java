package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TestDriveDTO;

import java.util.List;

/**
 * @author Adel Chakouri
 */

public interface TestDriveFacade {

    TestDriveDTO findTestDriveByID(long id);

    void addTestDrive(TestDriveDTO testDriveDTO);

    void deleteTestDrive(TestDriveDTO testDriveDTO);

    void updateTestDrive(TestDriveDTO testDriveDTO);

    List<TestDriveDTO> getAllTestDrive();
}
