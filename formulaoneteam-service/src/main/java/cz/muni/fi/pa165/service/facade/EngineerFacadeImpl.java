package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.EngineerDTO;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.facade.EngineerFacade;
import cz.muni.fi.pa165.service.EngineerService;
import cz.muni.fi.pa165.service.facade.base.BaseUserFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Dendis
 */
@Service
@Transactional
public class EngineerFacadeImpl
    extends BaseUserFacadeImpl<EngineerDTO, Engineer, EngineerService>
    implements EngineerFacade {

    @Override
    protected Class<EngineerDTO> getDtoClass() {
        return EngineerDTO.class;
    }

    @Override
    protected Class<Engineer> getEntityClass() {
        return Engineer.class;
    }
}
