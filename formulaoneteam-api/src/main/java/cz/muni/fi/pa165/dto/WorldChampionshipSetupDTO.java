package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;

import java.util.Date;
import java.util.Objects;

public class WorldChampionshipSetupDTO {
    private Date date;
    private String location;
    private CarSetupDTO firstCarSetup;
    private DriverDTO firstDriver;
    private CarSetupDTO secondCarSetup;
    private DriverDTO secondDriver;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CarSetupDTO getFirstCarSetup() {
        return firstCarSetup;
    }

    public void setFirstCarSetup(CarSetupDTO firstCarSetup) {
        this.firstCarSetup = firstCarSetup;
    }

    public DriverDTO getFirstDriver() {
        return firstDriver;
    }

    public void setFirstDriver(DriverDTO firstDriver) {
        this.firstDriver = firstDriver;
    }

    public CarSetupDTO getSecondCarSetup() {
        return secondCarSetup;
    }

    public void setSecondCarSetup(CarSetupDTO secondCarSetup) {
        this.secondCarSetup = secondCarSetup;
    }

    public DriverDTO getSecondDriver() {
        return secondDriver;
    }

    public void setSecondDriver(DriverDTO secondDriver) {
        this.secondDriver = secondDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldChampionshipSetupDTO)) return false;
        WorldChampionshipSetupDTO that = (WorldChampionshipSetupDTO) o;
        return Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getFirstCarSetup(), that.getFirstCarSetup()) &&
                Objects.equals(getFirstDriver(), that.getFirstDriver()) &&
                Objects.equals(getSecondCarSetup(), that.getSecondCarSetup()) &&
                Objects.equals(getSecondDriver(), that.getSecondDriver());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getLocation(), getFirstCarSetup(), getFirstDriver(), getSecondCarSetup(), getSecondDriver());
    }
}
