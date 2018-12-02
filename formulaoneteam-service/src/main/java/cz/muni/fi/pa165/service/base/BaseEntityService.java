package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.lang.Nullable;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseEntityService<T extends BaseEntity> extends BaseService<T> {

    void add(@Nullable T entity) throws FormulaOneTeamException;
}
