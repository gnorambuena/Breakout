package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.observer.GameObserver;

/**
 * Abstraccion del Score,
 * sirve para mantener el score del nivel
 * y el del Player.
 * @author gabriel
 *
 */

public class Score {
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
  }
  
  /**
   * Anade puntaje al jugador.
   * Si este sobrepasa los puntos requeridos se cambio de nivel.
   * @param pt Puntos a anadir.
   */
  public void add(long pt) {
    points += pt;
    if (points > flyweight.getRequiredPoints()) {
      for (GameObserver observer : flyweight.getObservers()) {
        observer.bonusAutoSwitch();
      }
      for (GameObserver observer: flyweight.getObservers()) {
        observer.levelAutoSwitch();
      }
    }
  }
  
  public void addNextLevel(long pt) {
    points += pt;
  }
  
  public long getPoints() {
    return points;
  }
}
