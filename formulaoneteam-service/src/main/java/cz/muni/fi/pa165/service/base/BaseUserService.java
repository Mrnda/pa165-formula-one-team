package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseUserService<T extends BaseEntity> extends BaseService<T> {

    void register(T entity, String unencryptedPassword);

    boolean authenticate(T entity, String password);

    T findByEmail(String email);
}
