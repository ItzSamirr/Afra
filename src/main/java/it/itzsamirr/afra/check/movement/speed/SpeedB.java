package it.itzsamirr.afra.check.movement.speed;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.annotations.Testing;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.profile.flag.SpeedFlagController;
import it.itzsamirr.afra.utils.Distance;
import it.itzsamirr.afra.utils.Values;
import org.bukkit.Location;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Experimental(dev = true)
@Testing
public class SpeedB extends Check {
    public SpeedB(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Speed", 'B');
    }

    @Override
    public List<Class<? extends Event>> getFilter() {
        return Arrays.asList(MoveEvent.class);
    }

    @Override
    public void on(Event event) {
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
        if(profile.getFlagController(SpeedFlagController.class).shouldNotFlag())
        {
            noFlag(profile);
            return;
        }
        final double lastDXZ = profile.getLastDistance().getDXZ();
        final double dXZ = d.getDXZ();
        final float dYaw = d.getDYaw();
        final double acc = Math.abs(dXZ - lastDXZ);
        final double squaredAcc = acc*100;
        final double lastAcc = profile.getLastAcceleration();
        if(dYaw > 1.5f && dXZ > .15D && acc < 1.0E-5){
            preVL.accumulate(profile);
            if(preVL.isMax(profile)){
                HashMap<String, Object> infoMap = new HashMap<>();
                infoMap.put("DY", dYaw);
                infoMap.put("accel", squaredAcc);
                infoMap.put("deltaXZ", dXZ);
                flag(infoMap, profile, e, 0);
            }
        }else{
            noFlag(profile);
        }
    }
}
