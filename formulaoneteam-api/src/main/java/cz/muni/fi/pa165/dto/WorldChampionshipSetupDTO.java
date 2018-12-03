package cz.muni.fi.pa165.dto;

import java.util.Date;

public class WorldChampionshipSetupDTO {
    private Date date;
    private String location;
    private CarSetupDTO firstCarSetup;
    private DriverDetailDTO firstDriver;
    private CarSetupDTO secondCarSetup;
    private DriverDetailDTO secondDriver;

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

    public DriverDetailDTO getFirstDriver() {
        return firstDriver;
    }

    public void setFirstDriver(DriverDetailDTO firstDriver) {
        this.firstDriver = firstDriver;
    }

    public CarSetupDTO getSecondCarSetup() {
        return secondCarSetup;
    }

    public void setSecondCarSetup(CarSetupDTO secondCarSetup) {
        this.secondCarSetup = secondCarSetup;
    }

    public DriverDetailDTO getSecondDriver() {
        return secondDriver;
    }

    public void setSecondDriver(DriverDetailDTO secondDriver) {
        this.secondDriver = secondDriver;
    }
}
