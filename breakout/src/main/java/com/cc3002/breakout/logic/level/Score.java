package com.cc3002.breakout.logic.level;


import com.cc3002.breakout.facade.Flyweight;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstraccion del Score,
 * sirve para mantener el score del nivel
 * y el del Player.
 * @author gabriel
 *
 */

public class Score extends Observable {
  long points;
  long requiredPoints;
  Flyweight flyweight;
  
  public Score() {
    points = 0;
    requiredPoints = 100000000L;
  }
  
  /**
   * Este objeto se encarga de mantener el Score del juego y el jugador.
   * @param newFlyweight Flyweight del juego.
   */
  public Score(Flyweight newFlyweight) {
    points = 0;
    requiredPoints = 100000000L;
    flyweight = newFlyweight;
    
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Anade puntaje al jugador.
   * Si este sobrepasa los puntos requeridos se cambio de nivel.
   * @param pt Puntos a anadir.
   */
  public void add(long pt) {
    points += pt;
    System.out.println("Current score:" + points);

    if (points > flyweight.getRequiredPoints()) {
      setChanged();
      notifyObservers("AS");
      clearChanged();
    }
  }
  
  public void addNextLevel(long pt) {
    System.out.println("Current score: (next level)" + points);
    points += pt;
  }
  
  public long getPoints() {
    return points;
  }
}
