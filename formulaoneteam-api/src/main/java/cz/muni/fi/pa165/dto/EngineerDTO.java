package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.EngineerSpecialization;

/**
 * @author Ivan Dendis
 */
public class EngineerDTO extends UserDTO {

    private EngineerSpecialization specialization;

    public EngineerSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(EngineerSpecialization specialization) {
        this.specialization = specialization;
    }
}
