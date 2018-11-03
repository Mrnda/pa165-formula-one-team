package cz.muni.fi.pa165.entity.base;

import cz.muni.fi.pa165.entity.component.Component;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Théo Desjardins
 */
@Entity
public class CarSetup extends BaseEntity {

    @OneToOne
    private Component engine;

    @OneToOne
    private Component suspension;

    @OneToOne
    private Component brakes;

    @OneToOne
    private Component transmission;

    @OneToOne
    private Component tires;

    @OneToOne
    private Component cover;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarSetup)) return false;

        CarSetup carsetup = (CarSetup) o;

        return getId() == carsetup.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(getId());
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
