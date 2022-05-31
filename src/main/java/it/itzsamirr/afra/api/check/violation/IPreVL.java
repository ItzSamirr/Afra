package it.itzsamirr.afra.api.check.violation;

import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.HashMap;

public interface IPreVL {
    double get(IProfile profile);
    void set(IProfile profile, double value);
    double accumulate(IProfile profile);
    double deaccumulate(IProfile profile);
    ICheck getParent();
    boolean isMax(IProfile profile);
    HashMap<IProfile, Double> getMap();
}
