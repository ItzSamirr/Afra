package it.itzsamirr.afra.check;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.ICheckManager;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.api.check.annotations.Testing;
import it.itzsamirr.afra.check.movement.jump.JumpA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CheckManager implements ICheckManager {
    private List<ICheck> checks = new ArrayList<>();
    private final Afra plugin;

    public CheckManager(Afra plugin)
    {
        this.plugin = plugin;
        register(new JumpA(plugin));
    }

    @Override
    public void register(ICheck... checks) {
        List<ICheck> checksList = Arrays.asList(checks);
        if(checksList.stream().anyMatch(check -> isTesting(check.getClass()))){
            checksList.stream().filter(check -> isTesting(check.getClass()))
                    .forEach(check -> {
                        this.checks.add(check);
                        check.init();
                    });
        }else{
            checksList.forEach(check -> {
                this.checks.add(check);
                check.init();
            });
        }
    }

    @Override
    public void unregister(Class<? extends ICheck>... classes) {
        for(Class<? extends ICheck> check : classes)
        {
            checks.removeIf(c2 -> c2.getClass().isAssignableFrom(check));
        }
    }

    @Override
    public List<ICheck> getChecks() {
        return checks;
    }

    @Override
    public <T extends ICheck> T getCheck(Class<T> clazz) {
        return (T) checks.stream().filter(check -> check.getClass().isAssignableFrom(clazz))
                .findFirst().orElse(null);
    }

    @Override
    public boolean isExperimental(Class<?> clazz) {
        return clazz.isAnnotationPresent(Experimental.class);
    }

    @Override
    public boolean isTesting(Class<?> clazz) {
        return clazz.isAnnotationPresent(Testing.class);
    }
}
