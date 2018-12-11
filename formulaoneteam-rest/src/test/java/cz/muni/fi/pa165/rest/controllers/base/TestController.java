package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.facade.base.BaseEntityFacade;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@RestController
class TestController extends BaseCrudController<BaseEntityFacade<TestDTO, TestEntity>, TestDTO, TestEntity> {

}
