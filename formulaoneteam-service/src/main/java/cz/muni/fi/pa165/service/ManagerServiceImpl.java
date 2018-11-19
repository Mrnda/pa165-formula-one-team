package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.manager.ManagerDao;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Inject
    private ManagerDao managerDao;

    @Override
    public void registerManager(Manager manager, String unencryptedPassword) {
        manager.setPasswordHash(Validator.createHash(unencryptedPassword));
        managerDao.add(manager);
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerDao.findAll();
    }

    @Override
    public boolean authenticate(Manager manager, String password) {
        return Validator.validatePassword(password, manager.getPasswordHash());
    }

    @Override
    public Manager findManagerById(Long managerId) {
        return managerDao.findById(managerId);
    }

    @Override
    public Manager findManagerByEmail(String email) {
        return managerDao.findByEmail(email);
    }
}
