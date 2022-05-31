package it.itzsamirr.afra.api.event.profile;

import it.itzsamirr.afra.api.event.ProfileEvent;
import it.itzsamirr.afra.api.profile.IProfile;

public final class JoinEvent extends ProfileEvent {

    public JoinEvent(IProfile profile) {
        super(profile);
    }
}
