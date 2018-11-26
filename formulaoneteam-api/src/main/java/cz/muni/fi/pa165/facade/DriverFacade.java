package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.dto.DriverListItemDTO;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public interface DriverFacade {

    void registerDriver(DriverDetailDTO driver, String unencryptedPassword);

    boolean authenticate(DriverDetailDTO driver, String password);

    DriverDetailDTO findDriverById(long id);

    DriverDetailDTO findDriverByEmail(String email);

    List<DriverListItemDTO> getAllDrivers();

    List<DriverListItemDTO> getAllDriversByStatus(DriverStatus status);

    DriverDetailDTO findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType);

    DriverDetailDTO updateDriversCharacteristicsValue(CharacteristicsValueDTO characteristicsValueDTO);
}
