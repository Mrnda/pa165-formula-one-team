package cz.muni.fi.pa165.enums;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public enum CharacteristicsType {
    AGGRESSIVITY("Aggressivity"),
    DRIVING_ON_WET("Driving on wet"),
    ENDURANCE("Endurance"),
    PATIENCE("Patience"),
    STEERING("Steering");

    private String displayName;

    CharacteristicsType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }}


