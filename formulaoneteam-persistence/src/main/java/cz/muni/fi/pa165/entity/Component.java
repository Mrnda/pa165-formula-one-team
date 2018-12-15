package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.enums.ComponentType;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Théo Desjardins
 */
@Entity
public class Component extends BaseEntity {

    @Enumerated
    @Column
    private ComponentType type;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ComponentParameter> parameters = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "engine")
    private Set<CarSetup> carSetups = new HashSet<>();

    public Component(String name, ComponentType type) {
        this.type = type;
        this.name = name;
    }

    protected Component() {
    }

    public Set<CarSetup> getCarSetups() {
        return carSetups;
    }

    public void setCarSetups(Set<CarSetup> carSetups) {
        this.carSetups = carSetups;
    }

    public ComponentType getType() { return type; }

    public String getName() { return name; }

    public Set<ComponentParameter> getParameters() { return Collections.unmodifiableSet(parameters); }

    public void addParameter(ComponentParameter parameter) {
        parameters.add(parameter);
    }

    public void removeParameter(ComponentParameter parameter){
        parameters.remove(parameter);
    }

    public void setType(ComponentType type) { this.type = type;}

    public void setName(String name) { this.name = name;}

    public boolean isConfigured() {
        return !getName().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        Component component = (Component) o;
        return getType() == component.getType() &&
                Objects.equals(getName(), component.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName());
    }

    public String toString() {
        return "Component{" +
                "type='" + getType() + '\'' +
                ", name='" + getName() + '\'' +
                ", parameters='" + getParameters() + '\'' +
                "} " + super.toString();
    }
}
