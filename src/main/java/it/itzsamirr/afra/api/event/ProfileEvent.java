package it.itzsamirr.afra.api.event;

import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.profile.IProfile;

public abstract class ProfileEvent extends Event {
    protected IProfile profile;

    public ProfileEvent(IProfile profile) {
        this.profile = profile;
    }

    public IProfile getProfile() {
        return profile;
    }

    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
