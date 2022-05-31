package it.itzsamirr.afra.check;

import it.itzsamirr.afra.Afra;
import it.itzsamirr.afra.api.check.ICheck;
import it.itzsamirr.afra.api.check.ICheckManager;
import it.itzsamirr.afra.api.check.annotations.Experimental;
import it.itzsamirr.afra.check.movement.jump.JumpA;

import java.util.ArrayList;
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
        for(ICheck check : checks)
        {
            check.init();
            this.checks.add(check);
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
}
