package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import org.apache.derby.vti.Restriction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Adel Chakouri
 */
@Entity
public class RaceParticipation extends BaseEntity {

    @ManyToOne
    private CarSetup car;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Race race;

    @NotNull
    @Column(nullable = false)
    private int resultPosition;

    public CarSetup getCar() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaceParticipation)) return false;

        RaceParticipation that = (RaceParticipation) o;

        if (getCar() != null ? !getCar().equals(that.getCar()) : that.getCar() != null) return false;
        if (getDriver() != null ? !getDriver().equals(that.getDriver()) : that.getDriver() != null) return false;
        return getRace() != null ? getRace().equals(that.getRace()) : that.getRace() == null;
    }

    @Override
    public int hashCode() {
        int result = getCar() != null ? getCar().hashCode() : 0;
        result = 31 * result + (getDriver() != null ? getDriver().hashCode() : 0);
        result = 31 * result + (getRace() != null ? getRace().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RaceParticipation{" +
                "car='" + getCar() + '\'' +
                ", driver='" + getDriver() + '\'' +
                ", race='" + getRace() + '\'' +
                ",  resultPosition='" + getResultPosition() + '\'' +
                "} " + super.toString();
    }
}
