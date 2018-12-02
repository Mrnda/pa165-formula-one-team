package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.facade.ManagerFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
@Transactional
public class ManagerFacadeImpl implements ManagerFacade {

    @Inject
    private ManagerService managerService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public ManagerDTO findManagerById(Long managerId) {
        Manager manager = managerService.findById(managerId);
        return (manager == null) ? null : beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO findManagerByEmail(String email) {
        Manager Manager = managerService.findByEmail(email);
        return (Manager == null) ? null : beanMappingService.mapTo(Manager, ManagerDTO.class);
    }

    @Override
    public void registerManager(ManagerDTO ManagerDTO, String unencryptedPassword) {
        Manager ManagerEntity = beanMappingService.mapTo(ManagerDTO, Manager.class);
        managerService.register(ManagerEntity, unencryptedPassword);
        ManagerDTO.setId(ManagerEntity.getId());
    }

    @Override
    public Collection<ManagerDTO> getAllManagers() {
        return beanMappingService.mapTo(managerService.getAll(), ManagerDTO.class);
    }

    @Override
    public boolean authenticate(ManagerDTO manager) {
        return managerService.authenticate(managerService.findById(manager.getId()), manager.getPassword());
    }
}
