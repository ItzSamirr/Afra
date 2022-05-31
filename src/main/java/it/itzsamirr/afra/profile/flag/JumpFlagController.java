package it.itzsamirr.afra.profile.flag;

import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.profile.flag.IFlagController;
import org.bukkit.Location;

public class JumpFlagController implements IFlagController {
    private IProfile parent;

    public JumpFlagController(IProfile parent) {
        this.parent = parent;
    }

    @Override
    public boolean shouldNotFlag() {
        Location loc = parent.getLocation();
        return parent.isMaterialGlideable(loc.getBlock().getType()) ||
                parent.isMaterialGlideable(loc.clone().add(parent.getPlayerWidth()/2, 0, 0).getBlock().getType()) ||
                parent.isMaterialGlideable(loc.clone().add(parent.getPlayerWidth()/2, 0, parent.getPlayerWidth()/2).getBlock().getType()) ||
                parent.isMaterialGlideable(loc.clone().add(-parent.getPlayerWidth()/2, 0, 0).getBlock().getType()) ||
                parent.isMaterialGlideable(loc.clone().add(-parent.getPlayerWidth()/2, 0, -parent.getPlayerWidth()/2).getBlock().getType()) ||
                parent.isMaterialGlideable(loc.clone().add(0, 0, -parent.getPlayerWidth()/2).getBlock().getType()) ||
                parent.isMaterialGlideable(loc.clone().add(0, 0, parent.getPlayerWidth()/2).getBlock().getType()) ||
                parent.isOnGround();
    }

    @Override
    public IProfile getParent() {
        return parent;
    }
}
