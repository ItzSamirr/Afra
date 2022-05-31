package it.itzsamirr.afra.check.settings;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.settings.ICheckSettings;

import java.util.HashMap;

public final class CheckSettings implements ICheckSettings {
    private final Afra plugin;
    private final ICheck parent;

    public CheckSettings(Afra plugin, ICheck parent) {
        this.plugin = plugin;
        this.parent = parent;
    }

    @Override
    public Object getSetting(String name) {
        return plugin.getConfig().get("checks." + parent.getInfo().getCategory().name().toLowerCase() + "." + parent.getInfo().getName().toLowerCase() + "." + Character.toLowerCase(parent.getInfo().getType()) + "." + name);
    }

    @Override
    public void setSetting(String name, Object o) {
        plugin.getConfig().set("checks." + parent.getInfo().getCategory().name().toLowerCase() + "." +
                parent.getInfo().getName().toLowerCase() + "." +
                Character.toLowerCase(parent.getInfo().getType()) + "." + name, o);
        plugin.saveConfig();
    }

    @Override
    public HashMap<String, Object> getSettings() {
        HashMap<String, Object> map = new HashMap<>();
        plugin.getConfig().getConfigurationSection("checks." + parent.getInfo().getCategory().name().toLowerCase() + "." + parent.getInfo().getName().toLowerCase() + "." + Character.toLowerCase(parent.getInfo().getType()))
                .getKeys(true)
                .forEach(key -> {
                    map.put(key, getSetting(key));
                });
        return map;
    }

    @Override
    public ICheck getParent() {
        return parent;
    }
}
