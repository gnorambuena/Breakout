package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Modificador que quita 3 puntos al Score.
 * @author gabriel
 *
 */
public class LossScoreModifier implements IBonus {
  
  Player player;
  List<GameObserver> observers;
  
  public LossScoreModifier(Player newPlayer,List<GameObserver> newObservers) {
    player = newPlayer;
    observers = newObservers;
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.addScore(-3);
    for (GameObserver observer : observers) {
      observer.scoreDiscount();
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
