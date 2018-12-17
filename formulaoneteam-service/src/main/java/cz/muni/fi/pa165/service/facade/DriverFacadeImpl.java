package cz.muni.fi.pa165.service.facade;

import com.google.common.collect.Sets;
import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.facade.base.BaseUserFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
@Transactional
public class DriverFacadeImpl
        extends BaseUserFacadeImpl<DriverDTO, Driver, DriverService>
        implements DriverFacade {

    @Override
    public void register(DriverDTO driver, String unencryptedPassword) {
        Driver driverEntity = beanMappingService.mapTo(driver, getEntityClass());
        if (driver.getCharacteristics().size() == 0) {
            addDefaultCharacteristicValuesToDriver(driverEntity);
        }
        service.register(driverEntity, unencryptedPassword);
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> allDriverEntities = service.getAll();
        return beanMappingService.mapTo(allDriverEntities, DriverDTO.class);
    }

    @Override
    public List<DriverDTO> getAllDriversByStatus(DriverStatus status) {
        List<Driver> allDriverEntities = service.getAllDriversByStatus(status);
        return beanMappingService.mapTo(allDriverEntities, DriverDTO.class);
    }

    @Override
    public DriverDTO findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType) {
        Driver driverEntity = service.findDriverWithHighestCharacteristicsValue(characteristicsType);
        return beanMappingService.mapTo(driverEntity, getDtoClass());
    }

    @Override
    public DriverDTO updateDriversCharacteristicsValue(
            DriverDTO driverDTO, CharacteristicsValueDTO characteristicsValueDTO
    ) {
        CharacteristicsValue characteristicsValue = beanMappingService.mapTo(characteristicsValueDTO, CharacteristicsValue.class);
        characteristicsValueService.update(characteristicsValue);
        return beanMappingService.mapTo(service.findById(driverDTO.getId()), getDtoClass());
    }

    @Override
    protected Class<DriverDTO> getDtoClass() {
        return DriverDTO.class;
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }

    private void addDefaultCharacteristicValuesToDriver(Driver driver) {
        driver.addCharacteristics(characteristicsValueService.add(Sets.newHashSet(
                new CharacteristicsValue(CharacteristicsType.PATIENCE, 0),
                new CharacteristicsValue(CharacteristicsType.STEERING, 0),
                new CharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 0),
                new CharacteristicsValue(CharacteristicsType.ENDURANCE, 0),
                new CharacteristicsValue(CharacteristicsType.PATIENCE, 0),
                new CharacteristicsValue(CharacteristicsType.AGGRESSIVITY, 0)
        )));
    }
}
