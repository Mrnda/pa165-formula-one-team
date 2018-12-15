package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.facade.base.BaseService;

import javax.inject.Inject;
import java.util.List;

import static cz.muni.fi.pa165.entity.base.BaseEntity.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
abstract class BaseServiceImpl<T extends BaseEntity, D extends Dao<T>> implements BaseService<T> {

    @Inject
    protected D dao;

    @Override
    public T findById(long id) throws FormulaOneTeamException {
        if (id == NO_ID) throw new FormulaOneTeamException("Not valid id");

        T entity = dao.findById(id);

        if (entity == null) throw new FormulaOneTeamException("Entity is null");

        return entity;
    }

    @Override
    public void remove(long id) throws FormulaOneTeamException {
        if (id == NO_ID) throw new FormulaOneTeamException("Not valid id");

        dao.delete(id);
    }

    @Override
    public T update(T entity) throws FormulaOneTeamException {
        validateEntity(entity);
        dao.update(entity);

        return dao.findById(entity.getId());
    }

    @Override
    public List<T> getAll() throws FormulaOneTeamException {
        List<T> entityList = dao.findAll();

        if (entityList == null) throw new FormulaOneTeamException("Couldn't create a new list");

        return entityList;
    }
}
