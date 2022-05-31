package it.itzsamirr.afra.check.movement.jump;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.api.event.profile.MoveEvent;

import java.util.Arrays;
import java.util.List;

@Experimental(dev = true)
public class JumpB extends Check {
    public JumpB(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Jump", 'B', "Checks if a player jumps to higher than usual");
    }

    @Override
    public void on(Event event) {
        MoveEvent e = (MoveEvent) event;

    }

    @Override
    public List<Class<? extends Event>> getFilter() {
        return Arrays.asList(MoveEvent.class);
    }
}
