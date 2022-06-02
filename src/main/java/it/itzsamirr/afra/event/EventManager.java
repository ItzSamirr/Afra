package it.itzsamirr.afra.event;

import it.itzsamirr.afra.api.AfraAPI;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.event.IEventManager;
import it.itzsamirr.afra.api.event.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public final class EventManager implements IEventManager {
    private final AfraAPI api;
    private final List<Listener> listeners;

    public EventManager(AfraAPI api) {
        this.api = api;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void call(Event e) {
        listeners.forEach(listener -> {
            if(listener.getFilter().isEmpty() || listener.getFilter().contains(e.getClass())) {
                listener.on(e);
            }
        });
    }

    @Override
    public List<Listener> getListeners() {
        return null;
    }

    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Class<?> listener) {
        listeners.removeIf(l -> l.getClass().isAssignableFrom(listener));
    }
}
