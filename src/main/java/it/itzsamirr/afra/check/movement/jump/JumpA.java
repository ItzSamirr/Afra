package it.itzsamirr.afra.check.movement.jump;

import com.google.common.collect.Maps;
import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.api.event.profile.MoveEvent;
import it.itzsamirr.afra.profile.flag.JumpFlagController;
import it.itzsamirr.afra.utils.Distance;
import it.itzsamirr.afra.utils.Values;
import org.bukkit.Location;

import java.util.HashMap;

@Experimental(dev = true)
public class JumpA extends Check {
    public JumpA(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Jump", 'A', "Checks if a player jumps longer than usual");
    }

    @Override
    public void on(Event event) {
        if(!isEnabled())return;
        if(event instanceof MoveEvent){
            MoveEvent e = (MoveEvent) event;
            Location from = e.getFrom();
            Location to = e.getTo();
            Distance d = new Distance(from, to);
            IProfile profile = e.getProfile();
            if(profile.getPlayer().getAllowFlight()) return;
            if(profile.getFlagController(JumpFlagController.class).shouldNotFlag())
            {
                if(preVL.get(profile) != .0){
                    preVL.deaccumulate(profile);
                }
                if(preVL.get(profile) < .0){
                    preVL.set(profile, .0);
                }
                return;
            }
            long jumpTicks = profile.getJumpTicks();
            long lastJumpTicks = profile.getLastJumpTicks();
            if(lastJumpTicks != 0){
                if(jumpTicks != 0){
                    if(jumpTicks > Values.MAX_JUMP_TICKS_IN_AIR+profile.getYJumpModifier()){
                        preVL.accumulate(profile);
                        if(preVL.isMax(profile)) {
                            HashMap<String, Object> infoMap = new HashMap<>();
                            infoMap.put("ticks", jumpTicks);
                            infoMap.put("max", Values.MAX_JUMP_TICKS_IN_AIR+profile.getYJumpModifier());
                            flag(infoMap, profile, e, MoveEvent.CancelType.get((String) getSettings().getSetting("cancel-type")));
                        }
                    }
                }else if(preVL.get(profile) != .0){
                    preVL.deaccumulate(profile);
                    if(preVL.get(profile) < .0){
                        preVL.set(profile, .0);
                    }
                }
            }else if(preVL.get(profile) != .0){
                preVL.deaccumulate(profile);
                if(preVL.get(profile) < .0){
                    preVL.set(profile, .0);
                }
            }
        }
    }
}
