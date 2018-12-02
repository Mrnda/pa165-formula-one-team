package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.componentparameter.ComponentParameterDao;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;

/**
 * @author Ivan Dendis
 */
public class ComponentParameterServiceImpl extends BaseEntityServiceImpl<ComponentParameter, ComponentParameterDao>
        implements ComponentParameterService {

    @Override
    public void validateEntity(ComponentParameter entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("ComponentParameter entity is null or not configured");
        }
	}
}
