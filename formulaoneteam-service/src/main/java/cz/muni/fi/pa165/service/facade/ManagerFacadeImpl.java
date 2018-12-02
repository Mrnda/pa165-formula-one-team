package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.facade.ManagerFacade;
import cz.muni.fi.pa165.service.ManagerService;
import cz.muni.fi.pa165.service.facade.base.BaseUserFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
@Transactional
public class ManagerFacadeImpl
        extends BaseUserFacadeImpl<ManagerDTO, Manager, ManagerService>
        implements ManagerFacade {

    @Override
    protected Class<ManagerDTO> getDtoClass() {
        return ManagerDTO.class;
    }

    @Override
    protected Class<Manager> getEntityClass() {
        return Manager.class;
    }
}
