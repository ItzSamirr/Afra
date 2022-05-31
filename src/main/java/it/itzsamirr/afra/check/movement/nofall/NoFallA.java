package it.itzsamirr.afra.check.movement.nofall;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.annotations.Testing;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.utils.Distance;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Experimental(dev = true)
public class NoFallA extends Check {
    public NoFallA(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "NoFall", 'A', "Checks if the player is spoofing the onground value when falling");
    }

    @Override
    public void on(Event event) {
        MoveEvent e = (MoveEvent) event;
        if(!isEnabled()) {
            if(preVL.get(e.getProfile()) != .0) preVL.set(e.getProfile(), .0);
            return;
        }
        if(canBypass(e.getProfile())){
            if(preVL.get(e.getProfile()) != .0) preVL.set(e.getProfile(), .0);
            return;
        }
        Location from = e.getFrom();
        Location to = e.getTo();
        Distance d = new Distance(from, to);
        IProfile profile = e.getProfile();
        boolean onGroundServer = profile.isLastOnGround();
        boolean onGroundClient = profile.getPlayer().isOnGround();
        if(onGroundServer)
        {
            HashMap<String, Object> infoMap = new HashMap<>();
            infoMap.put("onGroundClient", onGroundClient);
            infoMap.put("onGroundServer", onGroundServer);
            flag(infoMap, profile, e, 0);
        }
    }

    @Override
    public List<Class<? extends Event>> getFilter() {
        return Arrays.asList(MoveEvent.class);
    }
}
