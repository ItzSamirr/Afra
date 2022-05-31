package it.itzsamirr.afra.api.check;


public enum CheckCategory {
    COMBAT("Combat"), MOVEMENT("Movement"), PLAYER("Player"), EXPLOIT("Exploit");

    String displayName;

    CheckCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
