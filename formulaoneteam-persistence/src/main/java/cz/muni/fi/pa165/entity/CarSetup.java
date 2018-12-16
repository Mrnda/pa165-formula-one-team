package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.*;

/**
 * @author Théo Desjardins
 */
@Entity
public class CarSetup extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Component engine;

    @ManyToOne(cascade = CascadeType.ALL)
    private Component suspension;

    @ManyToOne(cascade = CascadeType.ALL)
    private Component brakes;

    @ManyToOne(cascade = CascadeType.ALL)
    private Component transmission;

    @ManyToOne(cascade = CascadeType.ALL)
    private Component tires;

    @ManyToOne(cascade = CascadeType.ALL)
    private Component cover;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carSetup")
    private Set<RaceParticipation> raceParticipations = new HashSet<>();

    public CarSetup(
            Component engine,
            Component suspension,
            Component brakes,
            Component transmission,
            Component tires,
            Component cover
    ) {
        this.engine = engine;
        this.suspension = suspension;
        this.brakes = brakes;
        this.transmission = transmission;
        this.tires = tires;
        this.cover = cover;
    }

    protected CarSetup() {
    }

    public Set<RaceParticipation> getRaceParticipations() {
        return raceParticipations;
    }

    public void setRaceParticipations(Set<RaceParticipation> raceParticipations) {
        this.raceParticipations = raceParticipations;
    }

    public Component getEngine() {
        return engine;
    }

    public Component getSuspension() {
        return suspension;
    }

    public Component getBrakes() {
        return brakes;
    }

    public Component getTransmission() {
        return transmission;
    }

    public Component getTires() {
        return tires;
    }

    public Component getCover() {
        return cover;
    }

    public void setEngine(Component engine) {
        this.engine = engine;
    }

    public void setSuspension(Component suspension) {
        this.suspension = suspension;
    }

    public void setBrakes(Component brakes) {
        this.brakes = brakes;
    }

    public void setTransmission(Component transmission) {
        this.transmission = transmission;
    }

    public void setTires(Component tires) {
        this.tires = tires;
    }

    public void setCover(Component cover) {
        this.cover = cover;
    }

    public List<Component> getComponents() {
        return Arrays.asList(getEngine(), getBrakes(), getCover(), getSuspension(), getTransmission(), getTires());
    }

    public boolean isConfigured() {
        return getEngine().isConfigured()
                && getSuspension().isConfigured()
                && getBrakes().isConfigured()
                && getTransmission().isConfigured()
                && getTires().isConfigured()
                && getCover().isConfigured();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarSetup)) return false;
        CarSetup carSetup = (CarSetup) o;
        return Objects.equals(getEngine(), carSetup.getEngine()) &&
                Objects.equals(getSuspension(), carSetup.getSuspension()) &&
                Objects.equals(getBrakes(), carSetup.getBrakes()) &&
                Objects.equals(getTransmission(), carSetup.getTransmission()) &&
                Objects.equals(getTires(), carSetup.getTires()) &&
                Objects.equals(getCover(), carSetup.getCover());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEngine(), getSuspension(), getBrakes(), getTransmission(), getTires(), getCover());
    }

    public String toString() {
        return "CarSetup{" +
                "engine='" + getEngine() + '\'' +
                ", suspension='" + getSuspension() + '\'' +
                ", brakes='" + getBrakes() + '\'' +
                ", transmission='" + getTransmission() + '\'' +
                ", tires='" + getTires() + '\'' +
                ", cover='" + getCover() + '\'' +
                "} " + super.toString();
    }
}
