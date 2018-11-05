package cz.muni.fi.pa165.dao.TestDrive;

import cz.muni.fi.pa165.dao.base.DaoImpl;
import cz.muni.fi.pa165.entity.TestDrive;
import org.springframework.stereotype.Repository;

/**
 * @author Adel Chakouri
 */
@Repository
public class TestDriveDaoImpl extends DaoImpl<TestDrive> implements TestDriveDao {

    @Override
    protected Class<TestDrive> getClassType() {
        return TestDrive.class;
    }

    @Override
    protected void validateEntity(TestDrive testDrive) {
        if (testDrive == null || !testDrive.isConfigured()) {
            throw new IllegalArgumentException("TestDrive is null or not configured.");
        }
    }
}
