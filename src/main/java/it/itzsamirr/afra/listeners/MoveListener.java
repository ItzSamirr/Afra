package it.itzsamirr.afra.listeners;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
    private final Afra plugin;

    public MoveListener(Afra plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void move(PlayerMoveEvent e){
        IProfile profile = plugin.getProfileManager().getProfile(e.getPlayer());
        if(e.getTo().getY() > e.getFrom().getY() && !e.getTo().getBlock().isLiquid())
        {
            profile.setJumpTicks(profile.getJumpTicks()+1);
        }else{
            profile.setJumpTicks(0);
        }
        if(e.getPlayer().getAllowFlight()) {
            if(profile.getJumpTicks() != 0)
            {
                profile.setJumpTicks(0);
            }
        }
        if(profile.isOnGround())
        {
            profile.setGroundTicks(profile.getGroundTicks()+1);
        }else{
            profile.setGroundTicks(0);
        }
        MoveEvent moveEvent = new MoveEvent(profile, e.getFrom(), e.getTo());
        plugin.getEventManager().call(moveEvent);
        switch(moveEvent.getCancelType())
        {
            case 1:
                e.setTo(e.getFrom());
                if(e.getFrom().getBlock().getType().isSolid())
                {
                    profile.setJumpTicks(0);
                }
                break;
            case 2:
                e.setTo(e.getPlayer().getWorld().getHighestBlockAt(e.getFrom()).getLocation());
                profile.setJumpTicks(0);
                break;
        }
        profile.setLastOnGround(profile.isOnGround());
        profile.setLastGroundTicks(profile.getGroundTicks());
        profile.setLastJumpTicks(profile.getJumpTicks());
    }
}
