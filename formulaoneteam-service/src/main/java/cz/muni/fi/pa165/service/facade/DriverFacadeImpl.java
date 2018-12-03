package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.dto.DriverListItemDTO;
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
        extends BaseUserFacadeImpl<DriverDetailDTO, Driver, DriverService>
        implements DriverFacade {

    @Override
    public void register(DriverDetailDTO driver, String unencryptedPassword) {
        Driver driverEntity = beanMappingService.mapTo(driver, getEntityClass());
        if (driver.getCharacteristics().size() == 0) {
            addDefaultCharacteristicValuesToDriver(driverEntity);
        }
        service.register(driverEntity, unencryptedPassword);
    }

    @Override
    public List<DriverListItemDTO> getAllDrivers() {
        List<Driver> allDriverEntities = service.getAll();
        return beanMappingService.mapTo(allDriverEntities, DriverListItemDTO.class);
    }

    @Override
    public List<DriverListItemDTO> getAllDriversByStatus(DriverStatus status) {
        List<Driver> allDriverEntities = service.getAllDriversByStatus(status);
        return beanMappingService.mapTo(allDriverEntities, DriverListItemDTO.class);
    }

    @Override
    public DriverDetailDTO findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType) {
        Driver driverEntity = service.findDriverWithHighestCharacteristicsValue(characteristicsType);
        return beanMappingService.mapTo(driverEntity, getDtoClass());
    }

    @Override
    public DriverDetailDTO updateDriversCharacteristicsValue(CharacteristicsValueDTO characteristicsValueDTO) {
        CharacteristicsValue characteristicsValue = beanMappingService.mapTo(characteristicsValueDTO, CharacteristicsValue.class);
        characteristicsValueService.update(characteristicsValue);
        return beanMappingService.mapTo(service.findById(characteristicsValueDTO.getDriver().getId()), getDtoClass());
    }

    @Override
    protected Class<DriverDetailDTO> getDtoClass() {
        return DriverDetailDTO.class;
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }

    private void addDefaultCharacteristicValuesToDriver(Driver driver) {
        CharacteristicsValue value = new CharacteristicsValue(CharacteristicsType.AGGRESIVITY, 0, driver);
        characteristicsValueService.add(value);
        driver.addCharacteristic(value);
        value = new CharacteristicsValue(CharacteristicsType.PATIENCE, 0, driver);
        characteristicsValueService.add(value);
        driver.addCharacteristic(value);
        value = new CharacteristicsValue(CharacteristicsType.ENDURANCE, 0, driver);
        characteristicsValueService.add(value);
        driver.addCharacteristic(value);
        value = new CharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 0, driver);
        characteristicsValueService.add(value);
        driver.addCharacteristic(value);
        value = new CharacteristicsValue(CharacteristicsType.STEERING, 0, driver);
        characteristicsValueService.add(value);
        driver.addCharacteristic(value);
    }
}
