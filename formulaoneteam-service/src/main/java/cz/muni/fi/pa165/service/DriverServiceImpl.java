package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.base.BaseUserServiceImpl;
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
public class DriverServiceImpl extends BaseUserServiceImpl<Driver, DriverDao> implements DriverService {

    @Inject
    private DriverDao driverDao;

    @Override
    public Driver update(Driver entity) throws FormulaOneTeamException {
        if (entity.getPassword().isEmpty()) {
            final Driver originalDriver = findById(entity.getId());
            entity.setPassword(originalDriver.getPassword());
        } else {
            entity.setPassword(Validator.createHash(entity.getPassword()));
        }
        return super.update(entity);
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
    public void validateEntity(Driver entity) {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("Driver is null or not configured.");
        }
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }
}