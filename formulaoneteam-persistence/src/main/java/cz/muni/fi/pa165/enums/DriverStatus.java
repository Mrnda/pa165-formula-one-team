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

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

