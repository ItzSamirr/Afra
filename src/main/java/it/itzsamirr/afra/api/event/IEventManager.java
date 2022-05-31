package it.itzsamirr.afra.api.event;

import it.itzsamirr.afra.api.event.listener.Listener;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.List;

public interface IEventManager {
    void call(Event e);
    List<Listener> getListeners();
    void registerListener(Listener listener);
    void unregisterListener(Class<?> listener);

}
