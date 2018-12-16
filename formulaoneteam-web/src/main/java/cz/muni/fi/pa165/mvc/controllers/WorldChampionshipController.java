package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Controller
@RequestMapping("/world-championship")
public class WorldChampionshipController {

    @RequestMapping("/list")
    public String list() {
        return "world-championships/list";
    }
}
