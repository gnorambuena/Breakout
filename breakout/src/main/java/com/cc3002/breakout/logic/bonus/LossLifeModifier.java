package com.cc3002.breakout.logic.bonus;


import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Player;

import java.util.Observable;
import java.util.Observer;

/**
 * Modificador que quita una vida al Player.
 * @author gabriel
 *
 */
public class LossLifeModifier extends Observable implements IBonus {
  Player player;
  
  /**
   * Constructor de un modificador que quita vidas.
   * @param flyweight flyweight del juego.
   */
  public LossLifeModifier(Flyweight flyweight) {
    player = flyweight.getPlayer();
    for (Observer observer: flyweight.getObservers()) {
      addObserver(observer);
    }
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.lossOfHeart();
    setChanged();
    notifyObservers("HD");
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
    return false;
  }
}
