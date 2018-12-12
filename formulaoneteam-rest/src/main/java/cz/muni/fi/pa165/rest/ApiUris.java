package cz.muni.fi.pa165.rest;

/**
 * @author mrnda (Michal Mrnuštík)
 */

public final class ApiUris {
    public static final String ROOT_URI_RACE = "/race";
    public static final String ROOT_URI_TEST_DRIVE = "/test-drive";
    public static final String ROOT_URI_RACE_PARTICIPATION = "/race-participation";
    
    private ApiUris() {
        throw new IllegalAccessError("This constructor should never get called.");
    }
}
