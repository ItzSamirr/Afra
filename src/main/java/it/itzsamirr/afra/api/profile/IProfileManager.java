package it.itzsamirr.afra.api.profile;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface IProfileManager {
    IProfile getProfile(UUID uuid);
    IProfile getProfile(Player player);
    IProfile getProfile(String name);
    List<IProfile> getProfiles();
    void addProfile(IProfile profile);
    void removeProfile(UUID uuid);
    void removeProfile(Player player);
    void removeProfile(String name);
}
