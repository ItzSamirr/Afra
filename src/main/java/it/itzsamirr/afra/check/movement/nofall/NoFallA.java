package it.itzsamirr.afra.check.movement.nofall;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.annotations.Testing;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.profile.flag.NoFallFlagController;
import it.itzsamirr.afra.utils.Distance;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Experimental(dev = true)
public class NoFallA extends Check {
    private int accumulator = 0;
    public NoFallA(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "NoFall", 'A', "Checks if the player is spoofing the client ground");
    }

    @Override
    public void on(Event event) {
        if(!(event instanceof MoveEvent)) return;
        MoveEvent e = (MoveEvent) event;
        if(!isEnabled()) {
            return;
        }
        if(canBypass(e.getProfile())){
            noFlag(e.getProfile());
            return;
        }
        Location from = e.getFrom();
        Location to = e.getTo();
        Distance d = new Distance(from, to);
        IProfile profile = e.getProfile();
        if(profile.getFlagController(NoFallFlagController.class).shouldNotFlag()){
            noFlag(profile);
            return;
        }
        boolean onGroundServer = profile.isOnGround();
        boolean lastOnGroundServer = profile.isLastOnGround();
        boolean onGroundClient = profile.getPlayer().isOnGround();
        if(!onGroundServer && !lastOnGroundServer &&  onGroundClient)
        {
            HashMap<String, Object> infoMap = new HashMap<>();
            infoMap.put("onGroundClient", onGroundClient);
            infoMap.put("onGroundServer", onGroundServer);
            infoMap.put("lastOnGroundServer", onGroundServer);
            flag(infoMap, profile, e, 0);
        }else{
            accumulator += 1;
        }
        if(accumulator >= 2000){
            noFlag(profile);
        }
    }

}
