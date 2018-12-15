package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;

import javax.persistence.Entity;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Entity
public class Manager extends User {

    public Manager(String name, String surname, String email) {
        super(name, surname, email);
    }

    protected Manager() {
        super();
    }
}
