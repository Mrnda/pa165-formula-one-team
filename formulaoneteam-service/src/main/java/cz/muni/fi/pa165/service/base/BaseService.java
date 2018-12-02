package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
interface BaseService<T extends BaseEntity> {

    void remove(@Nullable T entity) throws FormulaOneTeamException;

    T update(@Nullable T entity) throws FormulaOneTeamException;

    @NonNull
    T findById(long id) throws FormulaOneTeamException;

    @NonNull
    List<T> getAll() throws FormulaOneTeamException;

    void validateEntity(@Nullable T entity) throws FormulaOneTeamException;
}
