package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
public interface DriverService {

    void registerDriver(Driver driver, String unencryptedPassword);

    boolean authenticate(Driver driver, String password);

    Driver findDriverById(long id);

    Driver findDriverByEmail(String email);

    List<Driver> getAllDrivers();

    List<Driver> getAllDriversByStatus(DriverStatus status);

    Driver findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType);

    Driver updateDriver(Driver driver);

    void deleteDriver(Driver driver);
}
