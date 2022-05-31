package it.itzsamirr.afra.check.movement.speed;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.annotations.Testing;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
import it.itzsamirr.afra.utils.Distance;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

@Experimental(dev = true)
public class SpeedA extends Check {
    public SpeedA(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Speed", 'A', "Checks if the player is going too fast");
    }

    @Override
    public void on(Event event) {
        if(!(event instanceof MoveEvent)) return;
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
        final double dxz = d.getDXZ();
        final float dYaw = d.getDYaw();
        final double lastDxz = profile.getLastDistance().getDXZ();
        final double acc = Math.abs(dxz - lastDxz);

//        if(dYaw > 1.5f && dxz > .15D){
            profile.sendMessage(String.valueOf(acc));
//        }
    }
}
