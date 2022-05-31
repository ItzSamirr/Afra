package it.itzsamirr.afra.api.profile.flag;

import it.itzsamirr.afra.api.profile.IProfile;

public interface IFlagController {
    boolean shouldNotFlag();
    IProfile getParent();
}
