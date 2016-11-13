package com.cc3002.breakout.logic.bonus;


import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Modificador que quita una vida al Player.
 * @author gabriel
 *
 */
public class LossLifeModifier implements IBonus {
  Player player;
  List<GameObserver> observers;
  
  public LossLifeModifier(Player newPlayer, List<GameObserver> newObservers) {
    player = newPlayer;
    observers = newObservers;
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.lossOfHeart();
    for (GameObserver observer : observers) {
      observer.lifeDiscount();
    }
  }

  public boolean isExtraBonus() {
    return false;
  }

  public boolean isDiscount() {
    return true;
  }
  
  public List<GameObserver> getObservers() {
    return observers;
  }
}
