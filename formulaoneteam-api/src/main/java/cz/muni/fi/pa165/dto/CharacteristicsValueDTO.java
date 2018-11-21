package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.enums.CharacteristicsType;

import java.util.Objects;

public class CharacteristicsValueDTO extends BaseDTO {
    private double value;
    private CharacteristicsType type;
    private DriverDetailDTO driver;

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

    public DriverDetailDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDetailDTO driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacteristicsValueDTO)) return false;
        CharacteristicsValueDTO that = (CharacteristicsValueDTO) o;
        return Double.compare(that.getValue(), getValue()) == 0 &&
                getType() == that.getType() &&
                Objects.equals(getDriver(), that.getDriver());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getType(), getDriver());
    }

    @Override
    public String toString() {
        return "CharacteristicsValueDTO{" +
                "id=" + getId() +
                ", value=" + getValue() +
                ", type=" + getType() +
                ", driver=" + getDriver() +
                '}';
    }
}
