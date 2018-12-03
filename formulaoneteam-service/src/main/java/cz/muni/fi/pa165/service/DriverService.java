package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.facade.base.BaseUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
public interface DriverService extends BaseUserService<Driver> {

    List<Driver> getAllDriversByStatus(DriverStatus status);

    Driver findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType);
}
