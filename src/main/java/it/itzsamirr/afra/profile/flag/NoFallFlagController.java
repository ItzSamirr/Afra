package it.itzsamirr.afra.profile.flag;

import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.profile.flag.IFlagController;

public class NoFallFlagController implements IFlagController {
    private IProfile parent;

    public NoFallFlagController(IProfile parent) {
        this.parent = parent;
    }

    @Override
    public boolean shouldNotFlag() {
        return false;
    }

    @Override
    public IProfile getParent() {
        return parent;
    }
}
