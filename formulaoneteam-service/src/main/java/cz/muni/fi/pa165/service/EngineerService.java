package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.service.base.BaseService;

/**
 * @author Ivan Dendis
 */
public interface EngineerService extends BaseService<Engineer> {

    void registerEngineer(Engineer engineer, String plainPassword);

    boolean authenticate(Engineer engineer, String password);

    Engineer findEngineerByEmail(String email);
}
