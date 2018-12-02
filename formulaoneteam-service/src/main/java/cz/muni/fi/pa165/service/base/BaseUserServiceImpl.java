package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.UserDao;
import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.service.facade.base.BaseUserService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.utils.Validator;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseUserServiceImpl<T extends User, D extends UserDao<T>>
        extends BaseServiceImpl<T, D> implements BaseUserService<T> {

    @Override
    public void register(T user, String unencryptedPassword) {
        if (unencryptedPassword == null || unencryptedPassword.isEmpty()) {
            throw new FormulaOneTeamException("Password can't be empty");
        }
        validateEntity(user);

        user.setPasswordHash(Validator.createHash(unencryptedPassword));
        validateEntity(user);
        dao.add(user);
    }

    @Override
    public boolean authenticate(String email, String password) {
        return Validator.validatePassword(password, dao.findByEmail(email).getPasswordHash());
    }

    @Override
    public T findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
