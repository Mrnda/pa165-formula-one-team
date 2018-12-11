package cz.muni.fi.pa165.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseEntityFacade<DTO extends BaseDTO, E extends BaseEntity>
        extends BaseFacade<DTO, E> {

    long add(DTO dto);

    void remove(DTO dto);

    void update(DTO dto);
}
