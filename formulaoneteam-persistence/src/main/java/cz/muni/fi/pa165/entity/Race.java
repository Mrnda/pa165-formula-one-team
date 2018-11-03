package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.apache.derby.vti.Restriction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Adel Chakouri
 */
@Entity
public class Race extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private Date date;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false)
    private String Location;

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
        return Location;
    }

    public void setLocation(String location) {
        this.Location = Location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Race)) return false;
        Race race = (Race) o;

        if (date != null ? !date.equals(race.date) : race.date != null) return false;
        return title != null ? title.equals(race.title) : race.title == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Race{" +
                "date='" + getDate() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", Location='" + getLocation() + '\'' +
                "} " + super.toString();
    }
}
