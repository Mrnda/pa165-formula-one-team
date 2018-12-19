package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import org.springframework.format.annotation.NumberFormat;

import java.util.Objects;

public class CharacteristicsValueDTO extends BaseDTO {

    @NumberFormat(style = NumberFormat.Style.DEFAULT)
    private double value;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacteristicsValueDTO)) return false;
        CharacteristicsValueDTO that = (CharacteristicsValueDTO) o;
        return Double.compare(that.getValue(), getValue()) == 0 &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getType());
    }

    @Override
    public String toString() {
        return "CharacteristicsValueDTO{" +
                "id=" + getId() +
                ", value=" + getValue() +
                ", type=" + getType() +
                '}';
    }
}
