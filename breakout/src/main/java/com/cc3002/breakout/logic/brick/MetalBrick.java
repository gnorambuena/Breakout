package com.cc3002.breakout.logic.brick;

import java.util.Observable;
import java.util.Observer;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Printer;


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
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isStoneBrick() {
    // TODO Auto-generated method stub
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
}
