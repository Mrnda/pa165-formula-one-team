package cz.muni.fi.pa165.entity.component;

import javax.persistence.Entity;
import javax.persistence.Id;

// TODO Update temporal class
@Entity
public class ComponentParameter {
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

