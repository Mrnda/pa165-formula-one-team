package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.enums.CharacteristicsType;
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

    @OneToMany(fetch = FetchType.EAGER)
    private Set<CharacteristicsValue> characteristics = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private Collection<RaceParticipation> raceParticipations;

    public Driver(
            String name,
            String surname,
            String email,
            @NotNull String nationality,
            @NotNull Date birthday,
            DriverStatus driverStatus
    ) {
        super(name, surname, email);
        this.nationality = nationality;
        this.birthday = birthday;
        this.driverStatus = driverStatus;
    }

    public Driver(
            String name,
            String surname,
            String email,
            @NotNull String nationality,
            @NotNull Date birthday,
            DriverStatus driverStatus,
            Set<CharacteristicsValue> characteristics
    ) {
        this(name, surname, email, nationality, birthday, driverStatus);
        this.characteristics = characteristics;
    }

    protected Driver() {
    }

    public Collection<RaceParticipation> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(Collection<RaceParticipation> raceParticipations) {
        this.raceParticipations = raceParticipations;
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

    public Set<CharacteristicsValue> getCharacteristics() {
        return Collections.unmodifiableSet(characteristics);
    }

    public CharacteristicsValue getCharacteristicOfType(CharacteristicsType type) {
        return characteristics.stream()
                .filter(characteristicsValue -> characteristicsValue.getType() == type)
                .findFirst()
                .orElse(new CharacteristicsValue(type, 0));
    }

    public void addCharacteristic(CharacteristicsValue characteristicsValue) {
        characteristics.add(characteristicsValue);
    }

    public void addCharacteristics(Set<CharacteristicsValue> characteristicsValue) {
        characteristics.addAll(characteristicsValue);
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
