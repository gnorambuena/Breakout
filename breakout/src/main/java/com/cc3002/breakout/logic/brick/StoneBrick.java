package com.cc3002.breakout.logic.brick;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Printer;

import java.util.Observable;
import java.util.Observer;


/**
 * Representa un StoneBrick del juego,
 * tiene tres hitpoints.
 * @author gabriel
 *
 */

public class StoneBrick extends Observable implements IBrick {
  int hitpoints;
  Flyweight flyweight;
  
  /**
   * Constructor del StoneBrick, toma una referencia al Score del jugador,
   * y la lista de Observers del Juego.
   * @param newFlyweight objeto Flyweight del juego.
   */
  public StoneBrick(Flyweight newFlyweight) {
    hitpoints = 3;
    flyweight = newFlyweight;
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }

  public int remainingHits() {
    return hitpoints;
  }

  public boolean isSoftBrick() {
    return false;
  }
  
  public boolean isStoneBrick() {
    return true;
  }
  
  /**
   * Le quita uno a los hitpoints
   * del IBrick.
   */
  
  public void hit() {
    if (hitpoints > 0) {
      hitpoints--;
      if (isDestroyed()) {
        setChanged();
        notifyObservers("STB");
        clearChanged();
        flyweight.getBonusHandler().reached();
        flyweight.getCurScore().add(50);
      }
    }
  }

  public String print(Printer printer) {
    return printer.printStoneBrick();
  }

  public boolean isDestroyed() {
    return hitpoints == 0;
  }
  
  @Override
  public boolean isMetalBrick() {
    return false;
  }
  
  @Override
  public boolean isPoisonBrick() {
    return false;
  }
}

