package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseEntityService<E extends BaseEntity> extends BaseService<E> {

    E add(@Nullable E entity);

    Set<E> add(@Nullable Set<E> entities);
}
