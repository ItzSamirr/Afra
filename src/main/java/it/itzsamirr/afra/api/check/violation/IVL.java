package it.itzsamirr.afra.api.check.violation;

import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.profile.IProfile;

public interface IVL {
    void accumulate(IProfile profile);
    void decay(IProfile profile);
    void set(IProfile profile, int amount);
    int get(IProfile profile);
    void reset(IProfile profile);
    void reset();
    boolean isMax(IProfile profile);
    ICheck getParent();
}
