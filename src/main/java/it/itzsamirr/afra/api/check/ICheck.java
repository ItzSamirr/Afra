package it.itzsamirr.afra.api.check;

import it.itzsamirr.afra.api.check.info.ICheckInfo;
import it.itzsamirr.afra.api.check.settings.ICheckSettings;
import it.itzsamirr.afra.api.check.violation.IPreVL;
import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.event.listener.Listener;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.HashMap;

public interface ICheck extends Listener {
    void reload();
    void init();
    ICheckSettings getSettings();
    ICheckInfo getInfo();
    IPreVL getPreVL();
    boolean isEnabled();
    boolean isExperimental();
    boolean isDev();
    void flag(HashMap<String, Object> infoMap, IProfile profile, Cancellable cancellable, int type);
    String generateFormattedInfo(HashMap<String, Object> infoMap);
    default boolean canBypass(IProfile profile) {
        return (boolean) getSettings().getSetting("bypass") && profile.getPlayer().hasPermission("afra.bypass." + getInfo().getName().toLowerCase() + "." + Character.toLowerCase(getInfo().getType())) ||
                profile.getPlayer().hasPermission("afra.bypass.*") || profile.getPlayer().hasPermission("afra.bypass." + getInfo().getName().toLowerCase() + ".*");
    }
}
