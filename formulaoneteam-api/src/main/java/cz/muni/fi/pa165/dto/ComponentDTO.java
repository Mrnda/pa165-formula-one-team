package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.enums.ComponentType;

import java.util.Objects;
import java.util.Set;

/**
 * @author Théo Desjardins
 */
public class ComponentDTO extends BaseDTO {

    private ComponentType type;
    private String name;
    private Set<ComponentParameterDTO> parameters;

    public ComponentType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Set<ComponentParameterDTO> getParameters() {
        return this.parameters;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(Set<ComponentParameterDTO> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentDTO)) return false;
        ComponentDTO that = (ComponentDTO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, parameters);
    }
}
