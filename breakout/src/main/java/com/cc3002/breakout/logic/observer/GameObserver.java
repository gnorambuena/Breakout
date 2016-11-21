package com.cc3002.breakout.logic.observer;

import com.cc3002.breakout.facade.Flyweight;

/**
 * Clase abstracta para todos los Observer del juego.
 * @author gabriel
 *
 */
public abstract class GameObserver {
  
  Flyweight flyweight;
  
  public GameObserver(Flyweight newFlyweight) {
    flyweight = newFlyweight;
  }
  
  public abstract void scoreSoftBrickUpdate();
  
  public abstract void scoreStoneBrickUpdate();
  
  public abstract void levelUpdate(String name);
  
  public abstract void scoreDiscount();
  
  public abstract void scoreBonus();
  
  public abstract void lifeDiscount();
  
  public abstract void lifeBonus();
  
  public abstract void levelAutoSwitch();

  public abstract void bonusAutoSwitch();
}
