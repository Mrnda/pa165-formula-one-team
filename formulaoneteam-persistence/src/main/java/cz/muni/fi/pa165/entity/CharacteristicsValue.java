package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CharacteristicsValue extends BaseEntity {

    @Column
    private double value;

    @Column
    private CharacteristicsType type;

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
