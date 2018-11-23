package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.DriverStatus;

import java.util.Date;

public class DriverListItemDTO extends UserDTO {

    private String nationality;
    private Date birthday;
    private DriverStatus driverStatus;

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
}
