package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;

import java.util.Objects;

/**
 * @author mrnda (Michal Mrnuštík)
 */

class TestDTO extends BaseDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestDTO)) return false;
        TestDTO testDto = (TestDTO) o;
        return Objects.equals(getName(), testDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
