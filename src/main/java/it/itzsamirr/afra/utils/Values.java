package it.itzsamirr.afra.utils;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public final class Values {
    private Values(){
        throw new UnsupportedOperationException();
    }
    public static final int MAX_JUMP_TICKS_IN_AIR;
    public static final ArrayList<String> steppableMaterials;
    public static final float FRICTION;

    static{
        MAX_JUMP_TICKS_IN_AIR = 6;
        FRICTION = 0.91f;
        steppableMaterials = new ArrayList<>(Arrays.asList("STAIRS", "LADDER", "STEP"));
    }

    public static boolean isSteppable(Material mat){
        return steppableMaterials.stream().anyMatch(st -> mat.name().toUpperCase().contains(st.toUpperCase()));
    }
}
