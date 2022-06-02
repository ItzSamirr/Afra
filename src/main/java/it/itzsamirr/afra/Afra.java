package it.itzsamirr.afra;

import it.itzsamirr.afra.api.AfraAPI;
import it.itzsamirr.afra.api.AfraProvider;
import it.itzsamirr.afra.api.check.ICheckManager;
import it.itzsamirr.afra.api.event.IEventManager;
import it.itzsamirr.afra.api.profile.IProfileManager;
import it.itzsamirr.afra.check.CheckManager;
import it.itzsamirr.afra.event.EventManager;
import it.itzsamirr.afra.listeners.MoveListener;
import it.itzsamirr.afra.listeners.ProfileListener;
import it.itzsamirr.afra.profile.Profile;
import it.itzsamirr.afra.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Afra extends JavaPlugin implements AfraAPI {
    private ICheckManager checkManager;
    private IEventManager eventManager;
    private IProfileManager profileManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.eventManager = new EventManager(this);
        this.profileManager = new ProfileManager();
        loadListeners();
        this.checkManager = new CheckManager(this);
        Bukkit.getOnlinePlayers().stream().filter(player -> profileManager.getProfile(player) == null).forEach(player -> profileManager.addProfile(new Profile(player)));
        AfraProvider.setApi(this);
    }

    private void loadListeners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ProfileListener(this), this);
        pm.registerEvents(new MoveListener(this), this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public ICheckManager getCheckManager() {
        return checkManager;
    }

    @Override
    public IEventManager getEventManager() {
        return eventManager;
    }

    @Override
    public IProfileManager getProfileManager() {
        return profileManager;
    }
}
