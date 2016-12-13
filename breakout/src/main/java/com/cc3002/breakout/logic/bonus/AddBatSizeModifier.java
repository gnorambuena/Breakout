package com.cc3002.breakout.logic.bonus;


import com.cc3002.breakout.facade.Flyweight;

import java.util.Observable;
import java.util.Observer;


public class AddBatSizeModifier extends Observable implements IBonus {

  /**
   * Constructor de un modificador que le hace resize al bat.
   * @param flyweight flyweight del juego.
   */
  public AddBatSizeModifier(Flyweight flyweight) {
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
    
  }
  
  @Override
  public void reached() {
    setChanged();
    notifyObservers("RB");
    clearChanged();
  }

  @Override
  public boolean isExtraBonus() {
    return false;
  }

  @Override
  public boolean isDiscount() {
    return false;
  }

  @Override
  public boolean isBatResize() {
    return true;
  }
  
  @Override
  public boolean isAddBall() {
    return false;
  }
}
