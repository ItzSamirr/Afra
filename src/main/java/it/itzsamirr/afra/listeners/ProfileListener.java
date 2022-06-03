package it.itzsamirr.afra.listeners;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.event.profile.JoinEvent;
import it.itzsamirr.afra.api.event.profile.QuitEvent;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {
    private final Afra plugin;

    public ProfileListener(Afra plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if(plugin.getProfileManager().getProfile(p) == null) plugin.getProfileManager().addProfile(new Profile(p));
        JoinEvent joinEvent = new JoinEvent(plugin.getProfileManager().getProfile(p));
        plugin.getEventManager().call(joinEvent);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        IProfile profile = plugin.getProfileManager().getProfile(p);
        plugin.getCheckManager().getChecks(ICheck::isEnabled).forEach(check -> {
            check.getVL().remove(profile);
            check.getPreVL().remove(profile);
        });
        QuitEvent quitEvent = new QuitEvent(profile);
        plugin.getEventManager().call(quitEvent);
        plugin.getProfileManager().removeProfile(p);
    }
}
