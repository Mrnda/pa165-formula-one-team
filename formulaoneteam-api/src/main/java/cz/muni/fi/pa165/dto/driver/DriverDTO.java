package cz.muni.fi.pa165.dto.driver;

import cz.muni.fi.pa165.dto.CharacteristicsValueDTO;
import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.DriverStatus;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverDTO extends UserDTO {

    @NotEmpty
    private String nationality;
    private Date birthday;
    private String birthdayString;
    private DriverStatus driverStatus;
    private List<RaceParticipationDTO> raceParticipations;
    private List<TestDriveDTO> testDrives;
    private List<CharacteristicsValueDTO> characteristics = new ArrayList<>();

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

    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
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

    public List<TestDriveDTO> getTestDrives() {
        return testDrives;
    }

    public void setTestDrives(List<TestDriveDTO> testDrives) {
        this.testDrives = testDrives;
    }

    public List<CharacteristicsValueDTO> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicsValueDTO> characteristics) {
        this.characteristics = characteristics;
    }
}
