package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CarSetupDTO;

import java.util.List;

/**
 * @author Théo Desjardins
 */
public interface CarSetupFacade {

    CarSetupDTO findByID(long id);

    List<CarSetupDTO> getAllCarSetup();

    void deleteCarSetup(CarSetupDTO carSetupDTO);

    void addCarSetup(CarSetupDTO carSetupDTO);

    void updateCarSetup(CarSetupDTO carSetupDTO);
}
