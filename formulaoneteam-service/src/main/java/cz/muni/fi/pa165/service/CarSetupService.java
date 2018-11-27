package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.CarSetup;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Théo Desjardins
 */
@Service
public interface CarSetupService {

    CarSetup findById(long id);

    void add(CarSetup carSetup );

    void update(CarSetup carSetup);

    void delete(CarSetup carSetup );

    List<CarSetup> getAllCarSetup();
}
