package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
public interface ManagerService {

    void registerManager(Manager manager, String unencryptedPassword);

    List<Manager> getAllManagers();

    boolean authenticate(Manager manager, String password);

    Manager findManagerById(Long managerId);

    Manager findManagerByEmail(String email);
}
