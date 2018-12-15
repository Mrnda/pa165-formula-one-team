package cz.muni.fi.pa165.service.facade.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseService<E extends BaseEntity> {

    void remove(long id);

    E update(@Nullable E entity);

    @NonNull
    E findById(long id);

    @NonNull
    List<E> getAll();

    void validateEntity(@Nullable E entity);
}
