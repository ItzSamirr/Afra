package it.itzsamirr.afra.api.event.profile;

import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.event.ProfileEvent;
import it.itzsamirr.afra.api.profile.IProfile;
import org.bukkit.Location;

public class MoveEvent extends ProfileEvent implements Cancellable {
    private Location from,to;

    private int cancelType = 0;

    public MoveEvent(IProfile profile, Location from, Location to) {
        super(profile);
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public static final class CancelType{
        public static int NOTHING = 0;
        public static int SETBACK = 1;
        public static int PULLDOWN = 2;

        public static int get(String name){
            switch(name.toUpperCase()){
                case "SETBACK":
                    return SETBACK;
                case "PULLDOWN":
                    return PULLDOWN;
                default:
                    return NOTHING;
            }
        }

        private CancelType(){throw new UnsupportedOperationException();}
    }

    @Override
    public void cancel(int type) {
        this.cancelType = type;
    }

    @Override
    public int getCancelType() {
        return cancelType;
    }
}
