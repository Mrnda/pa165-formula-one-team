package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Entity
public class CharacteristicsValue extends BaseEntity {

    public final static String TYPE_FIELD = "type";

    @Column
    private double value;

    @Column
    @Enumerated
    private CharacteristicsType type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CharacteristicsType getType() {
        return type;
    }

    public void setType(CharacteristicsType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CharacteristicsValue{" +
                "value=" + getValue() +
                ", type=" + getType() +
                "} " + super.toString();
    }
}
