package cz.muni.fi.pa165.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseFacade<DTO extends BaseDTO, E extends BaseEntity> {

    DTO findById(long id);

    List<DTO> getAll();
}
