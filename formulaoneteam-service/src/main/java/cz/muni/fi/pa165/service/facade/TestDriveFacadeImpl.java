package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.TestDriveService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class TestDriveFacadeImpl
        extends BaseEntityFacadeImpl<TestDriveDTO, TestDrive, TestDriveService>
        implements TestDriveFacade {

    @Inject
    private DriverService driverService;

    @Inject
    private CarSetupService carSetupService;

    @Override
    public Map<CarSetupDTO, List<String>> getNotesForDriver(DriverDTO driverDto) {
        Driver driver = driverService.findById(driverDto.getId());

        Map<CarSetup, List<String>> notes = service.getNotesForDriver(driver);

        return beanMappingService.mapTo(notes, CarSetupDTO.class);
    }

    @Override
    public Map<DriverDTO, List<String>> getNotesForCar(CarSetupDTO carDto) {
        CarSetup car = carSetupService.findById(carDto.getId());

        Map<Driver, List<String>> notes = service.getNotesForCar(car);

        return beanMappingService.mapTo(notes, DriverDTO.class);
    }

    @Override
    protected Class<TestDriveDTO> getDtoClass() {
        return TestDriveDTO.class;
    }

    @Override
    protected Class<TestDrive> getEntityClass() {
        return TestDrive.class;
    }
}
