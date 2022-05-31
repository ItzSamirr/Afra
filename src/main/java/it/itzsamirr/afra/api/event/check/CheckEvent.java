package it.itzsamirr.afra.api.event.check;

import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.event.ProfileEvent;
import it.itzsamirr.afra.api.profile.IProfile;

public abstract class CheckEvent extends ProfileEvent {
    private ICheck check;

    public CheckEvent(IProfile profile, ICheck check) {
        super(profile);
        this.check = check;
    }

    public ICheck getCheck() {
        return check;
    }

    public void setCheck(ICheck check) {
        this.check = check;
    }
}
