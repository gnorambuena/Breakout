package com.cc3002.breakout.gui.event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Eventos del juego.
 * @author gabriel
 *
 */
public class Events {

  public static final EventType<Event> GAME_OVER
            = new EventType<>(Event.ANY, "GAME_OVER");

  public static final EventType<Event> REACH_FINISH
            = new EventType<>(Event.ANY, "REACH_FINISH");
}
