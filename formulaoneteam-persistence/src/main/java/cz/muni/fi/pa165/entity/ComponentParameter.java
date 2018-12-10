package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Class representing a component parameter entity.
 *
 * @author Ivan Dendis
 */
@Entity
public class ComponentParameter extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String name = "";

    @Column
    private String value = "";

    public ComponentParameter() {
    }

    public ComponentParameter(@NotNull String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isConfigured() {
        return !getName().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentParameter)) return false;
        ComponentParameter that = (ComponentParameter) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue(), getId());
    }

    @Override
    public String toString() {
        return "ComponentParameter{"
                + "name='" + getName() + '\''
                + ", value='" + getValue() + '\''
                + "} " + super.toString();
    }
}
