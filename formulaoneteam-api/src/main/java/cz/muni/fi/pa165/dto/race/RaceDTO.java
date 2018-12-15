package cz.muni.fi.pa165.dto.race;

import cz.muni.fi.pa165.dto.base.BaseDTO;

import java.util.Date;

/**
 * @author Adel Chakouri
 */
public class RaceDTO extends BaseDTO {

    private Date date;
    private String title;
    private String location;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaceDTO)) return false;

        RaceDTO raceDTO = (RaceDTO) o;

        if (date != null ? !date.equals(raceDTO.date) : raceDTO.date != null) return false;
        if (title != null ? !title.equals(raceDTO.title) : raceDTO.title != null) return false;
        return location != null ? location.equals(raceDTO.location) : raceDTO.location == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
