package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

public class AddLifeModifier implements IBonus {

  Player player;
  List<GameObserver> observers;
  
  public AddLifeModifier(Player newPlayer, List<GameObserver> newObservers) {
    player = newPlayer;
    observers = newObservers;
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

}
