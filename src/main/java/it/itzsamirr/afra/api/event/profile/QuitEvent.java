package it.itzsamirr.afra.api.event.profile;

import it.itzsamirr.afra.api.event.ProfileEvent;
import it.itzsamirr.afra.api.profile.IProfile;

public class QuitEvent extends ProfileEvent {
    public QuitEvent(IProfile profile) {
        super(profile);
    }
}
