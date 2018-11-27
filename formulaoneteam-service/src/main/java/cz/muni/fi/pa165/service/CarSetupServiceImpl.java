package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.carsetup.CarSetupDao;
import cz.muni.fi.pa165.entity.CarSetup;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Théo Desjardins
 */
@Service
public class CarSetupServiceImpl implements CarSetupService {

    @Inject
    private CarSetupDao carSetupDao;

    @Override
    public CarSetup findById(long id) {
        return carSetupDao.findById(id);
    }

    @Override
    public void add(CarSetup carSetup) {
        carSetupDao.add(carSetup);
    }

    @Override
    public void update(CarSetup carSetup) {
        carSetupDao.update(carSetup);
    }

    @Override
    public void delete(CarSetup carSetup) {
        carSetupDao.delete(carSetup);
    }

    @Override
    public List<CarSetup> getAllCarSetup() {
        return carSetupDao.findAll();
    }
}
