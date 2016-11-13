package com.cc3002.breakout.logic.level;

import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Abstraccion de un jugador,
 * sirve para mantener el Score,
 * y el numero de vidas.
 * @author gabriel
 *
 */

public class Player {
  Score sc;
  Life life;
  
  public Player() {
    sc = new Score(0);
    life = new Life();
  }
  
  public int getNumberOfHearts() {
    return life.getHearts();
  }
  
  public void lossOfHeart() {
    life.lessHearts();
  }
  
  public void addScore(long points) {
    sc.add(points);
  }
  
  public Score getScore() {
    return sc;
  }
  
  public long getTotalPoints() {
    return sc.getPoints();
  }
  
  public void addHearts() {
    life.addHearts();
  }

  public void setObservers(List<GameObserver> newObservers) {
    sc.setObservers(newObservers);
  }
  
}
