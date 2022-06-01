package it.itzsamirr.afra.check.violation;

import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.violation.IVL;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.HashMap;

public class VL implements IVL {
    private HashMap<IProfile, Integer> map = new HashMap<>();
    private final ICheck parent;

    public VL(ICheck parent) {
        this.parent = parent;
    }

    @Override
    public void accumulate(IProfile profile) {
        set(profile, get(profile) + (int) getParent().getSettings().getSetting("vl.accumulator"));
    }

    @Override
    public void decay(IProfile profile) {
        if(get(profile) != 0) {
            map.put(profile, get(profile) - (int) getParent().getSettings().getSetting("vl.decay"));
        }
        if(get(profile) < 0){
            set(profile, 0);
        }
    }

    @Override
    public void set(IProfile profile, int amount) {
        map.replace(profile, amount);
    }

    @Override
    public int get(IProfile profile) {
        if(!map.containsKey(profile)){
            map.put(profile, 0);
        }
        return map.get(profile);
    }

    @Override
    public void reset(IProfile profile) {
        set(profile, 0);
    }

    @Override
    public void reset() {
        map.forEach((profile, vl) -> {
            map.replace(profile, 0);
        });
    }

    @Override
    public boolean isMax(IProfile profile) {
        return get(profile) >= (int)parent.getSettings().getSetting("vl.max");
    }

    @Override
    public ICheck getParent() {
        return parent;
    }
}
