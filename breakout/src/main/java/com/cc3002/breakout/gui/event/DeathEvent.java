package com.cc3002.breakout.gui.event;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

public class DeathEvent extends Event {

    public static final EventType<DeathEvent> ANY
            = new EventType<>(Event.ANY, "DEATH_EVENT");

    public DeathEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }
}
