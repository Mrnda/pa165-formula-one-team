package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.base.BaseUserFacade;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public interface DriverFacade extends BaseUserFacade<DriverDTO, Driver> {

    void updateDriver(DriverDTO driver);

    List<DriverDTO> getAllDrivers();

    List<DriverDTO> getAllDriversByStatus(DriverStatus status);

    DriverDTO findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType);

    DriverDTO updateDriversCharacteristicsValue(
            DriverDTO driverDTO, CharacteristicsValueDTO characteristicsValueDTO
    );

    DriverDTO createDefaultDriver();
}
