package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Class representing an engineer entity.
 *
 * @author Ivan Dendis
 */
public class Engineer extends User {

    @NotNull
    @Column(nullable = false)
    @Enumerated
    private EngineerSpecialization specialization;

    public EngineerSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(EngineerSpecialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Engineer{"
                + "specialization='" + getSpecialization() + '\''
                + ", " + super.toString()
                + "}";
    }
}
