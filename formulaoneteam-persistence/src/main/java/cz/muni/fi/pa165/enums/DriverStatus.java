package cz.muni.fi.pa165.enums;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public enum DriverStatus {
    MAIN("Main"),
    TEST("Test");

    private String displayName;

    DriverStatus(String diplayName) {
        this.displayName = diplayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

