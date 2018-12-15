package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adel Chakouri
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TEST_DRIVE)
public class TestDriveController extends BaseEntityController<TestDriveFacade, TestDriveDTO, TestDrive> {
}
