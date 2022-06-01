package it.itzsamirr.afra.check.violation;

import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.violation.IPreVL;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.HashMap;

public final class PreVL implements IPreVL {
    private final ICheck parent;
    private HashMap<IProfile, Double> preVl = new HashMap<>();

    public PreVL(ICheck parent) {
        this.parent = parent;
    }

    @Override
    public HashMap<IProfile, Double> getMap() {
        return preVl;
    }

    @Override
    public double get(IProfile profile) {
        if(!preVl.containsKey(profile))
        {
            preVl.put(profile, 0.0);
        }
        return preVl.get(profile);
    }

    @Override
    public void set(IProfile profile, double value) {
        preVl.replace(profile, value);
    }

    @Override
    public double accumulate(IProfile profile) {
        set(profile, get(profile)+(double)parent.getSettings().getSetting("buffer.accumulator"));
        return get(profile);
    }

    @Override
    public double decay(IProfile profile) {
        set(profile, get(profile)-(double)parent.getSettings().getSetting("buffer.decay"));
        return get(profile);
    }

    @Override
    public ICheck getParent() {
        return parent;
    }

    @Override
    public boolean isMax(IProfile profile) {
        return get(profile) >= (double)parent.getSettings().getSetting("buffer.max");
    }
}
