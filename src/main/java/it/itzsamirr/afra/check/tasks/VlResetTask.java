package it.itzsamirr.afra.check.tasks;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class VlResetTask extends BukkitRunnable {
    private final Afra plugin;

    public VlResetTask(Afra plugin){
        this.plugin = plugin;
        runTaskTimerAsynchronously(plugin, plugin.getConfig().getInt("vl-reset-delay"), plugin.getConfig().getInt("vl-reset-delay"));
    }

    @Override
    public void run() {
        plugin.getCheckManager().getChecks().stream().filter(ICheck::isEnabled)
                .forEach(check -> check.getVL().reset());
        Bukkit.getScheduler().runTask(plugin, () -> {
            if(plugin.getConfig().getBoolean("notify-vl-reset.enabled")){
                List<String> list = Color.translate(plugin.getConfig().getStringList("notify-vl-reset.msg"));
                list.replaceAll(s -> s.replace("{prefix}", Color.translate(plugin.getConfig().getString("prefix"))));
                list.forEach(Bukkit::broadcastMessage);
            }
        });
    }
}
