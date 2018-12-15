package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author Adel Chakouri
 */
@Entity
public class TestDrive extends BaseEntity {

    @Column(nullable = false)
    @Basic
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date date;

    @ManyToOne(optional = false)
    private CarSetup car;

    @ManyToOne(optional = false)
    private Driver driver;

    @NotNull
    @Column(nullable = false)
    private String notes;

    public TestDrive(CarSetup car, Driver driver, @NotNull String notes, @NotNull Date date) {
        this.date = date;
        this.car = car;
        this.driver = driver;
        this.notes = notes;
    }

    protected TestDrive() {
    }

    public CarSetup getCarSetup() {
        return car;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setCar(CarSetup car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isConfigured() {
        return !getNotes().isEmpty() && getCarSetup().isConfigured() && getDriver().isConfigured();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestDrive)) return false;
        TestDrive testDrive = (TestDrive) o;
        return Objects.equals(getDate(), testDrive.getDate()) &&
                Objects.equals(car, testDrive.car) &&
                Objects.equals(getDriver(), testDrive.getDriver()) &&
                Objects.equals(getNotes(), testDrive.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), car, getDriver(), getNotes());
    }

    @Override
    public String toString() {
        return "TestDrive{" +
                "car='" + getCarSetup() + '\'' +
                ", driver='" + getDriver() + '\'' +
                ", notes='" + getNotes() + '\'' +
                "} " + super.toString();
    }
}
