package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.DriverDetailDTO;
import cz.muni.fi.pa165.dto.DriverListItemDTO;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class DriverFacadeImpl implements DriverFacade {
    @Inject
    private DriverService driverService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void registerDriver(DriverDetailDTO driver, String unencryptedPassword) {
        Driver driverEntity = getDriverEntityFromDriverDetailDTO(driver);
        driverService.registerDriver(driverEntity, unencryptedPassword);
    }

    @Override
    public boolean authenticate(DriverDetailDTO driver, String password) {
        Driver driverEntity = getDriverEntityFromDriverDetailDTO(driver);
        return driverService.authenticate(driverEntity, password);
    }

    @Override
    public DriverDetailDTO findDriverById(long id) {
        Driver driverEntity = driverService.findDriverById(id);
        return beanMappingService.mapTo(driverEntity, DriverDetailDTO.class);
    }

    @Override
    public DriverDetailDTO findDriverByEmail(String email) {
        Driver driverEntity = driverService.findDriverByEmail(email);
        return beanMappingService.mapTo(driverEntity, DriverDetailDTO.class);
    }

    @Override
    public List<DriverListItemDTO> getAllDrivers() {
        List<Driver> allDriverEntities = driverService.getAllDrivers();
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

    private Driver getDriverEntityFromDriverDetailDTO(DriverDetailDTO driver) {
        return beanMappingService.mapTo(driver, Driver.class);
    }

}
