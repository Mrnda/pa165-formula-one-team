package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Driver findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType) {
        List<Driver> allDrivers = driverDao.findAll();
        Optional<Driver> bestDriver = allDrivers.stream()
                .max(Comparator.comparingDouble(value -> value.getCharaceristicOfType(characteristicsType).getValue()));
        return bestDriver.orElse(null);
    }

    @Override
    public Driver updateDriver(Driver driver){
        driverDao.update(driver);
        return driverDao.findById(driver.getId());
    }

    @Override
    public void deleteDriver(Driver driver){
        driverDao.delete(driver);
    }
}
