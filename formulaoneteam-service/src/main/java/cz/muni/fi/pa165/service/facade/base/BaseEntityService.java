package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.springframework.lang.Nullable;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseEntityService<E extends BaseEntity> extends BaseService<E> {

    void add(@Nullable E entity);
}
