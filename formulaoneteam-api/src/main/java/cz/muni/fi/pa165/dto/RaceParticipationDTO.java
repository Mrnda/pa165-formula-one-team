package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.Race;

/**
 * @author Adel Chakouri
 */
public class RaceParticipationDTO extends BaseDTO {

    private CarSetupDTO car;
    private DriverDetailDTO driver;
    private RaceDTO race;
    private int resultPosition;

    public CarSetupDTO getCar() {
        return car;
    }

    public void setCar(CarSetupDTO car) {
        this.car = car;
    }

    public DriverDetailDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDetailDTO driver) {
        this.driver = driver;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
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
        if (!(o instanceof RaceParticipationDTO)) return false;

        RaceParticipationDTO that = (RaceParticipationDTO) o;

        if (resultPosition != that.resultPosition) return false;
        if (car != null ? !car.equals(that.car) : that.car != null) return false;
        if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;
        return race != null ? race.equals(that.race) : that.race == null;
    }

    @Override
    public int hashCode() {
        int result = car != null ? car.hashCode() : 0;
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + resultPosition;
        return result;
    }

    @Override
    public String toString() {
        return "RaceParticipationDTO{" +
                "car=" + car +
                ", driver=" + driver +
                ", race=" + race +
                ", resultPosition=" + resultPosition +
                '}';
    }
}
