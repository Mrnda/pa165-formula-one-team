package cz.muni.fi.pa165.dto;

import java.util.Objects;

import cz.muni.fi.pa165.dto.base.BaseDTO;

/**
 * @author Ivan Dendis
 */
public class ComponentParameterDTO extends BaseDTO {

    private String name;
    private String value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentParameterDTO)) return false;
        ComponentParameterDTO that = (ComponentParameterDTO) o;
        return that.getValue().equals(getValue()) &&
                that.getName().equals(getName()); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getName());
    }
}
