package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;
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
  List<GameObserver> observers;
  
  public Score() {
    points = 0;
    requiredPoints = 100000000L;
  }
  
  public Score(long pt) {
    points = pt;
    requiredPoints = 100000000L;
  }
  
  public void setObservers(List<GameObserver> newGameObserver) {
    observers = newGameObserver;
  }
  
  public void setRequiredPoints(long newPoints) {
    requiredPoints = newPoints;
  }
  
  public void setCurrentPoints(long newPoints) {
    points = newPoints;
  }
  
  public long getRequiredPoints() {
    return requiredPoints;
  }
  
  /**
   * Anademe puntaje al jugador.
   * Si este sobrepasa los puntos requeridos se cambio de nivel.
   * @param pt Puntos a anadir.
   */
  public void add(long pt) {
    points += pt;
    if (points > requiredPoints) {
      for (GameObserver observer : observers) {
        observer.bonusAutoSwitch();
      }
      for (GameObserver observer: observers) {
        observer.levelAutoSwitch();
      }
    }
  }
  
  public long getPoints() {
    return points;
  }
}
