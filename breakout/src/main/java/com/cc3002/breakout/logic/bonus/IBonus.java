package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;


/**
 * Interface que sirve como una abstraccion para cada bonus del juego.
 * @author gabriel
 *
 */
public interface IBonus {
  void reached();
  
  boolean isExtraBonus();
  
  boolean isDiscount();
  
  List<GameObserver> getObservers();
}
