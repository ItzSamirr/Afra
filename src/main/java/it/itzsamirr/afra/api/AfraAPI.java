package it.itzsamirr.afra.api;

import it.itzsamirr.afra.api.check.ICheckManager;
import it.itzsamirr.afra.api.event.IEventManager;
import it.itzsamirr.afra.api.profile.IProfileManager;

import java.util.logging.Logger;

public interface AfraAPI {
    ICheckManager getCheckManager();
    IEventManager getEventManager();
    IProfileManager getProfileManager();
    Logger getLogger();
}
