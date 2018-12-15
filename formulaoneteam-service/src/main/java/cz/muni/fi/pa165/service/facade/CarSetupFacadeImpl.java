package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Théo Desjardins
 */
@Service
@Transactional
public class CarSetupFacadeImpl
        extends BaseEntityFacadeImpl<CarSetupDTO, CarSetup, CarSetupService>
        implements CarSetupFacade {

    @Override
    protected Class<CarSetupDTO> getDtoClass() {
        return CarSetupDTO.class;
    }

    @Override
    protected Class<CarSetup> getEntityClass() {
        return CarSetup.class;
    }
}
