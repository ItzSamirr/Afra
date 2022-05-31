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

    static{
        MAX_JUMP_TICKS_IN_AIR = 5;
        steppableMaterials = new ArrayList<>(Arrays.asList("STAIRS", "STEP", "LADDER"));
    }

    public static boolean isSteppable(Material mat){
        return steppableMaterials.stream().anyMatch(st -> mat.name().toUpperCase().contains(st.toUpperCase()));
    }
}
