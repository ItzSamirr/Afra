package it.itzsamirr.afra.event;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.AfraAPI;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.event.IEventManager;
import it.itzsamirr.afra.api.event.listener.Listener;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public final class EventManager implements IEventManager {
    private final Afra plugin;
    private final List<Listener> listeners;

    public EventManager(Afra plugin) {
        this.plugin = plugin;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void call(Event e) {
//        Bukkit.getScheduler().runTask(plugin, () -> {
            listeners.forEach(listener -> {
                if(listener.getFilter().isEmpty() || listener.getFilter().contains(e.getClass())) {
                    listener.on(e);
                }
            });
//        });
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
