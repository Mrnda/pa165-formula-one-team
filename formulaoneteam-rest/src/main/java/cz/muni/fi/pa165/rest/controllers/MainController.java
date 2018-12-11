package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@RestController
public class MainController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {
        Map<String, String> resourcesMap = new HashMap<>();

        resourcesMap.put("race_uri", ApiUris.ROOT_URI_RACE);
        resourcesMap.put("test_drive_uri", ApiUris.ROOT_URI_TEST_DRIVE);
        resourcesMap.put("race_participation_uri", ApiUris.ROOT_URI_RACE_PARTICIPATION);

        return Collections.unmodifiableMap(resourcesMap);
    }
}
