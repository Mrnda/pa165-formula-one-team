package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.enums.EngineerSpecialization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Class representing engineer entity.
 *
 * @author Ivan Dendis
 */
@Entity
public class Engineer extends User {

    @NotNull
    @Column(nullable = false)
    @Enumerated
    private EngineerSpecialization specialization;

    protected Engineer() {
    }

    public Engineer(String name, String surname, String email, String password, EngineerSpecialization specialization) {
        super(name, surname, email, password);
        this.specialization = specialization;
    }

    @NotNull
    public EngineerSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(@NotNull EngineerSpecialization specialization) {
        this.specialization = specialization;
    }
}
