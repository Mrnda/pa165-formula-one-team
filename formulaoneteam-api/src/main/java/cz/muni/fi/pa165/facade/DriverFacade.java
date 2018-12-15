package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.driver.DriverDetailDTO;
import cz.muni.fi.pa165.dto.driver.DriverListItemDTO;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.base.BaseUserFacade;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public interface DriverFacade extends BaseUserFacade<DriverDetailDTO, Driver> {

    List<DriverListItemDTO> getAllDrivers();

    List<DriverListItemDTO> getAllDriversByStatus(DriverStatus status);

    DriverDetailDTO findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType);

    DriverDetailDTO updateDriversCharacteristicsValue(
            DriverListItemDTO driverListItemDTO, CharacteristicsValueDTO characteristicsValueDTO
    );
}
