package it.itzsamirr.afra.api.event.listener;

import it.itzsamirr.afra.api.event.Event;
import it.itzsamirr.afra.api.profile.IProfile;

import java.util.ArrayList;
import java.util.List;

public interface Listener extends Comparable<Listener>{
    void on(Event e);
    default Priority getPriority(){
        return Priority.NORMAL;
    }
    default List<Class<? extends Event>> getFilter()
    {
        return new ArrayList<>();
    }

    @Override
    default int compareTo(Listener o){
        return o.getPriority().getValue() - getPriority().getValue();
    }

    public static enum Priority{
        LOWEST((byte)1),
        LOW((byte)2),
        NORMAL((byte)3),
        HIGH((byte)4),
        HIGHEST((byte)5);

        byte value;

        Priority(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }

        public void setValue(byte value) {
            this.value = value;
        }
    }
}
