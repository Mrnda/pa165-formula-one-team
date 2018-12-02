package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.dto.DriverListItemDTO;
import cz.muni.fi.pa165.entity.CharacteristicsValue;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CharacteristicsValueService;
import cz.muni.fi.pa165.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Service
@Transactional
public class DriverFacadeImpl implements DriverFacade {

    @Inject
    private CharacteristicsValueService characteristicsValueService;

    @Inject
    private DriverService driverService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void registerDriver(DriverDetailDTO driver, String unencryptedPassword) {
        Driver driverEntity = getDriverEntityFromDriverDetailDTO(driver);
        if (driver.getCharacteristics().size() == 0) {
            addDefaultCharacteristicValuesToDriver(driverEntity);
        }
        driverService.register(driverEntity, unencryptedPassword);
    }

    @Override
    public boolean authenticate(DriverDetailDTO driver, String password) {
        Driver driverEntity = getDriverEntityFromDriverDetailDTO(driver);
        return driverService.authenticate(driverEntity.getEmail(), password);
    }

    @Override
    public DriverDetailDTO findDriverById(long id) {
        Driver driverEntity = driverService.findById(id);
        return beanMappingService.mapTo(driverEntity, DriverDetailDTO.class);
    }

    @Override
    public DriverDetailDTO findDriverByEmail(String email) {
        Driver driverEntity = driverService.findByEmail(email);
        return beanMappingService.mapTo(driverEntity, DriverDetailDTO.class);
    }

    @Override
    public List<DriverListItemDTO> getAllDrivers() {
        List<Driver> allDriverEntities = driverService.getAll();
        return beanMappingService.mapTo(allDriverEntities, DriverListItemDTO.class);
    }

    @Override
    public List<DriverListItemDTO> getAllDriversByStatus(DriverStatus status) {
        List<Driver> allDriverEntities = driverService.getAllDriversByStatus(status);
        return beanMappingService.mapTo(allDriverEntities, DriverListItemDTO.class);
    }

    @Override
    public DriverDetailDTO findDriverWithHighestCharacteristicsValue(CharacteristicsType characteristicsType) {
        Driver driverEntity = driverService.findDriverWithHighestCharacteristicsValue(characteristicsType);
        return beanMappingService.mapTo(driverEntity, DriverDetailDTO.class);
    }

    @Override
    public DriverDetailDTO updateDriversCharacteristicsValue(CharacteristicsValueDTO characteristicsValueDTO) {
        CharacteristicsValue characteristicsValue = beanMappingService.mapTo(characteristicsValueDTO, CharacteristicsValue.class);
        characteristicsValueService.update(characteristicsValue);
        return beanMappingService.mapTo(driverService.findById(characteristicsValueDTO.getDriver().getId()), DriverDetailDTO.class);
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

    private Driver getDriverEntityFromDriverDetailDTO(DriverDetailDTO driver) {
        return beanMappingService.mapTo(driver, Driver.class);
    }
}
