package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Modificador que agrega 5 en Score.
 * @author gabriel
 *
 */
public class AddScoreModifier implements IBonus {
  
  Player player;
  List<GameObserver> observers;
  
  public AddScoreModifier(Flyweight flyweight) {
    player = flyweight.getPlayer();
    observers = flyweight.getObservers();
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.addScore(5);
    for (GameObserver observer : observers) {
      observer.scoreBonus();
    }
  }

  public boolean isExtraBonus() {
    return true;
  }

  public boolean isDiscount() {
    return false;
  }

}
