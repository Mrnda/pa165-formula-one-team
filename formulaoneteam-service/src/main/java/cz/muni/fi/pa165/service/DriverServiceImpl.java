package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Inject
    private DriverDao driverDao;

    @Override
    public void registerDriver(Driver driver, String unencryptedPassword) {
        if (unencryptedPassword == null || unencryptedPassword.isEmpty()) {
            throw new FormulaOneTeamException("Password can't be empty.");
        }
        driver.setPasswordHash(Validator.createHash(unencryptedPassword));
        validateEntity(driver);
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
        return allDrivers
                .stream()
                .max(Comparator.comparingDouble(
                        value -> value.getCharacteristicOfType(characteristicsType).getValue()
                ))
                .orElse(null);
    }

    @Override
    public Driver updateDriver(Driver driver) {
        validateEntity(driver);
        driverDao.update(driver);
        return driverDao.findById(driver.getId());
    }

    @Override
    public void deleteDriver(Driver driver) {
        driverDao.delete(driver);
    }

    private void validateEntity(Driver entity) {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("Driver is null or not configured.");
        }
    }
}