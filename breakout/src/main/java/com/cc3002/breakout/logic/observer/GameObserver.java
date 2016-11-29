package com.cc3002.breakout.logic.observer;


import com.cc3002.breakout.facade.Flyweight;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase abstracta para todos los Observer del juego.
 * @author gabriel
 *
 */
public class GameObserver implements Observer {
  
  Flyweight flyweight;
  Hashtable<String,String> tabla;
  
  /**
   * Observer del juego, se encarga de mostrar los mensajes de los eventos.
   * @param newFlyweight Flyweight del juego.
   */
  public GameObserver(Flyweight newFlyweight) {
    flyweight = newFlyweight;
    tabla = new Hashtable<String,String>();
    tabla.put("SD", "Score discount emitted.");
    tabla.put("ES", "Extra score bonus emitted.");
    tabla.put("HD", "Heart discount emitted.");
    tabla.put("EH", "Extra heart bonus emitted.");
    tabla.put("SOB", "Soft brick destroyed and gained 10 points.");
    tabla.put("STB", "Stone brick destroyed and gained 50 points.");

  }

  /**
   * Metodo que muestra el mensaje.
   */
  public void update(Observable object, Object arg) {
    String value = tabla.get(arg);
    if (value != null) {
      flyweight.getGameConsole().print(value);
    }
  }

}
