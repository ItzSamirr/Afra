package it.itzsamirr.afra.api.check;

import it.itzsamirr.afra.api.check.info.ICheckInfo;
import it.itzsamirr.afra.api.check.settings.ICheckSettings;
import it.itzsamirr.afra.api.check.violation.IPreVL;
import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.event.listener.Listener;

public interface ICheck extends Listener {
    void reload();
    void init();
    ICheckSettings getSettings();
    ICheckInfo getInfo();
    IPreVL getPreVL();
    boolean isEnabled();
    boolean isExperimental();
    boolean isDev();
    void flag(String msg, Cancellable cancellable, int type);

}
