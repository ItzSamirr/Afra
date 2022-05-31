package it.itzsamirr.afra.api.check;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface ICheckManager {
    void register(ICheck... checks);
    void unregister(Class<? extends ICheck>... classes);
    List<ICheck> getChecks();
    <T extends ICheck> T getCheck(Class<T> clazz);
    default List<ICheck> getChecks(Predicate<ICheck> predicate){
        return getChecks()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    boolean isExperimental(Class<?> clazz);
}
