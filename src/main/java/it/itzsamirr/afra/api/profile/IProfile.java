package it.itzsamirr.afra.api.profile;

import it.itzsamirr.afra.api.profile.flag.IFlagController;
import it.itzsamirr.afra.utils.Distance;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public interface IProfile {
    Distance getLastDistance();
    void setLastDistance(Distance d);
    Vector getAppliedVelocity();
    void setAppliedVelocity(Vector vec);
    List<IFlagController> getFlagControllers();
    IFlagController getFlagController(Class<? extends IFlagController> clazz);
    int getYJumpModifier();
    Location getLastGroundLocation();
    void setLastGroundLocation(Location location);
    PotionEffect getEffect(PotionEffectType type);
    UUID getUUID();
    double getPlayerWidth();
    double getPlayerHeight();
    double getPlayerHeightSneaking();
    Player getPlayer();
    void sendMessage(String... msg);
    void sendMessage(BaseComponent... components);
    Player.Spigot spigot();
    int getPing();
    boolean isNearGround();
    boolean isOnGround();
    boolean isLastOnGround();
    void setLastOnGround(boolean onGround);
    long getGroundTicks();
    long getLastGroundTicks();
    long getJumpTicks();
    long getLastJumpTicks();
    boolean isMaterialGlideable(Material material);
    void setLastJumpTicks(long ticks);
    void setJumpTicks(long ticks);
    void setLastGroundTicks(long ticks);
    void setGroundTicks(long ticks);
    Location getLocation();
}
