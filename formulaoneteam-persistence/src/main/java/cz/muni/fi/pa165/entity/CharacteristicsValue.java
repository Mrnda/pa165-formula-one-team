package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.enums.CharacteristicsType;

import javax.persistence.*;
import java.util.Objects;

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

    public CharacteristicsValue() {
    }

    public CharacteristicsValue(CharacteristicsType type, double value, Driver driver) {
        this.value = value;
        this.type = type;
        this.driver = driver;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacteristicsValue)) return false;
        CharacteristicsValue that = (CharacteristicsValue) o;
        return Double.compare(that.getValue(), getValue()) == 0 &&
                getType() == that.getType() &&
                Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getType(), driver);
    }

    @Override
    public String toString() {
        return "CharacteristicsValue{" +
                "value=" + getValue() +
                ", type=" + getType() +
                "} " + super.toString();
    }
}
