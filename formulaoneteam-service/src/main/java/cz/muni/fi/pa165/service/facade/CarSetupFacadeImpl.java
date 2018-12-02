package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.CarSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CarSetupService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Théo Desjardins
 */
public class CarSetupFacadeImpl implements CarSetupFacade {

    @Inject
    private CarSetupService carSetupService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public CarSetupDTO findByID(long id) {
        CarSetup carSetup = carSetupService.findById(id);
        return (carSetup == null) ? null : beanMappingService.mapTo(carSetup, CarSetupDTO.class);
    }

    @Override
    public List<CarSetupDTO> getAllCarSetup() {
        List<CarSetup> allCarSetupEntities = carSetupService.getAllCarSetup();
        return beanMappingService.mapTo(allCarSetupEntities, CarSetupDTO.class);
    }

    @Override
    public void deleteCarSetup(CarSetupDTO carSetupDTO) {
        if (carSetupDTO == null) throw new IllegalArgumentException("null carSetupDTO, cannot delete");

        carSetupService.delete(beanMappingService.mapTo(carSetupDTO, CarSetup.class));
    }

    @Override
    public void addCarSetup(CarSetupDTO carSetupDTO) {
        if (carSetupDTO == null) throw new IllegalArgumentException("null carSetupDTO, cannot add");

        CarSetup carSetup = beanMappingService.mapTo(carSetupDTO, CarSetup.class);
        carSetupService.add(carSetup);
    }

    @Override
    public void updateCarSetup(CarSetupDTO carSetupDTO) {
        if (carSetupDTO == null) throw new IllegalArgumentException("null carSetupDTO, cannot update");
        carSetupService.update(beanMappingService.mapTo(carSetupDTO, CarSetup.class));
    }
}
