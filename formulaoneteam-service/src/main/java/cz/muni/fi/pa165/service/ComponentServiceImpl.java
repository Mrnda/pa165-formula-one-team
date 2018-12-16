package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author Théo Desjardins
 */
@Service
public class ComponentServiceImpl extends BaseEntityServiceImpl<Component, ComponentDao> implements ComponentService {

    @Override
    public void validateEntity(Component entity) {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("Component is null or not configured.");
        }
    }

    @Override
    protected Class<Component> getEntityClass() {
        return Component.class;
    }
}
