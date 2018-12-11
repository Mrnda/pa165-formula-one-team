package cz.muni.fi.pa165.rest.controllers.base;

import java.util.Objects;

public class CreatedEntityIdDTO {
    private long id;

    public CreatedEntityIdDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatedEntityIdDTO)) return false;
        CreatedEntityIdDTO that = (CreatedEntityIdDTO) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
