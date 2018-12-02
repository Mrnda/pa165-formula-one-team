package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseEntityServiceImpl<T extends BaseEntity, D extends Dao<T>>
        extends BaseServiceImpl<T, D> implements BaseEntityService<T> {

    @Override
    public void add(T entity) throws FormulaOneTeamException {
        validateEntity(entity);
        dao.add(entity);
    }
}
