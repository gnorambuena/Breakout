package com.cc3002.breakout.logic.brick;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Printer;

import java.util.Observable;
import java.util.Observer;

/**
 * Brick especial que es invencible, el jugador no puede destruirlo
 * este brick no tiene puntaje ni hitpoints.
 * @author gabriel
 *
 */
public class MetalBrick extends Observable implements IBrick {
  Flyweight flyweight;
  
  /**
   * Constructor del StoneBrick, toma una referencia al Score del jugador,
   * y la lista de Observers del Juego.
   * @param newFlyweight objeto Flyweight del juego.
   */
  public MetalBrick(Flyweight newFlyweight) {
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
  public void hit() {
    // TODO Auto-generated method stub
  }

  @Override
  public int remainingHits() {
    return 10000000;
  }

  @Override
  public String print(Printer printer) {
    return null;
  }

  @Override
  public boolean isDestroyed() {
    return false;
  }

  @Override
  public boolean isMetalBrick() {
    return true;
  }

  @Override
  public boolean isPoisonBrick() {
    return false;
  }
}
