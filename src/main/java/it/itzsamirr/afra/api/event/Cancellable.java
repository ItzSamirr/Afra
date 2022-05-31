package it.itzsamirr.afra.api.event;

public interface Cancellable {
    void cancel(int type);
    int getCancelType();
    default boolean isAllowed(){
        return getCancelType() == 0;
    }
}
