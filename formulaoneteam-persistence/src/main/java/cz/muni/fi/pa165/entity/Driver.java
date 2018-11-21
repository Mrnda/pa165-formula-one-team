package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.enums.DriverStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Entity
public class Driver extends User {

    @NotNull
    @Column(nullable = false)
    private String nationality;

    @Basic
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date birthday;

    @Column
    @Enumerated
    private DriverStatus driverStatus;

    @OneToMany(mappedBy = "driver")
    private List<CharacteristicsValue> characteristics = new ArrayList<>();

    public Driver(
            String name,
            String surname,
            String email,
            String password,
            @NotNull String nationality,
            @NotNull Date birthday,
            DriverStatus driverStatus,
            List<CharacteristicsValue> characteristics
    ) {
        super(name, surname, email, password);
        this.nationality = nationality;
        this.birthday = birthday;
        this.driverStatus = driverStatus;
        this.characteristics = characteristics;
    }

    protected Driver() {
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public List<CharacteristicsValue> getCharacteristics() {
        return Collections.unmodifiableList(characteristics);
    }

    public void addCharacteristic(CharacteristicsValue characteristicsValue) {
        characteristics.add(characteristicsValue);
    }

    public void removeCharacteristics(CharacteristicsValue characteristicsValue) {
        characteristics.remove(characteristicsValue);
    }

    @Override
    public boolean isConfigured() {
        return super.isConfigured()
                && hasNationality();
    }

    private boolean hasNationality() {
        return !getNationality().isEmpty();
    }

    @Override
    public String toString() {
        return "Driver{" +
                "nationality='" + getNationality() + '\'' +
                ", birthdate=" + getBirthday() +
                ", driverStatus=" + getDriverStatus() +
                ", characteristics=" + getCharacteristics() +
                "} " + super.toString();
    }
}
