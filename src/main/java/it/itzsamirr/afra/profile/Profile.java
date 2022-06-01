package it.itzsamirr.afra.profile;

import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.profile.flag.IFlagController;
import it.itzsamirr.afra.api.utils.Color;
import it.itzsamirr.afra.api.utils.Reflection;
import it.itzsamirr.afra.profile.flag.JumpFlagController;
import it.itzsamirr.afra.profile.flag.SpeedFlagController;
import it.itzsamirr.afra.utils.Distance;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Profile implements IProfile {
    private final Player player;
    private Vector appliedVelocity = new Vector();
    private Distance lastDistance = null;
    private final double PLAYER_WIDTH = .6;
    private final double PLAYER_HEIGHT = 1.8;
    private final double PLAYER_HEIGHT_SNEAKING = 1.6;
    private boolean lastOnGround = false;
    private long groundTicks = 0, lastGroundTicks = 0, jumpTicks = 0, lastJumpTicks = 0;
    private Location lastGroundLocation;
    private final List<IFlagController> flagControllers = new ArrayList<>();

    public Profile(Player player) {
        this.player = player;
        lastGroundLocation = null;
        lastDistance = new Distance(player.getLocation(), player.getLocation());
        flagControllers.add(new JumpFlagController(this));
        flagControllers.add(new SpeedFlagController(this));
    }

    @Override
    public Distance getLastDistance() {
        return lastDistance;
    }

    @Override
    public void setLastDistance(Distance d) {
        this.lastDistance = d;
    }

    @Override
    public Vector getAppliedVelocity() {
        return appliedVelocity;
    }

    @Override
    public void setAppliedVelocity(Vector vec) {
        this.appliedVelocity = vec;
    }

    @Override
    public List<IFlagController> getFlagControllers() {
        return flagControllers;
    }

    @Override
    public IFlagController getFlagController(Class<? extends IFlagController> clazz) {
        return flagControllers
                .stream()
                .filter(fc -> fc.getClass().isAssignableFrom(clazz))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getYJumpModifier() {
        return player.hasPotionEffect(PotionEffectType.JUMP) ? getEffect(PotionEffectType.JUMP).getAmplifier() : 0;
    }

    @Override
    public Location getLastGroundLocation() {
        return lastGroundLocation;
    }

    @Override
    public void setLastGroundLocation(Location loc) {
        this.lastGroundLocation = loc;
    }

    @Override
    public PotionEffect getEffect(PotionEffectType type) {
        return player.getActivePotionEffects()
                .stream()
                .filter(p -> p.getType().equals(type))
                .findFirst()
                .orElse(null);
    }

    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public double getPlayerWidth() {
        return PLAYER_WIDTH;

    }

    @Override
    public double getPlayerHeight() {
        return PLAYER_HEIGHT;
    }

    @Override
    public double getPlayerHeightSneaking() {
        return PLAYER_HEIGHT_SNEAKING;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void sendMessage(String... msg) {
        for(String s : msg)
        {
            player.sendMessage(Color.translate(s));
        }
    }

    @Override
    public void sendMessage(BaseComponent... components) {
        player.spigot().sendMessage(components);
    }

    @Override
    public Player.Spigot spigot() {
        return player.spigot();
    }

    @Override
    public int getPing() {
        return Reflection.getPing(player);
    }

    @Override
    public boolean isNearGround() {
        Location location = getLocation();
        double ex = 0.3d;
        for(double x = -ex;x<=ex;x+=ex){
            for(double z = -ex;z<=ex;z+=ex){
                if(location.clone().add(x, -0.5001, z).getBlock().getType().isSolid()) return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOnGround() {
        Location loc = getLocation();
        return loc.getBlock().getLocation().clone().add(0, -1, 0).getBlock().getType().isSolid() || loc.clone().add(PLAYER_WIDTH/2, -1,0).getBlock().getType().isSolid()
                || loc.clone().add(PLAYER_WIDTH/2, -1, PLAYER_WIDTH/2).getBlock().getType().isSolid()
                || loc.clone().add(PLAYER_WIDTH/2, -1, -PLAYER_WIDTH/2).getBlock().getType().isSolid()
                || loc.clone().add(-PLAYER_WIDTH/2, -1, 0).getBlock().getType().isSolid()
                || loc.clone().add(-PLAYER_WIDTH/2, -1,-PLAYER_WIDTH/2).getBlock().getType().isSolid()
                || loc.clone().add(0, -1,-PLAYER_WIDTH/2).getBlock().getType().isSolid()
                || loc.clone().add(0, -1, PLAYER_WIDTH/2).getBlock().getType().isSolid();
    }

    @Override
    public boolean isLastOnGround() {
        return lastOnGround;
    }

    @Override
    public void setLastOnGround(boolean onGround) {
        this.lastOnGround = onGround;
    }

    @Override
    public long getGroundTicks() {
        return groundTicks;
    }

    @Override
    public void setLastGroundTicks(long ticks) {
        this.lastGroundTicks = ticks;
    }

    @Override
    public void setGroundTicks(long ticks) {
        this.groundTicks = ticks;
    }

    @Override
    public long getLastGroundTicks() {
        return lastGroundTicks;
    }

    @Override
    public long getJumpTicks() {
        return jumpTicks;
    }

    @Override
    public long getLastJumpTicks() {
        return lastJumpTicks;
    }

    @Override
    public boolean isMaterialGlideable(Material material) {
        switch(material){
            case LADDER:
            case VINE:
                return true;
        }
        return false;
    }


    @Override
    public void setLastJumpTicks(long ticks) {
        this.lastJumpTicks = ticks;
    }

    @Override
    public void setJumpTicks(long ticks) {
        this.jumpTicks = ticks;
    }


    @Override
    public Location getLocation() {
        return player.getLocation();
    }
}
