package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;

import java.util.Date;

/**
 * @author Adel Chakouri
 */
public class TestDriveDTO extends BaseDTO {

    private CarSetup car;
    private Driver driver;
    private String notes;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestDriveDTO)) return false;

        TestDriveDTO that = (TestDriveDTO) o;

        if (car != null ? !car.equals(that.car) : that.car != null) return false;
        if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = car != null ? car.hashCode() : 0;
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestDriveDTO{" +
                "car=" + car +
                ", driver=" + driver +
                ", notes='" + notes + '\'' +
                ", date=" + date +
                '}';
    }
}
