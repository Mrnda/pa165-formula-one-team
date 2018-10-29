package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.Driver;

public interface DriverDao extends Dao<Driver> {
    Driver findByEmail(String email);
}
