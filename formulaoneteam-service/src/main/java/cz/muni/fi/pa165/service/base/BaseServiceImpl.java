package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;

import javax.inject.Inject;
import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseServiceImpl<T extends BaseEntity, D extends Dao<T>> implements BaseService<T> {

    @Inject
    protected D dao;

    @Override
    public T findById(long id) throws FormulaOneTeamException {
        if (id == BaseEntity.NO_ID) throw new FormulaOneTeamException("Not valid id");

        T entity = dao.findById(id);

        if (entity == null) throw new FormulaOneTeamException("Entity is null");

        return entity;
    }

    @Override
    public void add(T entity) throws FormulaOneTeamException {
        validateEntity(entity);
        dao.add(entity);
    }

    @Override
    public void remove(T entity) throws FormulaOneTeamException {
        validateEntity(entity);
        dao.delete(entity);
    }

    @Override
    public void update(T entity) throws FormulaOneTeamException {
        validateEntity(entity);
        dao.update(entity);
    }

    @Override
    public List<T> getAll() throws FormulaOneTeamException {
        List<T> entityList = dao.findAll();

        if (entityList == null) throw new FormulaOneTeamException("Couldn't create a new list");

        return entityList;
    }
}
