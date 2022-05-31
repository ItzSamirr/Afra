package it.itzsamirr.afra.api.event.check;

import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.HashMap;

public class CheckFlagEvent extends CheckEvent implements Cancellable {
    private HashMap<String, Object> info;
    private int cancelType = 0;

    public CheckFlagEvent(IProfile profile, ICheck check, HashMap<String, Object> info) {
        super(profile, check);
    }

    public HashMap<String, Object> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
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
