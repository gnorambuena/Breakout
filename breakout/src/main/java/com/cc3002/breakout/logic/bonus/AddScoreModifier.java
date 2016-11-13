package com.cc3002.breakout.logic.bonus;

import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.observer.GameObserver;

import java.util.List;

public class AddScoreModifier implements IBonus {
  
  Player player;
  List<GameObserver> observers;
  
  public AddScoreModifier(Player newPlayer, List<GameObserver> newObservers) {
    player = newPlayer;
    observers = newObservers;
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

}
