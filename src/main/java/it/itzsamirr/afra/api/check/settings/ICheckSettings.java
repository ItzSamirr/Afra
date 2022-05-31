package it.itzsamirr.afra.api.check.settings;

import it.itzsamirr.afra.api.check.ICheck;

import java.util.HashMap;

public interface ICheckSettings {
    Object getSetting(String name);
    void setSetting(String name, Object o);
    HashMap<String, Object> getSettings();
    ICheck getParent();
}
