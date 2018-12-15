package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDetailDTO;
import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;

import java.util.List;
import java.util.Map;

/**
 * @author Adel Chakouri
 */
public interface TestDriveFacade extends BaseEntityFacade<TestDriveDTO, TestDrive> {

    Map<CarSetupDTO, List<String>> getNotesForDriver(DriverDetailDTO driverDto);

    Map<DriverDetailDTO, List<String>> getNotesForCar(CarSetupDTO carDto);
}
