package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_CAR)
public class CarController extends BaseEntityController<CarSetupFacade, CarSetupDTO, CarSetup> {
}
