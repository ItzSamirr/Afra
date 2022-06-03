package it.itzsamirr.afra.check.movement.speed;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.annotations.Testing;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
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
public class SpeedA extends Check {
    public SpeedA(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Speed", 'A', "Checks if the player is not following the friction rules in air");
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
        if((!profile.isNearGround() && !profile.isLastNearGround()) || (!profile.isOnGround() && !profile.isLastOnGround())) {
            final double lastdxz = profile.getLastDistance().getDXZ();
            final double dxz = d.getDXZ();
            final double appliedLastDxz = lastdxz * Values.FRICTION;
            final double equalness = (dxz - appliedLastDxz) * 137;
            if(equalness >= 1.0)
            {
                preVL.accumulate(profile);
                if(preVL.isMax(profile)) {
                    HashMap<String, Object> infoMap = new HashMap<>();
                    infoMap.put("frictionAppliedLastDeltaXZ", new BigDecimal(appliedLastDxz).setScale(5, RoundingMode.HALF_UP).doubleValue());
                    infoMap.put("equalness", new BigDecimal(equalness).setScale(5, RoundingMode.HALF_UP).doubleValue());
                    flag(infoMap, profile, e, MoveEvent.CancelType.get((String) getSettings().getSetting("cancel-type")));
                }
            }else{
                noFlag(profile);
            }
        }

    }
}
