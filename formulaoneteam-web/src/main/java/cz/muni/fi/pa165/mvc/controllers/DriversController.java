package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.service.date.DateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Controller
@RequestMapping("/drivers")
public class DriversController {

    @Inject
    private DriverFacade driverFacade;

    @Inject
    private DateService dateService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("drivers", driverFacade.getAllDrivers());
        return "drivers/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("driver", driverFacade.findById(id));
        return "drivers/detail";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("driver", driverFacade.createDefaultDriver());
        return "drivers/edit";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("driver", driverFacade.findById(id));
        return "drivers/edit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitDriver(@ModelAttribute("driver") DriverDTO driver) {
        //FIXME: workaround until we have working datepicker
        driver.setBirthday(dateService.getCurrentDate());

        if (driver.getId() == 0) {
            driverFacade.register(driver, driver.getPassword());
        } else {
            //FIXME: missing update method on DriverFacade.
        }
        return "redirect:/drivers/list";
    }

    @ModelAttribute("driverStatusValues")
    public DriverStatus[] getDriverStatusValues() {
        return DriverStatus.values();
    }

}
