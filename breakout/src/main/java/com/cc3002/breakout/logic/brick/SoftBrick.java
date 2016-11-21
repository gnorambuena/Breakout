package com.cc3002.breakout.logic.brick;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Printer;
import com.cc3002.breakout.logic.observer.GameObserver;


/**
 * Representa un SoftBrick del juego,
 * tiene solo un hitpoint.
 * @author gabriel
 *
 */

public class  SoftBrick implements IBrick {
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
      for (GameObserver obs : flyweight.getObservers()) {
        obs.scoreSoftBrickUpdate();
      }
      flyweight.getBonusHandler().reached();
      flyweight.getCurScore().add(10);
    }
  }

  public boolean isDestroyed() {
    return hitpoints == 0;
  }

}