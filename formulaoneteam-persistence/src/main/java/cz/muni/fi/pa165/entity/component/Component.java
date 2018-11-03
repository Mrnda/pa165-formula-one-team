package cz.muni.fi.pa165.entity.component;

import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.enums.ComponentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
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

    @OneToMany
    private Set<ComponentParameter> parameters;

    public ComponentType getType() { return type; }

    public String getName() { return name; }

    public Set<ComponentParameter> getParameters() { return parameters; }

    public void setType(ComponentType type) { this.type = type;}

    public void setName(String name) { this.name = name;}

    public void setParameters(Set<ComponentParameter> parameters) { this.parameters = parameters;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;

        Component component = (Component) o;

        return getId() == component.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(getId());
    }

    public String toString() {
        return "Component{" +
                "type='" + getType() + '\'' +
                ", name='" + getName() + '\'' +
                ", parameters='" + getParameters() + '\'' +
                "} " + super.toString();
    }
}
