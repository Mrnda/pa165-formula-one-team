package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Date birthdate;

    @Column
    @Enumerated
    private DriverStatus driverStatus;

    @OneToMany(mappedBy = "driver")
    private Set<CharacteristicsValue> characteristics = new HashSet<>();

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Set<CharacteristicsValue> getCharacteristics() {
        return Collections.unmodifiableSet(characteristics);
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
                ", birthdate=" + getBirthdate() +
                ", driverStatus=" + getDriverStatus() +
                ", characteristics=" + getCharacteristics() +
                "} " + super.toString();
    }
}
