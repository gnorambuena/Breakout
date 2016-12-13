package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.facade.Flyweight;

import java.util.Observable;
import java.util.Observer;

/**
 * Modificador que quita una vida al Player.
 * @author gabriel
 *
 */
public class AddBallModifier extends Observable implements IBonus {
  
  /**
   * Constructor de un modificador que quita vidas.
   * @param flyweight flyweight del juego.
   */
  public AddBallModifier(Flyweight flyweight) {
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    setChanged();
    notifyObservers("AB");
    clearChanged();
  }

  public boolean isExtraBonus() {
    return false;
  }

  public boolean isDiscount() {
    return true;
  }

  @Override
  public boolean isBatResize() {
    return false;
  }

  @Override
  public boolean isAddBall() {
    return true;
  }
}

