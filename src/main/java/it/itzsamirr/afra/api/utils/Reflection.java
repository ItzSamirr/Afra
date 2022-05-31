package it.itzsamirr.afra.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    private static String VERSION;
    private static Class<?> craftPlayerClass;
    private static Class<?> entityPlayerClass;
    private static Method getHandleMethod;
    private static Field pingField;

    static{
        try {
            String temp = Bukkit.getServer().getClass().getPackage().getName();
            VERSION = temp.substring(temp.lastIndexOf(".") + 1);
        }catch(Exception e){
            VERSION = "";
        }
        craftPlayerClass = getClass("{cb}CraftPlayer");
        entityPlayerClass = getClass("{nms}EntityPlayer");
        getHandleMethod = getMethod(craftPlayerClass, "getHandle");
        pingField = getField(entityPlayerClass, "ping");
    }

    public static int getPing(Player player){
        try {
            return pingField.getInt(getEntityPlayer(player));
        } catch (IllegalAccessException e) {
            return -1;
        }
    }

    public static Object getEntityPlayer(Player player){
        try {
            return getHandleMethod.invoke(player);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> getClass(String name){
        try {
            return Class.forName(name
                    .replace("{nms}", !VERSION.isEmpty() ? "net.minecraft.server." + VERSION + "." : "net.minecraft.server.")
                    .replace("{cb}", !VERSION.isEmpty() ? "org.craftbukkit." + VERSION + "." : "org.craftbukkit."));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... typesParameters){
        try {
            return clazz.getDeclaredMethod(name, typesParameters);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Field getField(Class<?> clazz, String name){
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
