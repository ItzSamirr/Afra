package it.itzsamirr.afra.profile.flag;

import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.profile.flag.IFlagController;
import org.bukkit.Material;

public class SpeedFlagController implements IFlagController {
    private final IProfile parent;

    public SpeedFlagController(IProfile parent) {
        this.parent = parent;
    }

    @Override
    public boolean shouldNotFlag() {
        return parent.getPlayer().getAllowFlight() || parent.getAppliedVelocity().lengthSquared() != 0 || parent.isInLiquid();
    }

    @Override
    public IProfile getParent() {
        return parent;
    }
}
