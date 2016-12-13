package com.cc3002.breakout.logic.bonus;

/**
 * Interface que sirve como una abstraccion para cada bonus del juego.
 * @author gabriel
 *
 */
public interface IBonus {
  void reached();
  
  boolean isExtraBonus();
  
  boolean isDiscount();

  boolean isBatResize();
  
  boolean isAddBall();
}
