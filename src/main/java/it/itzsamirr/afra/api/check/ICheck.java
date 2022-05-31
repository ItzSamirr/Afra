package it.itzsamirr.afra.api.check;

import it.itzsamirr.afra.api.check.info.ICheckInfo;
import it.itzsamirr.afra.api.check.settings.ICheckSettings;
import it.itzsamirr.afra.api.check.violation.IPreVL;
import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.event.listener.Listener;
import it.itzsamirr.afra.api.profile.IProfile;

public interface ICheck extends Listener {
    void reload();
    void init();
    ICheckSettings getSettings();
    ICheckInfo getInfo();
    IPreVL getPreVL();
    boolean isEnabled();
    boolean isExperimental();
    boolean isDev();
    void flag(String msg, IProfile profile, Cancellable cancellable, int type);

}
