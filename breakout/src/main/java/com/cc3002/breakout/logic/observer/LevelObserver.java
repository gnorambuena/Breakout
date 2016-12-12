package com.cc3002.breakout.logic.observer;


import com.cc3002.breakout.facade.Flyweight;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Observer para los ILevel.
 * @author gabriel
 *
 */
public class LevelObserver implements Observer {

  Flyweight flyweight;
  Set<String> tabla;
  
  /**
   * Observer del juego, se encarga de mostrar los mensajes de los eventos.
   * @param newFlyweight Flyweight del juego.
   */
  public LevelObserver(Flyweight newFlyweight) {
    flyweight = newFlyweight;
    
    tabla = new HashSet<String>();
    tabla.add("SD");
    tabla.add("ES");
    tabla.add("HD");
    tabla.add("EH");
    tabla.add("SOB");
    tabla.add("STB");

  }
  

  /**
   * Update para los metodos autoSwitch y Update.
   */
  public void update(Observable object, Object arg) {
    if (arg != null) {
      String value = arg.toString();
      if ("AS".equals(value)) {
        levelAutoSwitch();
      } else if (!tabla.contains(value)) {
        levelUpdate(value);
      }
    }
  }
  
  private void levelUpdate(String name) {
    flyweight.getGameConsole().print("Playing Level " + name + ".");
  }

  private void levelAutoSwitch() {
    if (flyweight.getAutoSwitch().isOpen()) {
      flyweight.getLevelHandler().autoSwitchToNextLevel();
    }
  }

}
