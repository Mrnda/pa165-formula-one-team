package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Class representing a component parameter entity.
 *
 * @author Ivan Dendis
 */
@Entity
public class ComponentParameter extends BaseEntity {

    @NotNull
    @Column(nullable = false, unique = true)
    private String name = "";

    @Column
    private double value = 0;

    public ComponentParameter() {
    }

    public ComponentParameter(@NotNull String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isConfigured() {
        return !getName().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentParameter)) return false;

        ComponentParameter comp = (ComponentParameter) o;

        return getName().equals(comp.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "ComponentParameter{"
                + "name='" + getName() + '\''
                + ", value='" + getValue() + '\''
                + "} " + super.toString();
    }
}
