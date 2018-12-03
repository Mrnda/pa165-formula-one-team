package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.service.TestDriveService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class TestDriveFacadeImpl
        extends BaseEntityFacadeImpl<TestDriveDTO, TestDrive, TestDriveService>
        implements TestDriveFacade {

    @Override
    protected Class<TestDriveDTO> getDtoClass() {
        return TestDriveDTO.class;
    }

    @Override
    protected Class<TestDrive> getEntityClass() {
        return TestDrive.class;
    }
}
