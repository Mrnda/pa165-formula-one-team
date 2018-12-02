package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.engineer.EngineerDao;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.service.base.BaseServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Dendis
 */
@Service
public class EngineerServiceImpl extends BaseServiceImpl<Engineer, EngineerDao>
        implements EngineerService {

    @Override
    public void registerEngineer(Engineer engineer, String plainPassword) {
        engineer.setPasswordHash(Validator.createHash(plainPassword));
        dao.add(engineer);
    }

    @Override
    public boolean authenticate(Engineer engineer, String password) {
        return Validator.validatePassword(password, engineer.getPasswordHash());
    }

    @Override
    public Engineer findEngineerByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public void validateEntity(Engineer entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("Engineer entity is null or not configured");
        }
	}
}
