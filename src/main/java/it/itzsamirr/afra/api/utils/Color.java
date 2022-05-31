package it.itzsamirr.afra.api.utils;

import org.bukkit.ChatColor;

import java.util.List;

public final class Color {
    private Color() {
        throw new UnsupportedOperationException();
    }

    public static String translate(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> translate(List<String> list){
        list.replaceAll(Color::translate);
        return list;
    }
}
