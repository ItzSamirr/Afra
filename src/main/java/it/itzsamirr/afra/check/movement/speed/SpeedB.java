package it.itzsamirr.afra.check.movement.speed;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.check.Check;

public class SpeedB extends Check {
    public SpeedB(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Speed", 'B');
    }

    @Override
    public void on(Event e) {

    }
}
