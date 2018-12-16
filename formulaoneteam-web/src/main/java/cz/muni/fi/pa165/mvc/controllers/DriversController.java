package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.DriverFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Controller
@RequestMapping("/drivers")
public class DriversController {

    @Inject
    private DriverFacade driverFacade;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("drivers", driverFacade.getAllDrivers());
        return "drivers/list";
    }
}
