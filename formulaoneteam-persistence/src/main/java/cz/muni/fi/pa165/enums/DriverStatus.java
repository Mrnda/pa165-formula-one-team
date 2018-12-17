package cz.muni.fi.pa165.enums;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public enum DriverStatus {
    MAIN("Main"),
    TEST("Test");

    private String displayName;

    DriverStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

