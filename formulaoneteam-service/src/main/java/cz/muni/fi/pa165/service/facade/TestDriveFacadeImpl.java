package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.TestDriveService;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Adel Chakouri
 */

@Service
@Transactional
public class TestDriveFacadeImpl implements TestDriveFacade {

    @Inject
    private TestDriveService testDriveService;
    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public TestDriveDTO findTestDriveByID(long id) {
        TestDrive testDrive = testDriveService.findById(id);
        return (testDrive == null) ? null : beanMappingService.mapTo(testDrive, TestDriveDTO.class);
    }

    @Override
    public void addTestDrive(TestDriveDTO testDriveDTO) {
        if (testDriveDTO == null) throw new IllegalArgumentException("null TestDriveDTO, cannot add");
        TestDrive testDrive = beanMappingService.mapTo(testDriveDTO, TestDrive.class);
        testDriveService.add(testDrive);
    }

    @Override
    public void deleteTestDrive(TestDriveDTO testDriveDTO) {
        if (testDriveDTO == null) throw new IllegalArgumentException("null testDriveDTO, cannot delete");
        testDriveService.remove(beanMappingService.mapTo(testDriveDTO, TestDrive.class));
    }

    @Override
    public void updateTestDrive(TestDriveDTO testDriveDTO) {
        if (testDriveDTO == null) throw new IllegalArgumentException("null testDriveDTO, cannot update");
        testDriveService.update(beanMappingService.mapTo(testDriveDTO, TestDrive.class));
    }

    @Override
    public List<TestDriveDTO> getAllTestDrive() {
        List<TestDrive> allRacesEntities = testDriveService.getAll();
        return beanMappingService.mapTo(allRacesEntities, TestDriveDTO.class);
    }
}
