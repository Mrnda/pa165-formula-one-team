package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ManagerDTO;

import java.util.Collection;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface ManagerFacade {
    
    ManagerDTO findManagerById(Long managerId);

    ManagerDTO findManagerByEmail(String email);

    void registerManager(ManagerDTO manager, String unencryptedPassword);

    Collection<ManagerDTO> getAllManagers();

    boolean authenticate(ManagerDTO manager);
}
