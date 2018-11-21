package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Inject
    private DriverDao driverDao;

    @Override
    public void registerDriver(Driver driver, String unencryptedPassword) {
        driver.setPasswordHash(Validator.createHash(unencryptedPassword));
        driverDao.add(driver);
    }

    @Override
    public boolean authenticate(Driver driver, String password) {
        Driver driverEntity = driverDao.findById(driver.getId());
        return Validator.validatePassword(password, driverEntity.getPasswordHash());
    }

    @Override
    public Driver findDriverById(long id) {
        return driverDao.findById(id);
    }

    @Override
    public Driver findDriverByEmail(String email) {
        return driverDao.findByEmail(email);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDao.findAll();
    }

    @Override
    public List<Driver> getAllDriversByStatus(DriverStatus status) {
        return driverDao.findAll().stream()
                .filter(driver -> driver.getDriverStatus() == status)
                .collect(Collectors.toList());
    }
}
