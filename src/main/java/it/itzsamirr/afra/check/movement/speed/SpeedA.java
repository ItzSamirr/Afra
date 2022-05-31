package it.itzsamirr.afra.check.movement.speed;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.check.Check;
import it.itzsamirr.afra.api.event.profile.MoveEvent;

import java.util.Arrays;
import java.util.List;

public class SpeedA extends Check {
    public SpeedA(Afra plugin) {
        super(plugin, CheckCategory.MOVEMENT, "Speed", 'A', "Checks if the player is going too fast");
    }

    @Override
    public void on(Event e) {

    }

    @Override
    public List<Class<? extends Event>> getFilter() {
        return Arrays.asList(MoveEvent.class);
    }
}
