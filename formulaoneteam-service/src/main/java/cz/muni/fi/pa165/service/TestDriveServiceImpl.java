package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.TestDrive.TestDriveDao;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author Adel Chakouri
 */
@Service
public class TestDriveServiceImpl extends BaseEntityServiceImpl<TestDrive, TestDriveDao> implements TestDriveService {

    @Override
    public void validateEntity(TestDrive entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("TestDrive entity is null or not configured");
        }
    }
}
