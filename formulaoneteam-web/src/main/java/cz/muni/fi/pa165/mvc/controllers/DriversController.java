package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drivers")
public class DriversController {

    @RequestMapping("/list")
    public String list() {
        return "drivers/list";
    }
}
