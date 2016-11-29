package com.cc3002.breakout.logic.brick;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Printer;

import java.util.Observable;
import java.util.Observer;

/**
 * Representa un SoftBrick del juego,
 * tiene solo un hitpoint.
 * @author gabriel
 *
 */

public class  SoftBrick extends Observable implements IBrick {
  int hitpoints;
  Flyweight flyweight;
  
  /**
   * Constructor del StoneBrick, toma una referencia al Score del jugador,
   * y la lista de Observers del Juego.
   * @param newFlyweight objeto Flyweight del juego.
   */
  public SoftBrick(Flyweight newFlyweight) {
    hitpoints = 1;
    flyweight = newFlyweight;
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  public boolean isSoftBrick() {
    return true;
  }

  public boolean isStoneBrick() {
    return false;
  }
  
  public int remainingHits() {
    return hitpoints;
  }

  public String print(Printer printer) {
    return printer.printSoftBrick();
  }
  
  /**
   * Le quita uno a los hitpoints
   * del IBrick.
   */
  
  public void hit() {
    if (hitpoints > 0) {
      hitpoints--;
      setChanged();
      notifyObservers("SOB");
      clearChanged();
      flyweight.getBonusHandler().reached();
      flyweight.getCurScore().add(10);
    }
  }

  public boolean isDestroyed() {
    return hitpoints == 0;
  }

}