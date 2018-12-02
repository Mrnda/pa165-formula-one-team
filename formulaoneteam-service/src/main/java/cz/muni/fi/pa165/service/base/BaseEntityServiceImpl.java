package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.facade.base.BaseEntityService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseEntityServiceImpl<E extends BaseEntity, DAO extends Dao<E>>
        extends BaseServiceImpl<E, DAO> implements BaseEntityService<E> {

    @Override
    public void add(E entity) throws FormulaOneTeamException {
        validateEntity(entity);
        dao.add(entity);
    }
}
