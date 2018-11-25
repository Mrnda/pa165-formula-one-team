package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.base.BaseDTO;

import java.util.Objects;

/**
 * @author Théo Desjardins
 */
public class CarSetupDTO extends BaseDTO {

    private ComponentDTO engine;
    private ComponentDTO suspension;
    private ComponentDTO brakes;
    private ComponentDTO transmission;
    private ComponentDTO tires;
    private ComponentDTO cover;

    public ComponentDTO getEngine() {
        return engine;
    }

    public ComponentDTO getSuspension() {
        return suspension;
    }

    public ComponentDTO getBrakes() {
        return brakes;
    }

    public ComponentDTO getTransmission() {
        return transmission;
    }

    public ComponentDTO getTires() {
        return tires;
    }

    public ComponentDTO getCover() {
        return cover;
    }

    public void setEngine(ComponentDTO engine) {
        this.engine = engine;
    }

    public void setSuspension(ComponentDTO suspension) {
        this.suspension = suspension;
    }

    public void setBrakes(ComponentDTO brakes) {
        this.brakes = brakes;
    }

    public void setTransmission(ComponentDTO transmission) {
        this.transmission = transmission;
    }

    public void setTires(ComponentDTO tires) {
        this.tires = tires;
    }

    public void setCover(ComponentDTO cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarSetupDTO)) return false;
        CarSetupDTO that = (CarSetupDTO) o;
        return Objects.equals(engine, that.engine) &&
                Objects.equals(suspension, that.suspension) &&
                Objects.equals(brakes, that.brakes) &&
                Objects.equals(transmission, that.transmission) &&
                Objects.equals(tires, that.tires) &&
                Objects.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine, suspension, brakes, transmission, tires, cover);
    }
}
