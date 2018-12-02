package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Adel Chakouri
 */
@Entity
public class RaceParticipation extends BaseEntity {

    public static final int NO_RESULT_POSITION = -1;

    @ManyToOne
    private CarSetup car;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Race race;

    @NotNull
    @Column(nullable = false)
    private int resultPosition;

    public RaceParticipation(CarSetup car, Driver driver, Race race, @NotNull int resultPosition) {
        this.car = car;
        this.driver = driver;
        this.race = race;
        this.resultPosition = resultPosition;
    }

    protected RaceParticipation() {
    }

    public CarSetup getCarSetup() {
        return car;
    }

    public void setCar(CarSetup car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getResultPosition() {
        return resultPosition;
    }

    public void setResultPosition(int resultPosition) {
        this.resultPosition = resultPosition;
    }

    public boolean isConfigured() {
        return getCarSetup().isConfigured() && getDriver().isConfigured() && getRace().isConfigured();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaceParticipation)) return false;
        RaceParticipation that = (RaceParticipation) o;
        return Objects.equals(car, that.car) &&
                Objects.equals(getDriver(), that.getDriver()) &&
                Objects.equals(getRace(), that.getRace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, getDriver(), getRace());
    }

    @Override
    public String toString() {
        return "RaceParticipation{" +
                "car='" + getCarSetup() + '\'' +
                ", driver='" + getDriver() + '\'' +
                ", race='" + getRace() + '\'' +
                ",  resultPosition='" + getResultPosition() + '\'' +
                "} " + super.toString();
    }
}
