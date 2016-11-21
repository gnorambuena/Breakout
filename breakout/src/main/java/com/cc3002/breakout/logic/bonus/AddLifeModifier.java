package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.facade.Flyweight;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

/**
 * Modificador que aumenta en uno el numero de vidas.
 * @author gabriel
 *
 */
public class AddLifeModifier implements IBonus {

  Player player;
  List<GameObserver> observers;
  
  public AddLifeModifier(Flyweight flyweight) {
    player = flyweight.getPlayer();
    observers = flyweight.getObservers();
  }
  
  /**
   * Metodo que ejecuta el modificador.
   */
  public void reached() {
    player.addHearts();
    for (GameObserver observer : observers) {
      observer.lifeBonus();
    }
  }

  public boolean isExtraBonus() {
    return true;
  }

  public boolean isDiscount() {
    return false;
  }

  public List<GameObserver> getObservers() {
    return observers;
  }

}
