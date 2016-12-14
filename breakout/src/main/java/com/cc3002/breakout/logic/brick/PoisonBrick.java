package com.cc3002.breakout.logic.brick;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Printer;

import java.util.Observable;
import java.util.Observer;

/**
 * Brick especial que causa que al tocarlo dos veces se pierda el
 * juego.
 * @author gabriel
 *
 */
public class PoisonBrick extends Observable implements IBrick {
  
  int hitpoints;
  Flyweight flyweight;
  
  /**
   * Constructor del StoneBrick, toma una referencia al Score del jugador,
   * y la lista de Observers del Juego.
   * @param newFlyweight objeto Flyweight del juego.
   */
  public PoisonBrick(Flyweight newFlyweight) {
    hitpoints = 2;
    flyweight = newFlyweight;
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  @Override
  public boolean isSoftBrick() {
    return false;
  }

  @Override
  public boolean isStoneBrick() {
    return false;
  }

  @Override
  public boolean isMetalBrick() {
    return false;
  }

  @Override
  public void hit() {
    if (hitpoints > 0) {
      hitpoints--;
    }
  }

  @Override
  public int remainingHits() {
    return hitpoints;
  }

  @Override
  public String print(Printer printer) {
    return null;
  }

  @Override
  public boolean isDestroyed() {
    return hitpoints == 0;
  }

  @Override
  public boolean isPoisonBrick() {
    return true;
  }
}
