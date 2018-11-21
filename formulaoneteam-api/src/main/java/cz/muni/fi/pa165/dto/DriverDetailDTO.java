package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.DriverStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverDetailDTO extends DriverListItemDTO{
    private List<CharacteristicsValueDTO> characteristics = new ArrayList<>();

    public List<CharacteristicsValueDTO> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicsValueDTO> characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "DriverDetailDTO{" +
                "nationality='" + getNationality() + '\'' +
                ", birthday=" + getBirthday() +
                ", driverStatus=" + getDriverStatus() +
                ", characteristics=" + getCharacteristics() +
                '}';
    }
}
