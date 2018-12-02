package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.engineer.EngineerDao;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.service.base.BaseUserServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Dendis
 */
@Service
public class EngineerServiceImpl extends BaseUserServiceImpl<Engineer, EngineerDao>
        implements EngineerService {

    @Override
    public void validateEntity(Engineer entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("Engineer entity is null or not configured");
        }
	}
}
