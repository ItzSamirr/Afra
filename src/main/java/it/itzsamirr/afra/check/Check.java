package it.itzsamirr.afra.check;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.CheckCategory;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.info.ICheckInfo;
import it.itzsamirr.afra.api.check.settings.ICheckSettings;
import it.itzsamirr.afra.api.check.violation.IPreVL;
import it.itzsamirr.afra.api.check.violation.IVL;
import it.itzsamirr.afra.api.event.Cancellable;
import it.itzsamirr.afra.api.event.check.CheckFlagEvent;
import it.itzsamirr.afra.api.profile.IProfile;
import it.itzsamirr.afra.api.utils.Color;
import it.itzsamirr.afra.check.info.CheckInfo;
import it.itzsamirr.afra.check.settings.CheckSettings;
import it.itzsamirr.afra.check.violation.PreVL;
import it.itzsamirr.afra.check.violation.VL;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class Check implements ICheck {
    protected ICheckSettings settings;
    protected ICheckInfo info;
    protected IPreVL preVL;
    protected IVL vl;
    protected final Afra plugin;

    public Check(Afra plugin, ICheckInfo info) {
        this.plugin = plugin;
        this.info = info;
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
        this.vl = new VL(this);
    }

    @Override
    public IVL getVL() {
        return vl;
    }

    public Check(Afra plugin, CheckCategory category, String name, char type){
        this.plugin = plugin;
        this.info = new CheckInfo(this, category, name, type);
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
        this.vl = new VL(this);
    }

    public Check(Afra plugin, CheckCategory category, String name, char type, String description){
        this.plugin = plugin;
        this.info = new CheckInfo(this, category, name, type, description);
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
        this.vl = new VL(this);
    }

    @Override
    @Deprecated
    public void reload() {
        this.settings = new CheckSettings(plugin, this);
        this.preVL = new PreVL(this);
        this.vl = new VL(this);
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
    public void flag(HashMap<String, Object> infoMap, IProfile profile, Cancellable cancellable, int type) {
        CheckFlagEvent flagEvent = new CheckFlagEvent(profile, this, infoMap);
        plugin.getEventManager().call(flagEvent);
        if(!flagEvent.isAllowed()) return;
        vl.accumulate(profile);
        if(plugin.getConfig().getBoolean("flag.msg.enabled")) {
            TextComponent component = new TextComponent(Color.translate(plugin.getConfig().getString("flag.msg.text")).replace("{prefix}", Color.translate(plugin.getConfig().getString("prefix"))).replace("{player}", profile.getPlayer().getName()).replace("{check}", getInfo().getName()).replace("{type}", String.valueOf(getInfo().getType())).replace("{vl}", String.valueOf(vl.get(profile))).replace("{max}", String.valueOf((int)getSettings().getSetting("vl.max"))) + Color.translate(isExperimental() ? (isDev() ? " " + plugin.getConfig().getString("dev") : " " + plugin.getConfig().getString("experimental")) : ""));
            if(plugin.getConfig().getBoolean("flag.msg.hover.enabled")){
                List<String> hoverList = plugin.getConfig().getStringList("flag.msg.hover.text");
                BaseComponent[] hoverComponents = new TextComponent[hoverList.size()];
                    for (int i = 0; i < hoverList.size(); i++) {
                        hoverComponents[i] = new TextComponent(Color.translate(hoverList.get(i).replace("{desc}", getInfo().getDescription()).replace("{info}", generateFormattedInfo(infoMap)).replace("{player}", profile.getPlayer().getName()).replace("{ping}", String.valueOf(profile.getPing())).replace("{uuid}", profile.getPlayer().getUniqueId().toString())) + "\n");
                    }
                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponents));
            }
            if(plugin.getConfig().getBoolean("flag.msg.click.enabled"))
            {
                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/" + plugin.getConfig().getString("flag.msg.click.command")));
            }
            Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("afra.alerts")).forEach(player -> {
                player.spigot().sendMessage(component);
            });
        }
        cancellable.cancel(type);
        if(vl.isMax(profile) && (boolean)getSettings().getSetting("punishable"))
        {
            profile.getPlayer().kickPlayer((String)getSettings().getSetting("kick-reason"));
        }
    }

    @Override
    public void noFlag(IProfile profile) {
        if(preVL.get(profile) != .0) {
            preVL.decay(profile);
        }else{
            if(vl.get(profile) != 0){
                vl.decay(profile);
            }
            if(vl.get(profile) < 0){
                vl.reset();
            }
        }
        if(preVL.get(profile) < .0){
            preVL.set(profile, .0);
        }
    }

    @Override
    public String generateFormattedInfo(HashMap<String, Object> infoMap) {
        StringBuilder buffer = new StringBuilder();
        List<Map.Entry<String, Object>> entries = new ArrayList<>(infoMap.entrySet());
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, Object> entry = entries.get(i);
            if(i >= entries.size()-1) buffer.append(Color.translate(plugin.getConfig().getString("flag.msg.hover.info-format")
                    .replace("{key}", entry.getKey())
                    .replace("{value}", String.valueOf(entry.getValue()))));
            else buffer.append(Color.translate(plugin.getConfig().getString("flag.msg.hover.info-format")
                    .replace("{key}", entry.getKey())
                    .replace("{value}", String.valueOf(entry.getValue())))).append(", ");
        }
        return buffer.toString();
    }
}
