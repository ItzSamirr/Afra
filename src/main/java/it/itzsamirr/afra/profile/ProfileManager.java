package it.itzsamirr.afra.profile;

import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.profile.IProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ProfileManager implements IProfileManager {
    private final List<IProfile> profiles = new ArrayList<>();

    @Override
    public IProfile getProfile(UUID uuid) {
        return profiles
                .stream()
                .filter(p -> p.getUUID().equals(uuid))
                .findFirst().orElseGet(() -> {
                    IProfile profile = new Profile(Bukkit.getPlayer(uuid));
                    profiles.add(profile);
                    return profile;
                });
    }

    @Override
    public IProfile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }

    @Override
    public IProfile getProfile(String name) {
        return getProfile(Bukkit.getPlayer(name));
    }

    @Override
    public List<IProfile> getProfiles() {
        return profiles;
    }

    @Override
    public void addProfile(IProfile profile) {
        profiles.add(profile);
    }

    @Override
    public void removeProfile(UUID uuid) {
        profiles.removeIf(p -> p.getUUID().equals(uuid));
    }

    @Override
    public void removeProfile(Player player) {
        removeProfile(player.getUniqueId());
    }

    @Override
    public void removeProfile(String name) {
        removeProfile(Bukkit.getPlayer(name));
    }
}
