package it.itzsamirr.afra.check;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.info.ICheckInfo;
import it.itzsamirr.afra.api.check.settings.ICheckSettings;
import it.itzsamirr.afra.api.check.violation.IPreVL;
import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.utils.Color;
import it.itzsamirr.afra.check.info.CheckInfo;
import it.itzsamirr.afra.check.settings.CheckSettings;
import it.itzsamirr.afra.check.violation.PreVL;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public abstract class Check implements ICheck {
    protected ICheckSettings settings;
    protected ICheckInfo info;
    protected IPreVL preVL;
    protected final Afra plugin;

    public Check(Afra plugin, ICheckInfo info) {
        this.plugin = plugin;
        this.info = info;
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
    }

    public Check(Afra plugin, CheckCategory category, String name, char type){
        this.plugin = plugin;
        this.info = new CheckInfo(this, category, name, type);
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
    }

    public Check(Afra plugin, CheckCategory category, String name, char type, String description){
        this.plugin = plugin;
        this.info = new CheckInfo(this, category, name, type, description);
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
    }

    @Override
    @Deprecated
    public void reload() {
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
    }

    @Override
    public void init() {
        plugin.getEventManager().registerListener(this);
        plugin.getLogger().info("Loaded" + (isExperimental() ? (!isDev() ? " Experimental " : " Dev ") : " ") + info.getCategory().getDisplayName() + " check " + info.getName() + " [" + info.getType() + "]");
    }

    @Override
    public boolean isEnabled() {
        return (boolean) settings.getSetting("enabled");
    }

    @Override
    public boolean isExperimental() {
        return getClass().isAnnotationPresent(Experimental.class);
    }

    @Override
    public boolean isDev() {
        return isExperimental() && getClass().getDeclaredAnnotation(Experimental.class).dev();
    }

    @Override
    public ICheckSettings getSettings() {
        return settings;
    }

    @Override
    public ICheckInfo getInfo() {
        return info;
    }

    @Override
    public IPreVL getPreVL() {
        return preVL;
    }

    @Override
    public void flag(String msg, IProfi, Cancellable cancellable, int type) {
        if(plugin.getConfig().getBoolean("flag.msg.enabled")) {
            BaseComponent component = new TextComponent(Color.translate(plugin.getConfig().getString("flag.msg.text")).replace("{prefix}", Color.translate(plugin.getConfig().getString("prefix"))).replace("{player}", ));

        }
        cancellable.cancel(type);
    }
}
