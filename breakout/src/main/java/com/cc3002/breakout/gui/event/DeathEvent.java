package com.cc3002.breakout.gui.event;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Evento de muerte del jugador.
 * @author gabriel
 *
 */
public class DeathEvent extends Event {

  private static final long serialVersionUID = 1L;
  
  public static final EventType<DeathEvent> ANY
            = new EventType<>(Event.ANY, "DEATH_EVENT");

  public DeathEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
  }
}
