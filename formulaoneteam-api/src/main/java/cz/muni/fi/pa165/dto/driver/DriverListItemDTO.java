package cz.muni.fi.pa165.dto.driver;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.DriverStatus;

import java.util.Date;
import java.util.List;

public class DriverListItemDTO extends UserDTO {

    private String nationality;
    private Date birthday;
    private DriverStatus driverStatus;
    private List<RaceParticipationDTO> raceParticipations;

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

    public List<RaceParticipationDTO> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(List<RaceParticipationDTO> raceParticipations) {
        this.raceParticipations = raceParticipations;
    }
}
